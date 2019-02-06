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

import com.atar.web.dtos.CadastroServicoDto;
import com.atar.web.entities.Equipamentos;
import com.atar.web.entities.Servicos;
import com.atar.web.response.Response;

import com.atar.web.services.EquipamentoService;
import com.atar.web.services.ServicoService;

@RestController
@RequestMapping("/atar/cadastrar-servico")
@CrossOrigin(origins = "*")
public class CadastroServicoController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroServicoController.class);
	
	@Autowired
	private ServicoService servicoService;
	

	public CadastroServicoController() {
		
	}
	
	
	/**
	 * Cadastra um servico no sistema.
	 * 
	 * @param ServicoDto
	 * @param result
	 * @return ResponseEntity<Response<ServicoDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroServicoDto>> cadastrar(@Valid @RequestBody CadastroServicoDto cadastroServicoDto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Cadastrando Servico: {}", cadastroServicoDto.toString());
		Response<CadastroServicoDto> response = new Response<CadastroServicoDto>();

		//validarDadosExistentes(cadastroServicoDto, result);
		Servicos servico = this.converterDtoParaServico(cadastroServicoDto);
		

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de servico: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.servicoService.persistir(servico);		

		response.setData(this.converterCadastroServicoDto(servico));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Verifica se o servico já existe na base de dados.
	 * 
	 * @param cadastroServicoDto
	 * @param result
	 */
	//private void validarDadosExistentes(CadastroServicoDto cadastroServicoDto, BindingResult result) {
	//	this.servicoService.buscarPorNome(cadastroServicoDto.getNome())
	//			.ifPresent(cli -> result.addError(new ObjectError("servico", "Servico já existente.")));	

	//}
	
	/**
	 * Converte os dados do DTO para servico.
	 * 
	 * @param cadastroServicoDto
	 * @return servico
	 */
	private Servicos converterDtoParaServico(CadastroServicoDto cadastroServicoDto) {
		
		Servicos servico = new Servicos();
		servico.setDescricao(cadastroServicoDto.getDescricao());
		servico.setTipo(cadastroServicoDto.getTipo());
		servico.setObservacao(cadastroServicoDto.getObservacao());
		servico.setDtInicioServ(cadastroServicoDto.getDtInicioServico());
		servico.setDtFinalServ(cadastroServicoDto.getDtFinalServico());

		return servico;
		
	}
	
	/**
	 * Popula o DTO de cadastro com os dados do servico
	 * 
	 * @param servico
	 * @return cadastroServicoDto
	 */
	private CadastroServicoDto converterCadastroServicoDto(Servicos servico) {
		CadastroServicoDto cadastroServicoDto = new CadastroServicoDto();
		
		cadastroServicoDto.setDescricao(servico.getDescricao());
		cadastroServicoDto.setTipo(servico.getTipo());
		cadastroServicoDto.setObservacao(servico.getObservacao());
		cadastroServicoDto.setDtInicioServico(servico.getDtInicioServ());
		cadastroServicoDto.setDtFinalServico(servico.getDtFinalServ());
		return cadastroServicoDto;
	}
	
}
