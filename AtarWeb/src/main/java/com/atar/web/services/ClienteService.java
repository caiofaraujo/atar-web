package com.atar.web.services;

import java.util.Optional;
import com.atar.web.entities.Clientes;

public interface ClienteService {
	
	
	/**
	 * Cadastra um cliente dado um nome.
	 * 
	 * @param cliente
	 * @return Clientes
	 * @author @Caio Fernandes
	 */
	Clientes persistir (Clientes cliente);
	
	
	/**
	 * Retorna um cliente dado um nome.
	 * 
	 * @param nome
	 * @return Optional<Clientes>
	 * @author @Caio Fernandes
	 */
	Optional<Clientes> buscarPorNome(String nome);
	
	/**
	 * Retorna um cliente dado um endereco.
	 * 
	 * @param endereco
	 * @return Optional<Clientes>
	 * @author @Caio Fernandes
	 */
	Optional<Clientes> buscarPorEndereco(String endereco);
	
	
}
