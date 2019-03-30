package com.atar.web.converter;

import org.springframework.context.annotation.Configuration;

import com.atar.web.dtos.CadastroClienteDto;
import com.atar.web.dtos.CadastroEquipamentoDto;
import com.atar.web.entities.Clientes;
import com.atar.web.entities.Equipamentos;

@Configuration
public class ClienteConverter {
	/**
	 * Converte os dados do DTO para cliente.
	 * 
	 * @param cadastroClienteDto
	 * @return cliente
	 */
	public Clientes converterDtoParaCliente(CadastroClienteDto cadastroClienteDto) {
		Clientes cliente = new Clientes();
		cliente.setNome(cadastroClienteDto.getNome());
		cliente.setEndereco(cadastroClienteDto.getEndereco());
		cliente.setNrTelefone(cadastroClienteDto.getTelefone());
//		cliente.setDtCadastro(dataAtual());
		cliente.setId(cadastroClienteDto.getId());

		return cliente;
	}

	/**
	 * Popula o DTO de cadastro com os dados do cliente
	 * 
	 * @param cliente
	 * @return cadastroClienteDto
	 */
	public CadastroClienteDto converterCadastroClienteDto(Clientes cliente) {
		//
		CadastroClienteDto cadastroClienteDto = new CadastroClienteDto();
		if (cliente.getId() != null) {
			cadastroClienteDto.setId(cliente.getId());
		}
		cadastroClienteDto.setNome(cliente.getNome());
		cadastroClienteDto.setEndereco(cliente.getEndereco());
		cadastroClienteDto.setTelefone(cliente.getNrTelefone());
		return cadastroClienteDto;
	}
}
