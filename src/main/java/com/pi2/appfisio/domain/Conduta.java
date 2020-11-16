	package com.pi2.appfisio.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Conduta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL, mappedBy="conduta")
	private Sessao sessao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="patologia_id")
	private Patologia patologia;
	
	@OneToMany(mappedBy="id.conduta")
	private Set<CondutaTecnica> tecnicas = new HashSet<>();
	
	public Conduta() {
	}

	public Conduta(Integer id, String nome, Sessao sessao, Patologia patologia) {
		super();
		this.id = id;
		this.nome = nome;
		this.sessao = sessao;
		this.patologia = patologia;
	}

	public Conduta(Integer id, String nome, Patologia patologia) {
		super();
		this.id = id;
		this.nome = nome;
		this.patologia = patologia;
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

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public Patologia getPatologia() {
		return patologia;
	}

	public void setPatologia(Patologia patologia) {
		this.patologia = patologia;
	}

	public Set<CondutaTecnica> getTecnicas() {
		return tecnicas;
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
		Conduta other = (Conduta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
