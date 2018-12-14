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
@Table(name = "tb_equipamentos_equi")


public class Equipamentos implements Serializable {

	private static final long serialVersionUID = 3960436649365666213L;
	
	private Long id;
	private String marca;
	private String modelo;
	private Date dtInativacao;
	private Date dtCadastro;
	private List<Equipamentos> equipamentos;
	
	public Equipamentos() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ds_marca_equi", nullable = false)
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	@Column(name = "ds_modelo_equi", nullable = false)
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Column(name = "dh_inativacao_equi", nullable = false)
	public Date getDtInativacao() {
		return dtInativacao;
	}

	public void setDtInativacao(Date dtInativacao) {
		this.dtInativacao = dtInativacao;
	}

	@Column(name = "dh_cadastro_equi", nullable = false)
	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public List<Equipamentos> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamentos> equipamentos) {
		this.equipamentos = equipamentos;
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
		return "Equipamento [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", dtCadastro=" + dtCadastro + "]";
	}
	
	
}
