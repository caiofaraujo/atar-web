package com.atar.web.dtos;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;



public class CadastroClienteDto {

	private Long id;
	
	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	private String nome;
	
	@NotEmpty(message = "Endereço não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Endereço deve conter entre 3 e 200 caracteres.")
	private String endereco;
	
	public CadastroClienteDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", endereco=" + endereco + "]";
	}
	
	
}
