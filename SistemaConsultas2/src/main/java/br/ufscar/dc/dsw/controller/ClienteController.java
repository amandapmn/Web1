package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.service.EmailService;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Usuario;

import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

import br.ufscar.dc.dsw.security.UsuarioDetails;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.InternetAddress;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IConsultaService consultaService;

  @Autowired
	private IClienteService clienteService;

  @Autowired
	private IProfissionalService profissionalService;

  @Autowired
	private EmailService emailService;

  private Usuario getUsuario() {
  		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return usuarioDetails.getUsuario();
  	}

	@GetMapping("/minhasConsultas")
	public String minhasConsultas(ModelMap model) {
		model.addAttribute("consultas", consultaService.buscarPorCliente((Cliente) this.getUsuario()));
		return "cliente/minhasConsultas";
	}

  @GetMapping("/agendarConsulta/{id}")
	public String agendarConsulta(Consulta consulta, ModelMap model, @PathVariable("id") Long id) {
		model.addAttribute("idProfissional", id);
		return "cliente/agendarConsulta";
	}

  @PostMapping("/salvarConsulta/{id}")
	public String salvarConsulta(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr, @PathVariable("id") Long id) {
    try {
      Date data = new SimpleDateFormat("dd/MM/yyyyHH:mm:ss").parse(consulta.getData() + consulta.getHorario());

      Cliente cliente = (Cliente) this.getUsuario();
      Profissional profissional = profissionalService.buscarPorId(id);

      consulta.setCliente(cliente);
      consulta.setProfissional(profissional);
      consulta.setDataHorario(data);

      if (consultaService.consultaExiste(data, profissional) == true){
        attr.addFlashAttribute("fail", "Já existe uma consulta agendada neste horário");
      }
      else{
        consultaService.salvar(consulta);
        attr.addFlashAttribute("sucess", "Consulta agendada com sucesso.");

	  		InternetAddress from = new InternetAddress("dsw1sistemaconsultas@gmail.com", "SistemaConsultas");
	  		InternetAddress to1 = new InternetAddress(consulta.getCliente().getEmail(),
	       consulta.getCliente().getPrimeiroNome());
	  		InternetAddress to2 = new InternetAddress(consulta.getProfissional().getEmail(),
	       consulta.getProfissional().getPrimeiroNome());

	  		String subject1 = "Consulta.me: Consulta marcada com sucesso!";
	  		String subject2 = "Consulta.me: Nova consulta marcada";

	  		String body1 = "Consulta agendada com sucesso. Segue informações:"
	  				+ "Profissional: " + consulta.getProfissional().getPrimeiroNome()
	          + " " + consulta.getProfissional().getSobrenome()
	  				+ "Especialidade: " + consulta.getProfissional().getEspecialidade()
	  				+ "Data e horário: " + consulta.getDataHorario()
	  				+ "Link da consulta: " + consulta.getVideoconferencia();

	  		String body2 = "Nova consulta agendada. Segue informações:"
	  				+ "Cliente: " + consulta.getCliente().getPrimeiroNome()
	          + " " + consulta.getCliente().getSobrenome()
	  				+ "Data e horário: " + consulta.getDataHorario()
	  				+ "Link da consulta: " + consulta.getVideoconferencia();

	  		// Envio cliente
	  		emailService.send(from, to1, subject1, body1);

	  		// Envio profissional
	  		emailService.send(from, to2, subject2, body2);
      }
  		return "redirect:/cliente/minhasConsultas";
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/cliente/minhasConsultas";
	}

}
