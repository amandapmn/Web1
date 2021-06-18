package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Usuario;

import br.ufscar.dc.dsw.service.spec.IConsultaService;

import br.ufscar.dc.dsw.security.UsuarioDetails;

@Controller
@RequestMapping("/profissional")
public class ProfissionalController {

  private static final Logger log = LoggerFactory.getLogger(ProfissionalController.class);

	@Autowired
	private IConsultaService consultaService;

  private Usuario getUsuario() {
  		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return usuarioDetails.getUsuario();
  	}

	@GetMapping("/minhasConsultas")
	public String minhasConsultas(ModelMap model) {
		model.addAttribute("consultas", consultaService.buscarPorProfissional((Profissional) this.getUsuario()));
    log.info(this.getUsuario().toString());
		return "profissional/minhasConsultas";
	}

}
