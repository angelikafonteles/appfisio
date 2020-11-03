package com.pi2.appfisio.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CondutaTecnicaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="condutaId")
	private Conduta conduta;
	
	@ManyToOne
	@JoinColumn(name="tecnicaId")
	private Tecnica tecnica;

	public Conduta getConduta() {
		return conduta;
	}

	public void setConduta(Conduta conduta) {
		this.conduta = conduta;
	}

	public Tecnica getTecnica() {
		return tecnica;
	}

	public void setTecnica(Tecnica tecnica) {
		this.tecnica = tecnica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conduta == null) ? 0 : conduta.hashCode());
		result = prime * result + ((tecnica == null) ? 0 : tecnica.hashCode());
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
		CondutaTecnicaPK other = (CondutaTecnicaPK) obj;
		if (conduta == null) {
			if (other.conduta != null)
				return false;
		} else if (!conduta.equals(other.conduta))
			return false;
		if (tecnica == null) {
			if (other.tecnica != null)
				return false;
		} else if (!tecnica.equals(other.tecnica))
			return false;
		return true;
	}
	

}
