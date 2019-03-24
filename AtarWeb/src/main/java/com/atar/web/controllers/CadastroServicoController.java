package com.atar.web.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atar.web.converter.EquipamentoConverter;
import com.atar.web.dtos.CadastroEquipamentoDto;
import com.atar.web.dtos.CadastroServicoDto;
import com.atar.web.entities.Equipamentos;
import com.atar.web.entities.Servicos;
import com.atar.web.repositories.ClienteRepository;
import com.atar.web.response.Response;
import com.atar.web.services.ServicoService;

@RestController
@RequestMapping("/atar/cadastrar-servico")
@CrossOrigin(origins = "*")
public class CadastroServicoController {

	private static final Logger log = LoggerFactory.getLogger(CadastroServicoController.class);

	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	EquipamentoConverter equipamentoConverter;  

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
	public ResponseEntity<Response<CadastroServicoDto>> cadastrar(
			@Valid @RequestBody CadastroServicoDto cadastroServicoDto, BindingResult result)
			throws NoSuchAlgorithmException {

		log.info("Cadastrando Servico: {}", cadastroServicoDto.toString());
		Response<CadastroServicoDto> response = new Response<CadastroServicoDto>();

		// validarDadosExistentes(cadastroServicoDto, result);
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
	
	@PostMapping("alterar")
	public ResponseEntity<Response<CadastroServicoDto>> update(
			@Valid @RequestBody CadastroServicoDto cadastroServicoDto, BindingResult result)
			throws NoSuchAlgorithmException {

		log.info("Alterando Servico: {}", cadastroServicoDto.toString());
		Response<CadastroServicoDto> response = new Response<CadastroServicoDto>();

		// validarDadosExistentes(cadastroServicoDto, result);
		Servicos servico = this.converterDtoParaServico(cadastroServicoDto);

		if (result.hasErrors()) {
			log.error("Erro validando dados de alteração de servico: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.servicoService.persistir(servico);

		response.setData(this.converterCadastroServicoDto(servico));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/listar")
	public ResponseEntity<Response<List<CadastroServicoDto>>> listar()
			throws NoSuchAlgorithmException {
		List<CadastroServicoDto> servicoDtos = new ArrayList<>();
		 for (Servicos servico :  servicoService.listarServicos()) {
			 servicoDtos.add(converterCadastroServicoDto(servico));
			
		}
		Response<List<CadastroServicoDto>> response = new Response<List<CadastroServicoDto>>();
		response.setData(servicoDtos);
		return ResponseEntity.ok(response);
		 

	}
	
	@PostMapping("excluir")
	public ResponseEntity<Response<CadastroServicoDto>> excluir(
			  @RequestBody CadastroServicoDto cadastroServicoDto)
			throws NoSuchAlgorithmException {

		log.info("Deletando Servico: {}", cadastroServicoDto.toString());
		Response<CadastroServicoDto> response = new Response<CadastroServicoDto>();
		

		Servicos servico = this.converterDtoParaServico(cadastroServicoDto);
		servicoService.deletar(servico);

//		if (result.hasErrors()) {
//			log.error("Erro no método excluir: {}", result.getAllErrors());
//			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
//			return ResponseEntity.badRequest().body(response);
//		}

		 
		return ResponseEntity.ok(response);
	}

	/**
	 * Verifica se o servico já existe na base de dados.
	 * 
	 * @param cadastroServicoDto
	 * @param result
	 */
	// private void validarDadosExistentes(CadastroServicoDto
	// cadastroServicoDto, BindingResult result) {
	// this.servicoService.buscarPorNome(cadastroServicoDto.getNome())
	// .ifPresent(cli -> result.addError(new ObjectError("servico", "Servico já
	// existente.")));

	// }

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
		servico.setClienteId(cadastroServicoDto.getIdCliente());
		servico.setId(cadastroServicoDto.getId());
		servico.setEquipamento(equipamentoConverter.converterDtoParaEquipamento(cadastroServicoDto.getEquipamento()));
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
		cadastroServicoDto.setNomeCliente(clienteRepository.findById(servico.getClienteId()).get().getNome());
		cadastroServicoDto.setIdCliente(servico.getClienteId());
		cadastroServicoDto.setId(servico.getId());
		cadastroServicoDto.setEquipamento(equipamentoConverter.converterCadastroEquipamentoDto(servico.getEquipamento()));
		
		return cadastroServicoDto;
	}

}
