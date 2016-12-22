package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.ProcessoRelacionado;

@Transactional
public interface ProcessoRelacionadoDAO extends CrudRepository<ProcessoRelacionado, Long> {
}
