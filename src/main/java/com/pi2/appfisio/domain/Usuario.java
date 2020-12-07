package com.pi2.appfisio.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String nome;
	private String cpf;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNascimento;
	private String orgaoDeClasse;
	
	@JsonIgnore
	@OneToOne
	@MapsId
	private Login login;
	
	@OneToMany(mappedBy="usuario")
	private List<Paciente> pacientes = new ArrayList<>();
	
	public Usuario() {
	}

	public Usuario(Integer id, String nome, String cpf, Date dataNascimento, String orgaoDeClasse, Login login) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.orgaoDeClasse = orgaoDeClasse;
		this.login = login;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getOrgaoDeClasse() {
		return orgaoDeClasse;
	}

	public void setOrgaoDeClasse(String orgaoDeClasse) {
		this.orgaoDeClasse = orgaoDeClasse;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
