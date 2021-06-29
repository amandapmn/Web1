package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import org.springframework.data.repository.query.Param;

public interface IConsultaDAO extends CrudRepository<Consulta, Long> {

	Consulta save(Consulta consulta);

	List<Consulta> findByProfissional(Profissional profissional);
	List<Consulta> findByCliente(Cliente cliente);

	@Query("SELECT c FROM Consulta c WHERE c.dataHorario = :dataHorario AND c.profissional = :profissional")
    public Consulta getConsultaByDataHorarioAndProfissional(@Param("dataHorario") Date dataHorario, @Param("profissional") Profissional profissional);

}
