package com.atar.web.dtos;

import javax.validation.constraints.NotEmpty;

public class CadastroEquipamentoDto {

	private Long id;

	@NotEmpty(message = "Marca não pode ser vazio.")
	private String marca;
	
	@NotEmpty(message = "Modelo não pode ser vazio.")
	private String modelo;
	
	public CadastroEquipamentoDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	@Override
	public String toString() {
		return "Equipamento [id=" + id + ", marca=" + marca + ", modelo=" + modelo + "]";
	}
}
