package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
import javax.swing.JOptionPane;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

  private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private IConsultaService consultaService;

  @Autowired
	private IClienteService clienteService;

  @Autowired
	private IProfissionalService profissionalService;

  private Usuario getUsuario() {
  		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return usuarioDetails.getUsuario();
  	}

	@GetMapping("/minhasConsultas")
	public String minhasConsultas(ModelMap model) {
		model.addAttribute("consultas", consultaService.buscarPorCliente((Cliente) this.getUsuario()));
    log.info(this.getUsuario().toString());
		return "cliente/minhasConsultas";
	}

  @GetMapping("/agendarConsulta")
	public String agendarConsulta(Consulta consulta) {
		return "cliente/agendarConsulta";
	}

  @PostMapping("/salvarConsulta")
	public String salvarConsulta(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr) {
    try {
  		if (result.hasErrors()) {
  			return "cliente/agendarConsulta";
  		}

      consulta.setCliente(clienteService.buscarPorCpf(consulta.getCpfCliente()));
      consulta.setProfissional(profissionalService.buscarPorCpf(consulta.getCpfProfissional()));
      Date data = data = new SimpleDateFormat("dd/MM/yyyyHH:mm:ss").parse(consulta.getData() + consulta.getHorario());
      consulta.setDataHorario(data);
  		consultaService.salvar(consulta);
  		attr.addFlashAttribute("sucess", "Consulta agendada com sucesso.");
  		return "redirect:/cliente/minhasConsultas";
    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Parsing Error");
    }
    return "redirect:/cliente/minhasConsultas";
	}

}
