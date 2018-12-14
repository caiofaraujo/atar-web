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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "tb_servicos_serv")

public class Servicos implements Serializable {

	private static final long serialVersionUID = 3960436649365666213L;
	
	private Long id;
	private String tipo;
	private String descricao;
	private String observacao;
	private Date dtInicioServ;
	private Date dtFinalServ;
	private Date dtInativacao;
	private Date dtCadastro;	
	private Clientes clientes;	
	private List<Servicos> servicos;
	
	
	public Servicos() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ds_tipo_serv", nullable = false)
	public String getTipo() {
		return tipo;
	}

	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Column(name = "ds_descricao_serv", nullable = false)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "ds_observacao_serv", nullable = false)
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "dh_inicio_serv", nullable = false)
	public Date getDtInicioServ() {
		return dtInicioServ;
	}

	public void setDtInicioServ(Date dtInicioServ) {
		this.dtInicioServ = dtInicioServ;
	}

	@Column(name = "dh_final_serv", nullable = false)
	public Date getDtFinalServ() {
		return dtFinalServ;
	}

	public void setDtFinalServ(Date dtFinalServ) {
		this.dtFinalServ = dtFinalServ;
	}

	@Column(name = "dh_inativacao_serv", nullable = false)
	public Date getDtInativacao() {
		return dtInativacao;
	}

	public void setDtInativacao(Date dtInativacao) {
		this.dtInativacao = dtInativacao;
	}

	@Column(name = "dh_cadastro_serv", nullable = false)
	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public List<Servicos> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servicos> servicos) {
		this.servicos = servicos;
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
		return "Servico [id=" + id + ", tipo=" + tipo + ", descricao=" + descricao + ", observacao=" + observacao + ", dtInicioServ" + dtInicioServ + ", dtFinalServ" + dtFinalServ + ", dtCadastro" + dtCadastro + "]";
	}
}
