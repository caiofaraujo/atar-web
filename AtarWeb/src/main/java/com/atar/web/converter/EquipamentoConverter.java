package com.atar.web.converter;

import org.springframework.context.annotation.Configuration;

import com.atar.web.dtos.CadastroEquipamentoDto;
import com.atar.web.entities.Equipamentos;

@Configuration
public class EquipamentoConverter {
	
	/**
	 * Converte os dados do DTO para equipamento.
	 * 
	 * @param cadastroEquipamentoDto
	 * @return equipamento
	 */
	public Equipamentos converterDtoParaEquipamento(CadastroEquipamentoDto cadastroEquipamentoDto) {
		Equipamentos equipamento = new Equipamentos();		
		equipamento.setMarca(cadastroEquipamentoDto.getMarca());
		equipamento.setModelo(cadastroEquipamentoDto.getModelo());
		equipamento.setId(cadastroEquipamentoDto.getId());
		
		return equipamento;
	}
	
	/**
	 * Popula o DTO de cadastro com os dados do equipamento
	 * 
	 * @param equipamento
	 * @return cadastroEquipamentoDto
	 */
	public CadastroEquipamentoDto converterCadastroEquipamentoDto(Equipamentos equipamento) {
		CadastroEquipamentoDto cadastroEquipamentoDto = new CadastroEquipamentoDto();
		cadastroEquipamentoDto.setId(equipamento.getId());
		cadastroEquipamentoDto.setMarca(equipamento.getMarca());
		cadastroEquipamentoDto.setModelo(equipamento.getModelo());
		
		return cadastroEquipamentoDto;
	}

}
