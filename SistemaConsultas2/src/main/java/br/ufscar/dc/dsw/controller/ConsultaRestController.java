package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import java.util.Date;

@RestController
public class ConsultaRestController {

  @Autowired
	private IConsultaService consultaService;

  @Autowired
	private IClienteService clienteService;

  @Autowired
	private IProfissionalService profissionalService;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

  @SuppressWarnings("unchecked")
	private void parse(Cliente cliente, JSONObject json) {

		cliente.setEmail((String) json.get("email"));
		cliente.setSenha((String) json.get("senha"));
    cliente.setCpf((String) json.get("cpf"));
		cliente.setPrimeiroNome((String) json.get("primeiroNome"));
    cliente.setSobrenome((String) json.get("sobrenome"));
    cliente.setPapel("CLIENTE");
    cliente.setTelefone((String) json.get("telefone"));
    cliente.setSexo((String) json.get("sexo"));
    cliente.setDataNasc((String) json.get("dataNasc"));
	}

  @SuppressWarnings("unchecked")
	private void parse(Profissional profissional, JSONObject json) {

    profissional.setEmail((String) json.get("email"));
		profissional.setSenha((String) json.get("senha"));
    profissional.setCpf((String) json.get("cpf"));
		profissional.setPrimeiroNome((String) json.get("primeiroNome"));
    profissional.setSobrenome((String) json.get("sobrenome"));
    profissional.setPapel("PROFISSIONAL");
    profissional.setEspecialidade((String) json.get("especialidade"));
    profissional.setQualificacoes((String) json.get("qualificacoes"));
	}

	@SuppressWarnings("unchecked")
	private void parse(Consulta consulta, JSONObject json) {

		consulta.setDataHorario((Date) json.get("dataHorario"));
		consulta.setVideoconferencia((String) json.get("videoconferencia"));

    Cliente cliente = new Cliente();
    Profissional profissional = new Profissional();

    parse(cliente, json);
    parse(profissional, json);

    consulta.setCliente(cliente);
    consulta.setProfissional(profissional);
	}

	@GetMapping(path = "/consultas")
	public ResponseEntity<List<Consulta>> lista() {
		List<Consulta> lista = consultaService.buscarTodas();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/consultas/{id}")
	public ResponseEntity<Consulta> lista(@PathVariable("id") long id) {
		Consulta consulta = consultaService.buscarPorId(id);
		if (consulta == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(consulta);
	}

  @GetMapping(path = "/consultas/clientes/{id}")
	public ResponseEntity<List<Consulta>>  clienteLista(@PathVariable("id") long id) {
		List<Consulta> lista = consultaService.buscarPorCliente(clienteService.buscarPorId(id));
		if (lista == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

  @GetMapping(path = "/consultas/profissionais/{id}")
	public ResponseEntity<List<Consulta>>  profissionalLista(@PathVariable("id") long id) {
		List<Consulta> lista = consultaService.buscarPorProfissional(profissionalService.buscarPorId(id));
		if (lista == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
}
