package com.pi2.appfisio.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Patologia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="especialidade_id")
	private Especialidade especialidade;
	
	@JsonIgnore
	@OneToMany(mappedBy="patologia")
	private List<Anamnese> anamneses = new ArrayList<>();
	
	@OneToMany(mappedBy="patologia")
	private List<Conduta> condutas = new ArrayList<>();
	
	public Patologia() {
	}

	public Patologia(Integer id, String nome, Especialidade especialidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.especialidade = especialidade;
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

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public List<Anamnese> getAnamneses() {
		return anamneses;
	}

	public void setAnamneses(List<Anamnese> anamneses) {
		this.anamneses = anamneses;
	}

	public List<Conduta> getCondutas() {
		return condutas;
	}

	public void setCondutas(List<Conduta> condutas) {
		this.condutas = condutas;
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
		Patologia other = (Patologia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
