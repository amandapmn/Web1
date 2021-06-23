package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Profissional;

import br.ufscar.dc.dsw.service.spec.IProfissionalService;

import br.ufscar.dc.dsw.security.UsuarioDetails;


@Controller
@RequestMapping("/")
public class IndexController {

  @Autowired
	private IProfissionalService profissionalService;

  @GetMapping("/")
	public String index() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}

  @GetMapping("/login")
	public String login() {
		return "login";
	}

  @GetMapping("/listarProfissionais")
  public String listaProfissionais(ModelMap model, @RequestParam(value="especialidade", required=false) String especialidade) {
    if(especialidade == null){
      model.addAttribute("profissionais", profissionalService.buscarTodos());
    }
    else{
      model.addAttribute("profissionais", profissionalService.buscarPorEspecialidade(especialidade));
    }
    return "listaProfissionais";
  }

}
