package br.ufscar.dc.dsw.service.impl;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IConsultaService;

@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService {

	@Autowired
	IConsultaDAO dao;

	public void salvar(Consulta consulta) {
		dao.save(consulta);
	}

	@Transactional(readOnly = true)
	public Consulta buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Consulta> buscarTodas() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Consulta> buscarPorProfissional(Profissional profissional) {
		return dao.findByProfissional(profissional);
	}

	@Transactional(readOnly = true)
	public List<Consulta> buscarPorCliente(Cliente cliente) {
		return dao.findByCliente(cliente);
	}

	public Boolean consultaExiste(Date dataHorario, Profissional profissional) {
		return dao.getConsultaByDataHorarioAndProfissional(dataHorario, profissional) != null;
	}

}
