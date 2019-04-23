package com.atar.web.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atar.web.dtos.CadastroClienteDto;
import com.atar.web.dtos.CadastroEquipamentoDto;
import com.atar.web.entities.Clientes;
import com.atar.web.entities.Equipamentos;
import com.atar.web.entities.Servicos;
import com.atar.web.response.Response;
import com.atar.web.services.EquipamentoService;
import com.atar.web.services.ServicoService;

@RestController
@RequestMapping("/atar/cadastrar-equipamento")
@CrossOrigin(origins = "*")
public class CadastroEquipamentoController {

	private static final Logger log = LoggerFactory.getLogger(CadastroClienteController.class);
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	@Autowired
	private ServicoService servicoService;
	
	public CadastroEquipamentoController() {
			
	}
	
	/**
	 * Listagem de equipamentos no sistema.
	 * 
	 * @return ResponseEntity<Response<EquipamentoDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@GetMapping("/listar-equipamento")
	public ResponseEntity<Response<List<CadastroEquipamentoDto>>> listarEquipamentos() throws NoSuchAlgorithmException {
		log.info("Listando Equipamentos..");
		Response<List<CadastroEquipamentoDto>> response = new Response<List<CadastroEquipamentoDto>>();
		List<Equipamentos> lst = equipamentoService.listarEquipamentos();
		List<CadastroEquipamentoDto> lstDto = new ArrayList<>();
		for (Equipamentos equipamentos : lst) {
			lstDto.add(converterCadastroEquipamentoDto(equipamentos));
		}			
		 response.setData(lstDto);		
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Exclusão de equipamentos no sistema.
	 * 
	 * @param cadastroEquipamentoDto
	 * @return ResponseEntity<Response<EquipamentoDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping("excluir")
	public ResponseEntity<Response<CadastroEquipamentoDto>> excluir(
			  @RequestBody CadastroEquipamentoDto cadastroEquipamentoDto)
			throws NoSuchAlgorithmException {

		log.info("Deletando Equipamento: {}", cadastroEquipamentoDto.toString());
		Response<CadastroEquipamentoDto> response = new Response<CadastroEquipamentoDto>();

		Equipamentos equipamento = this.converterDtoParaEquipamento(cadastroEquipamentoDto);
		
		Optional<List<Servicos>> retorno = servicoService.buscarPorEquipamento(equipamento);
		
		if(retorno.isPresent()) {
			response.getErrors().add("Existem serviços atrelados ao equipamento.");
			return ResponseEntity.badRequest().body(response);
		} else {
			equipamentoService.deletar(equipamento);
		}
		

		return ResponseEntity.ok(response);
	}
	
	/**
	 * Alteração de equipamentos no sistema.
	 * 
	 * @param cadastroEquipamentoDto
	 * @param result
	 * @return ResponseEntity<Response<EquipamentoDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping("alterar")
	public ResponseEntity<Response<CadastroEquipamentoDto>> update(
			@Valid @RequestBody CadastroEquipamentoDto cadastroEquipamentoDto, BindingResult result)
			throws NoSuchAlgorithmException {

		log.info("Alterando Equipamento: {}", cadastroEquipamentoDto.toString());
		Response<CadastroEquipamentoDto> response = new Response<CadastroEquipamentoDto>();

		Equipamentos equipamento = this.converterDtoParaEquipamento(cadastroEquipamentoDto);

		if (result.hasErrors()) {
			log.error("Erro validando dados de alteração de equipamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.equipamentoService.persistir(equipamento);

		response.setData(this.converterCadastroEquipamentoDto(equipamento));
		return ResponseEntity.ok(response);
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
		
//		this.equipamentoService.buscarPorMarca(cadastroEquipamentoDto.getMarca())
//				.ifPresent(cli -> result.addError(new ObjectError("marca", "Marca já existente.")));
		
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
		equipamento.setId(cadastroEquipamentoDto.getId());
		
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
