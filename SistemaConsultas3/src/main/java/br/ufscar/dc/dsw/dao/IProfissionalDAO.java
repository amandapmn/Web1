package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Profissional;

public interface IProfissionalDAO extends CrudRepository<Profissional, Long> {
  Profissional findById(long id);

  Profissional findByCpf(String cpf);

  List<Profissional> findByEspecialidadeLike(String especialidade);

  List<Profissional> findAll();

  Profissional save(Profissional profissional);

  void deleteById(Long id);
}
