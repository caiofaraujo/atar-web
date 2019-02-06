package com.atar.web.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.atar.web.entities.Clientes;
import com.atar.web.entities.Equipamentos;
import com.atar.web.entities.Servicos;
import com.atar.web.repositories.ClienteRepository;
import com.atar.web.repositories.ServicoRepository;
import com.atar.web.services.ServicoService;

@Service
public class ServicoServiceImpl implements ServicoService{

	private static final Logger log = LoggerFactory.getLogger(ServicoServiceImpl.class);
	
	
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	public Servicos persistir(Servicos servico) {
		log.info("[EquipamentoServiceImpl : persistir] - Persistindo um serviço {}", servico);
		return this.servicoRepository.save(servico);
	}

	public Optional<Servicos> buscarPorDescricao(String descricao) {
		log.info("[EquipamentoServiceImpl : buscarPorMarca] - Buscando um equipamento por marca {}", descricao);
		return Optional.ofNullable(servicoRepository.findByDescricao(descricao));
	}

	
	
}
