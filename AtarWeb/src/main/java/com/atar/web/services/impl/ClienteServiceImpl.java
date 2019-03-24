package com.atar.web.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atar.web.entities.Clientes;
import com.atar.web.repositories.ClienteRepository;
import com.atar.web.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

	@Autowired
	private ClienteRepository clienteRepository;
		
	@Override
	public Clientes persistir (Clientes cliente) {
		log.info("[ClienteServiceImpl : persistir] - Persistindo um cliente {}", cliente);
		return this.clienteRepository.save(cliente);
	}
	
	@Override
	public Optional<Clientes> buscarPorNome(String nome) {
		log.info("[ClienteServiceImpl : buscarPorNome] - Buscando um cliente por nome {}", nome);
		return Optional.ofNullable(clienteRepository.findByNome(nome));
	}
	
	@Override
	public Optional<Clientes> buscarPorEndereco(String endereco) {
		log.info("[ClienteServiceImpl : buscarPorEndereco] - Buscando um cliente por endereco {}", endereco);
		return Optional.ofNullable(clienteRepository.findByEndereco(endereco));
	}

	@Override
	public List<Clientes> listarClientes() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();			
	}

	public void deletar(Long id) {
		// TODO Auto-generated method stub
		this.clienteRepository.deleteById(id);
	}
		
	
}

	

