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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atar.web.dtos.CadastroClienteDto;
import com.atar.web.entities.Clientes;
import com.atar.web.response.Response;
import com.atar.web.services.ClienteService;

@RestController
@RequestMapping("/atar/cadastrar-cliente")
@CrossOrigin(origins = "*")
public class CadastroClienteController {

	private static final Logger log = LoggerFactory.getLogger(CadastroClienteController.class);

	@Autowired
	private ClienteService clienteService;

	public CadastroClienteController() {

	}

	@GetMapping
	@RequestMapping("/listar-cliente")
	public ResponseEntity<Response<List<CadastroClienteDto>>> listarClientes() throws NoSuchAlgorithmException {
		log.info("Listando Clientes..");
		Response<List<CadastroClienteDto>> response = new Response<List<CadastroClienteDto>>();
		List<Clientes> lst = clienteService.listarClientes();
		List<CadastroClienteDto> lstDto = new ArrayList<>();
		for (Clientes clientes : lst) {
			lstDto.add(converterCadastroClienteDto(clientes));
		}			
		 response.setData(lstDto);		
		return ResponseEntity.ok(response);
	}

	/**
	 * Cadastra um cliente no sistema.
	 * 
	 * @param ClienteDto
	 * @param result
	 * @return ResponseEntity<Response<ClienteDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroClienteDto>> cadastrar(
			@Valid @RequestBody CadastroClienteDto cadastroClienteDto, BindingResult result)
			throws NoSuchAlgorithmException {

		log.info("Cadastrando Cliente: {}", cadastroClienteDto.toString());
		Response<CadastroClienteDto> response = new Response<CadastroClienteDto>();

		validarDadosExistentes(cadastroClienteDto, result);
		Clientes cliente = this.converterDtoParaCliente(cadastroClienteDto);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.clienteService.persistir(cliente);

		response.setData(this.converterCadastroClienteDto(cliente));
		return ResponseEntity.ok(response);
	}

	/**
	 * Verifica se o cliente já existe na base de dados.
	 * 
	 * @param cadastroClienteDto
	 * @param result
	 */
	private void validarDadosExistentes(CadastroClienteDto cadastroClienteDto, BindingResult result) {
		this.clienteService.buscarPorNome(cadastroClienteDto.getNome())
				.ifPresent(cli -> result.addError(new ObjectError("cliente", "Cliente já existente.")));

	}

	/**
	 * Converte os dados do DTO para cliente.
	 * 
	 * @param cadastroClienteDto
	 * @return cliente
	 */
	private Clientes converterDtoParaCliente(CadastroClienteDto cadastroClienteDto) {
		Clientes cliente = new Clientes();
		cliente.setNome(cadastroClienteDto.getNome());
		cliente.setEndereco(cadastroClienteDto.getEndereco());
		cliente.setNrTelefone(cadastroClienteDto.getTelefone());

		return cliente;
	}

	/**
	 * Popula o DTO de cadastro com os dados do cliente
	 * 
	 * @param cliente
	 * @return cadastroClienteDto
	 */
	private CadastroClienteDto converterCadastroClienteDto(Clientes cliente) {
		CadastroClienteDto cadastroClienteDto = new CadastroClienteDto();
		cadastroClienteDto.setId(cliente.getId());
		cadastroClienteDto.setNome(cliente.getNome());
		cadastroClienteDto.setEndereco(cliente.getEndereco());
		cadastroClienteDto.setTelefone(cliente.getNrTelefone());
		return cadastroClienteDto;
	}

}
