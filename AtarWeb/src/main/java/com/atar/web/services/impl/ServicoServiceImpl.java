package com.atar.web.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.atar.web.entities.Servicos;
import com.atar.web.repositories.ServicoRepository;

public class ServicoServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(ServicoServiceImpl.class);
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	public Page<Servicos> buscarPorClienteId(Long clienteId, PageRequest pageRequest) {
		log.info("[EquipamentoServiceImpl : buscarPorClienteId] - Buscando lista de clientes por ID {}", clienteId);
		return this.servicoRepository.findByClientesId(clienteId, pageRequest);
	}
	
	
	public Servicos persistir(Servicos servico) {
		log.info("[EquipamentoServiceImpl : persistir] - Persistindo um serviço {}", servico);
		return this.servicoRepository.save(servico);
	}
	
}
