package com.atar.web.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.atar.web.entities.Servicos;

public interface ServicoService {

	/**
	 * Persiste um servico na base de dados.
	 * 
	 * @param servico
	 * @return Servicos
	 * @author @Caio Fernandes
	 * 
	 */
	Servicos persistir(Servicos servico);
	
	/**
	 * Retorna uma lista paginada de serviços de um determinado cliente.
	 * 
	 * @param clienteId
	 * @param pageRequest
	 * @return Page<Servicos>
	 * @author @Caio Fernandes
	 */
	//Page<Servicos> buscarPorFuncionarioId(Long clienteId, PageRequest pageRequest);
	
	/**
	 * Retorna uma descricao.
	 * 
	 * @param descricao
	 * @return Optional<Servicos>
	 * @author @Caio Fernandes
	 */
	Optional<Servicos> buscarPorDescricao(String descricao);
	
	
}
