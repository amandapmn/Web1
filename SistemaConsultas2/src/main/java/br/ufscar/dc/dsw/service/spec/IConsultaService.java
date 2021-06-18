package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;

public interface IConsultaService {

	void salvar(Consulta consulta);

	List<Consulta> buscarPorCliente(Cliente cliente);

	List<Consulta> buscarPorProfissional(Profissional profissional);
}
