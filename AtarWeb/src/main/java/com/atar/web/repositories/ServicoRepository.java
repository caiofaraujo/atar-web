package com.atar.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.atar.web.entities.Clientes;
import com.atar.web.entities.Equipamentos;
import com.atar.web.entities.Servicos;

@Transactional (readOnly = true)
public interface ServicoRepository extends JpaRepository<Servicos, Long> {
	
	
	Servicos findByDescricao(String descricao);

	Optional<List<Servicos>> findByCliente(Clientes cliente);
	
	Optional<List<Servicos>> findByEquipamento(Equipamentos equipamento);
	

	// Metódo responsável por retornar todos os clientes
	//@Query
	//List<Servicos> findByClientesId(@Param("id") Long id);
	
	// Metódo responsável por retornar todos os clientes de forma pageada (ex: do 01 ao 10 cliente)	
	//@Query
	//Page<Servicos> findByClientesId(@Param("id") Long id, Pageable pageable);

}

