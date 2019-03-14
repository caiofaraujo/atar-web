package com.atar.web.dtos;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;



public class CadastroServicoDto {

	private Long id;
	@NotEmpty(message = "Descrição não pode ser vazio.")
	private String descricao;
	
	@NotEmpty(message = "Tipo não pode ser vazio.")
	private String tipo;
	
	@NotEmpty(message = "Observação não pode ser vazio.")
	private String observacao;
	
	private Date dtInicioServico;
	
	private Date dtFinalServico;
	
	private Long idCliente; 
	
	private String nomeCliente;

	public CadastroServicoDto() {
		
	}

	public Long getId() {
		return id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDtInicioServico() {
		return dtInicioServico;
	}

	public void setDtInicioServico(Date date) {
		this.dtInicioServico = date;
	}

	public Date getDtFinalServico() {
		return dtFinalServico;
	}

	public void setDtFinalServico(Date dtFinalServico) {
		this.dtFinalServico = dtFinalServico;
	}

	@Override
	public String toString() {
		return "Servico [id=" + id + ", descricao=" + descricao + ", tipo=" + tipo + "observacao" + observacao + "dtInicioServico" + dtInicioServico + "dtFinalServico" + dtFinalServico + "]";
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	
}
