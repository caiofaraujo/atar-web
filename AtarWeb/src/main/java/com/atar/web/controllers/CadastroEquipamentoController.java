package com.atar.web.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atar.web.dtos.CadastroClienteDto;
import com.atar.web.dtos.CadastroEquipamentoDto;
import com.atar.web.entities.Clientes;
import com.atar.web.entities.Equipamentos;
import com.atar.web.response.Response;
import com.atar.web.services.EquipamentoService;

@RestController
@RequestMapping("/atar/cadastrar-equipamento")
@CrossOrigin(origins = "*")
public class CadastroEquipamentoController {

	private static final Logger log = LoggerFactory.getLogger(CadastroClienteController.class);
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	public CadastroEquipamentoController() {
			
	}
	
	
	/**
	 * Cadastra um equipamento no sistema.
	 * 
	 * @param EquipamentoDto
	 * @param result
	 * @return ResponseEntity<Response<EquipamentoDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroEquipamentoDto>> cadastrar(@Valid @RequestBody CadastroEquipamentoDto cadastroEquipamentoDto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Cadastrando Equipamento: {}", cadastroEquipamentoDto.toString());
		Response<CadastroEquipamentoDto> response = new Response<CadastroEquipamentoDto>();

		validarDadosExistentes(cadastroEquipamentoDto, result);
		Equipamentos equipamento = this.converterDtoParaEquipamento(cadastroEquipamentoDto);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de equipamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.equipamentoService.persistir(equipamento);		

		response.setData(this.converterCadastroEquipamentoDto(equipamento));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Verifica se o equipamento já existe na base de dados.
	 * 
	 * @param cadastroEquipamentoDto
	 * @param result
	 */
	private void validarDadosExistentes(CadastroEquipamentoDto cadastroEquipamentoDto, BindingResult result) {
		
		this.equipamentoService.buscarPorMarca(cadastroEquipamentoDto.getMarca())
				.ifPresent(cli -> result.addError(new ObjectError("marca", "Marca já existente.")));
		
		this.equipamentoService.buscarPorModelo(cadastroEquipamentoDto.getModelo())
		.ifPresent(cli -> result.addError(new ObjectError("modelo", "Modelo já existente.")));

	}
	
	/**
	 * Converte os dados do DTO para equipamento.
	 * 
	 * @param cadastroEquipamentoDto
	 * @return equipamento
	 */
	private Equipamentos converterDtoParaEquipamento(CadastroEquipamentoDto cadastroEquipamentoDto) {
		Equipamentos equipamento = new Equipamentos();		
		equipamento.setMarca(cadastroEquipamentoDto.getMarca());
		equipamento.setModelo(cadastroEquipamentoDto.getModelo());
		
		return equipamento;
	}
	
	/**
	 * Popula o DTO de cadastro com os dados do equipamento
	 * 
	 * @param equipamento
	 * @return cadastroEquipamentoDto
	 */
	private CadastroEquipamentoDto converterCadastroEquipamentoDto(Equipamentos equipamento) {
		CadastroEquipamentoDto cadastroEquipamentoDto = new CadastroEquipamentoDto();
		cadastroEquipamentoDto.setId(equipamento.getId());
		cadastroEquipamentoDto.setMarca(equipamento.getMarca());
		cadastroEquipamentoDto.setModelo(equipamento.getModelo());
		
		return cadastroEquipamentoDto;
	}
	
}
