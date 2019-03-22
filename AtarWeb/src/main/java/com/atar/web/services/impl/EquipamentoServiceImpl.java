package com.atar.web.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atar.web.entities.Equipamentos;
import com.atar.web.repositories.EquipamentoRepository;
import com.atar.web.services.EquipamentoService;

@Service
public class EquipamentoServiceImpl implements EquipamentoService {
	
	private static final Logger log = LoggerFactory.getLogger(EquipamentoServiceImpl.class);
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	public Equipamentos persistir(Equipamentos equipamento) {		
		log.info("[EquipamentoServiceImpl : persistir] - Persistindo um equipamento {}", equipamento);
		return this.equipamentoRepository.save(equipamento);
	} 
	
	public Optional<Equipamentos> buscarPorMarca(String marca) {
		log.info("[EquipamentoServiceImpl : buscarPorMarca] - Buscando um equipamento por marca {}", marca);
		return Optional.ofNullable(equipamentoRepository.findByMarca(marca));
	}
	
	public Optional<Equipamentos> buscarPorModelo(String modelo) {
		log.info("[EquipamentoServiceImpl : buscarPorModelo] - Buscando um equipamento por modelo {}", modelo);
		return Optional.ofNullable(equipamentoRepository.findByModelo(modelo));
	}

	@Override
	public List<Equipamentos> listarEquipamentos() {
		// TODO Auto-generated method stub
		return equipamentoRepository.findAll();	
	}

	@Override
	public void deletar(Equipamentos equipamento) {
		// TODO Auto-generated method stub
		this.equipamentoRepository.delete(equipamento);
	}

}
