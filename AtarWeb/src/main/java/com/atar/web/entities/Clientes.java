package com.atar.web.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "tb_clientes_cli")
public class Clientes implements Serializable {

	private static final long serialVersionUID = 3960436649365666213L;
	
	private Long id;
	private String nome;
	private String endereco;
	private Date dtInativacao;
	private Date dtCadastro;
	private List<Clientes> clientes;
	
	public Clientes() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ds_nome_clie", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "ds_endereco_clie", nullable = false)
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Column(name = "dh_inativacao_clie", nullable = false)
	public Date getDtInativacao() {
		return dtInativacao;
	}

	public void setDtInativacao(Date dtInativacao) {
		this.dtInativacao = dtInativacao;
	}
	
	@Column(name = "dh_cadastro_clie", nullable = false)
	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	
	public List<Clientes> getClientes() {
		return clientes;
	}

	public void setClientes(List<Clientes> clientes) {
		this.clientes = clientes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dtCadastro = atual;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", dtCadastro=" + dtCadastro + "]";
	}
	
}
