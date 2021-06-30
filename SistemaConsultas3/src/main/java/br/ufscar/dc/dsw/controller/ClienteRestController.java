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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@RestController
public class ClienteRestController {

  @Autowired
	private IClienteService clienteService;

  @Autowired
  BCryptPasswordEncoder encoder;

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
		cliente.setSenha(encoder.encode((String) json.get("senha")));
    cliente.setCpf((String) json.get("cpf"));
		cliente.setPrimeiroNome((String) json.get("primeiroNome"));
    cliente.setSobrenome((String) json.get("sobrenome"));
    cliente.setPapel("CLIENTE");
    cliente.setTelefone((String) json.get("telefone"));
    cliente.setSexo((String) json.get("sexo"));
    cliente.setDataNasc((String) json.get("dataNasc"));
	}

	@GetMapping(path = "/clientes")
	public ResponseEntity<List<Cliente>> lista() {
		List<Cliente> lista = clienteService.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/clientes/{id}")
	public ResponseEntity<Cliente> lista(@PathVariable("id") long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}

	@PostMapping(path = "/clientes")
	@ResponseBody
	public ResponseEntity<Cliente> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = new Cliente();
				parse(cliente, json);
				clienteService.salvar(cliente);
				return ResponseEntity.ok(cliente);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@PutMapping(path = "/clientes/{id}")
	public ResponseEntity<Cliente> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = clienteService.buscarPorId(id);
				if (cliente == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(cliente, json);
					clienteService.salvar(cliente);
					return ResponseEntity.ok(cliente);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@DeleteMapping(path = "/clientes/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Cliente cliente = clienteService.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			clienteService.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}
