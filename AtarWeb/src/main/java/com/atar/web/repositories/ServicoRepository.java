package com.atar.web.repositories;

import java.util.List;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.atar.web.entities.Servicos;

@Transactional (readOnly = true)

@NamedQueries({
	@NamedQuery(name = "ServicoRepository.findByClientesId", query = "SELECT clie FROM tb_clientes_clie clie WHERE clie.clientes.id = :id")})
public interface ServicoRepository extends JpaRepository<Servicos, Long> {
	
	// Metódo responsável por retornar todos os clientes
	@Query
	List<Servicos> findByClientesId(@Param("id") Long id);
	
	// Metódo responsável por retornar todos os clientes de forma pageada (ex: do 01 ao 10 cliente)	
	@Query
	Page<Servicos> findByClientesId(@Param("id") Long id, Pageable pageable);

}

