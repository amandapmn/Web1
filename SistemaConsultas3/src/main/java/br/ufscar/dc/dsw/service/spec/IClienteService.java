package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;

public interface IClienteService {

	Cliente buscarPorId(Long id);

	Cliente buscarPorCpf(String cpf);

	List<Cliente> buscarTodos();

	void salvar(Cliente cliente);

	void excluir(Long id);
}
