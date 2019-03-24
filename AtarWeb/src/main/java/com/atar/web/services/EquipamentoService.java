package com.atar.web.services;

import java.util.List;
import java.util.Optional;

import com.atar.web.entities.Equipamentos;
import com.atar.web.entities.Servicos;

public interface EquipamentoService {

	/**
	 * Cadastra um equipamento dado equipamento.
	 * 
	 * @param equipamento
	 * @return Equipamentos
	 * @author @Caio Fernandes
	 */
	Equipamentos persistir (Equipamentos equipamento);
	
	/**
	 * Retorna um equipamento dado uma marca.
	 * 
	 * @param marca
	 * @return Optional<Equipamentos>
	 * @author @Caio Fernandes
	 */
	Optional<Equipamentos> buscarPorMarca(String marca);
	
	/**
	 * Retorna um equipamento dado um modelo.
	 * 
	 * @param modelo
	 * @return Optional<Equipamentos>
	 * @author @Caio Fernandes
	 */
	Optional<Equipamentos> buscarPorModelo(String modelo);
	
	/**
	 * Retorna um equipamento dado um ID.
	 * 
	 * @param id
	 * @return Optional<Equipamentos>
	 * @author @Caio Fernandes
	 */
	
	/**
	 * Retorna uma lista de equipamentos.
	 * 
	 * @return List<Equipamentos>
	 * @author @Caio Fernandes
	 */
	List<Equipamentos> listarEquipamentos();
	
	/**
	 * Realiza a exclusão de um equipamento.
	 * 
	 * @param equipamento
	 * @author @Caio Fernandes
	 */
	void deletar(Equipamentos equipamento);
	
	
}
