package com.atar.web.repositories;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.atar.web.entities.Equipamentos;

@Transactional (readOnly = true)
public interface EquipamentoRepository extends JpaRepository<Equipamentos, Long>{
	
	
	
	Equipamentos findByModelo(String modelo);
	
	Equipamentos findByMarca(String marca);
	
	Equipamentos findByModeloOrMarca(String modelo, String email);

}
