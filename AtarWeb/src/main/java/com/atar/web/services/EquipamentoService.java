package com.atar.web.services;

import java.util.Optional;

import com.atar.web.entities.Clientes;
import com.atar.web.entities.Equipamentos;

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
	
	
}
