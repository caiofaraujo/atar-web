package com.atar.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.atar.web.entities.Clientes;

public interface ClienteRepository  extends JpaRepository<Clientes, Long>{
	
	@Transactional (readOnly = true)
	Clientes findByNome(String nome);
	
	@Transactional (readOnly = true)
	Clientes findByEndereco(String endereco);
	
	@Transactional (readOnly = false)
	void deleteById(Long id);
	
}
