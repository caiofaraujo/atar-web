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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "TB_CLIENTES_CLIE")
public class Clientes implements Serializable {

	private static final long serialVersionUID = 3960436649365666213L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE_CLIE", nullable = false)
	private Long id;
	
	@Column(name = "DS_NOME_CLIE", nullable = false)
	private String nome;
	
	@Column(name = "DS_ENDERECO_CLIE", nullable = false)
	private String endereco;
	
	@Column(name = "DH_INATIVACAO_CLIE")
	private Date dtInativacao;
	
	@Column(name = "DH_CADASTRO_CLIE")
	private Date dtCadastro;
	
	@Column(name = "NR_TELEFONE_CLIE")
	private String nrTelefone;
	
//	@OneToMany(cascade= CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE)  
//	@JoinColumn(name = "ID_SERVICO_SERV")
//	private List<Servicos> servicos;
	
//	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "ID_CLIENTE_CLIE")
//    private List<Clientes> clientes;
	
//	@ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="TB_CLIENTESEQUIPAMENTO_CLEQ", joinColumns=
//    {@JoinColumn(name="ID_CLIENTE_CLIE")}, inverseJoinColumns=
//      {@JoinColumn(name="ID_EQUIPAMENTO_EQUI")})
//	private List<Equipamentos> equipamentos;

	public Clientes() {

	}

//	public List<Equipamentos> getEquipamentos() {
//		return equipamentos;
//	}
//
//	public void setEquipamentos(List<Equipamentos> equipamentos) {
//		this.equipamentos = equipamentos;
//	}

//	public List<Clientes> getClientes() {
//		return clientes;
//	}
//
//	public void setClientes(List<Clientes> clientes) {
//		this.clientes = clientes;
//	}

//	public List<Servicos> getServicos() {
//		return servicos;
//	}
//
//	public void setServicos(List<Servicos> servicos) {
//		this.servicos = servicos;
//	}
	
//	public List<Equipamentos> getEquipamentos() {
//		return equipamentos;
//	}
//
//	public void setEquipamentos(List<Equipamentos> equipamentos) {
//		this.equipamentos = equipamentos;
//	}

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
	
	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
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
