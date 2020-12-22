package com.pi2.appfisio.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CondutaTecnica implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private CondutaTecnicaPK id = new CondutaTecnicaPK();
	private String descricao;
	
	public CondutaTecnica() {
	}

	public CondutaTecnica(Conduta conduta, Tecnica tecnica, String descricao) {
		super();
		id.setConduta(conduta);
		id.setTecnica(tecnica);
		this.descricao = descricao;
	}
	
	@JsonIgnore
	public Conduta getConduta() {
		return id.getConduta();
	}
	
	public void setConduta(Conduta conduta) {
		id.setConduta(conduta);
	}
	
	public Tecnica getTecnica() {
		return id.getTecnica();
	}
	
	public void setTecnica(Tecnica tecnica) {
		id.setTecnica(tecnica);
	}

	public CondutaTecnicaPK getId() {
		return id;
	}

	public void setId(CondutaTecnicaPK id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		CondutaTecnica other = (CondutaTecnica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
