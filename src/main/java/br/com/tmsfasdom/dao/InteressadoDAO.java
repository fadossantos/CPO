package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Interessado;

@Transactional
public interface InteressadoDAO extends CrudRepository<Interessado, Long> {
	public Interessado findByReInteressado(long reInteressado);
}
