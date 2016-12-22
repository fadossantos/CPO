package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Status;

@Transactional
public interface StatusDAO extends CrudRepository<Status, Long> {
	public Status findByDescStatus(String descStatus);
}
