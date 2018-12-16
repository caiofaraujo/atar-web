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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "TB_SERVICOS_SERV")

public class Servicos implements Serializable {

	private static final long serialVersionUID = 3960436649365666213L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_SERVICO_SERV", nullable = false)
	private Long id;
	
	@Column(name = "DS_TIPO_SERV", nullable = false)
	private String tipo;
	
	@Column(name = "DS_DESCRICAO_SERV", nullable = false)
	private String descricao;
	
	@Column(name = "DS_OBSERVACAO_SERV", nullable = false)
	private String observacao;
	
	@Column(name = "DH_INICIO_SERV", nullable = false)
	private Date dtInicioServ;
	
	@Column(name = "DH_FINAL_SERV", nullable = false)
	private Date dtFinalServ;
	
	@Column(name = "DH_INATIVACAO_SERV", nullable = false)
	private Date dtInativacao;
	
	@Column(name = "DH_CADASTRO_SERV", nullable = false)
	private Date dtCadastro;	
	
	@ManyToOne()
	@JoinColumn(name = "ID_CLIENTE_CLIE")
	private Clientes clientes;

	public Servicos() {
		
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}

	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDtInicioServ() {
		return dtInicioServ;
	}

	public void setDtInicioServ(Date dtInicioServ) {
		this.dtInicioServ = dtInicioServ;
	}

	public Date getDtFinalServ() {
		return dtFinalServ;
	}

	public void setDtFinalServ(Date dtFinalServ) {
		this.dtFinalServ = dtFinalServ;
	}

	public Date getDtInativacao() {
		return dtInativacao;
	}

	public void setDtInativacao(Date dtInativacao) {
		this.dtInativacao = dtInativacao;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
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
