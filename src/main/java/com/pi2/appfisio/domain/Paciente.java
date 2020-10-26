package com.pi2.appfisio.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.pi2.appfisio.domain.enums.Genero;

@Entity
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String cpf;
	private String nome;
	private Date dataNascimento;
	private Integer genero;
	private String email;
	private String profissao;
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@OneToMany(mappedBy="paciente")
	private List<Endereco> enderecos = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy="paciente")
	private List<Anamnese> anamneses = new ArrayList<>();
	
	public Paciente() {
	}

	public Paciente(Integer id, String cpf, String nome, Date dataNascimento, Genero genero, String email, String profissao, Usuario usuario) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.genero = genero.getCod();
		this.email = email;
		this.profissao = profissao;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Genero getGenero() {
		return Genero.toEnum(genero);
	}

	public void setGenero(Genero genero) {
		this.genero = genero.getCod();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Anamnese> getAnamneses() {
		return anamneses;
	}

	public void setAnamneses(List<Anamnese> anamneses) {
		this.anamneses = anamneses;
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
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
