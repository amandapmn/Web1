package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
  private IProfissionalService profissionalService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/clientes/cadastrar")
	public String cadastrarCliente(Cliente cliente) {
		return "admin/clientes/cadastro";
	}

	@GetMapping("/clientes/listar")
	public String listarClientes(ModelMap model) {
		model.addAttribute("clientes", clienteService.buscarTodos());
		return "admin/clientes/lista";
	}

	@PostMapping("/clientes/salvar")
	public String salvarCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "admin/clientes/cadastro";
		}
		cliente.setPapel("CLIENTE");
		cliente.setSenha(encoder.encode(cliente.getSenha()));
		clienteService.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente inserido com sucesso.");
		return "redirect:/admin/clientes/listar";
	}


  @GetMapping("/clientes/editar/{id}")
	public String preEditarCliente(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", clienteService.buscarPorId(id));
		return "admin/clientes/cadastro";
	}

	@PostMapping("/clientes/editar")
	public String editarCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "admin/clientes/cadastro";
		}

		cliente.setPapel("CLIENTE");
		cliente.setSenha(encoder.encode(cliente.getSenha()));
		clienteService.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
		return "redirect:/admin/clientes/listar";
	}

	@GetMapping("/clientes/excluir/{id}")
	public String excluirCliente(@PathVariable("id") Long id, ModelMap model) {
		clienteService.excluir(id);
		model.addAttribute("sucess", "Cliente excluído com sucesso.");
		return listarClientes(model);
	}



  @GetMapping("/profissionais/cadastrar")
	public String cadastrarProfissional(Profissional profissional) {
		return "admin/profissionais/cadastro";
	}

	@GetMapping("/profissionais/listar")
	public String listarProfissionais(ModelMap model) {
		model.addAttribute("profissionais", profissionalService.buscarTodos());
		return "admin/profissionais/lista";
	}

	@PostMapping("/profissionais/salvar")
	public String salvarProfissional(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "admin/profissionais/cadastro";
		}

		profissional.setPapel("PROFISSIONAL");
		profissional.setSenha(encoder.encode(profissional.getSenha()));
		profissionalService.salvar(profissional);
		attr.addFlashAttribute("sucess", "Profissional inserido com sucesso.");
		return "redirect:/admin/profissionais/listar";
	}

	@GetMapping("/profissionais/editar/{id}")
	public String preEditarProfissional(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("profissional", profissionalService.buscarPorId(id));
		return "admin/profissionais/cadastro";
	}

	@PostMapping("/profissionais/editar")
	public String editarProfissional(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "admin/profissionais/cadastro";
		}

		profissional.setPapel("PROFISSIONAL");
		profissional.setSenha(encoder.encode(profissional.getSenha()));
		profissionalService.salvar(profissional);
		attr.addFlashAttribute("sucess", "Profissional editado com sucesso.");
		return "redirect:/admin/profissionais/listar";
	}

	@GetMapping("/profissionais/excluir/{id}")
	public String excluirProfissional(@PathVariable("id") Long id, ModelMap model) {
		profissionalService.excluir(id);
		model.addAttribute("sucess", "Profissional excluído com sucesso.");
		return listarProfissionais(model);
	}

}
