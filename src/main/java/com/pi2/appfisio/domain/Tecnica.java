package com.pi2.appfisio.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi2.appfisio.domain.enums.TecnicaTerapeutica;

@Entity
public class Tecnica implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer nome;
	
	@JsonIgnore
	@OneToMany(mappedBy="id.tecnica")
	private Set<CondutaTecnica> tecnicas = new HashSet<>();
	
	public Tecnica() {
	}

	public Tecnica(Integer id, TecnicaTerapeutica nome) {
		super();
		this.id = id;
		this.nome = nome.getCod();
	}
	
	@JsonIgnore
	public List<Conduta> getCondutas(){
		List<Conduta> lista = new ArrayList<>();
		for(CondutaTecnica n : tecnicas) {
			lista.add(n.getConduta());
		}
		return lista;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TecnicaTerapeutica getTecnica() {
		return TecnicaTerapeutica.toEnum(nome);
	}

	public void setTecnica(TecnicaTerapeutica nome) {
		this.nome = nome.getCod();
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
		Tecnica other = (Tecnica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
