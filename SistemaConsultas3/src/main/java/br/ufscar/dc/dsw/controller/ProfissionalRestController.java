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

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
public class ProfissionalRestController {

  @Autowired
	private IProfissionalService profissionalService;

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
	private void parse(Profissional profissional, JSONObject json) {

    profissional.setEmail((String) json.get("email"));
		profissional.setSenha(encoder.encode((String) json.get("senha")));
    profissional.setCpf((String) json.get("cpf"));
		profissional.setPrimeiroNome((String) json.get("primeiroNome"));
    profissional.setSobrenome((String) json.get("sobrenome"));
    profissional.setPapel("PROFISSIONAL");
    profissional.setEspecialidade((String) json.get("especialidade"));
    profissional.setQualificacoes((String) json.get("qualificacoes"));
	}

	@GetMapping(path = "/profissionais")
	public ResponseEntity<List<Profissional>> lista() {
		List<Profissional> lista = profissionalService.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/profissionais/{id}")
	public ResponseEntity<Profissional> lista(@PathVariable("id") long id) {
		Profissional profissional = profissionalService.buscarPorId(id);
		if (profissional == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(profissional);
	}

  @GetMapping(path = "/profissionais/especialidades/{nome}")
	public ResponseEntity<List<Profissional>> listaEspecialidades(@PathVariable String nome) {
		List<Profissional> lista = profissionalService.buscarPorEspecialidade(nome + "%");
		if (lista == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping(path = "/profissionais")
	@ResponseBody
	public ResponseEntity<Profissional> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Profissional profissional = new Profissional();
				parse(profissional, json);
				profissionalService.salvar(profissional);
				return ResponseEntity.ok(profissional);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@PutMapping(path = "/profissionais/{id}")
	public ResponseEntity<Profissional> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Profissional profissional = profissionalService.buscarPorId(id);
				if (profissional == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(profissional, json);
					profissionalService.salvar(profissional);
					return ResponseEntity.ok(profissional);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@DeleteMapping(path = "/profissionais/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Profissional profissional = profissionalService.buscarPorId(id);
		if (profissional == null) {
			return ResponseEntity.notFound().build();
		} else {
			profissionalService.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}
