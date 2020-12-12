package com.pi2.appfisio.dto;

import java.io.Serializable;
import java.time.Instant;

import com.pi2.appfisio.domain.Sessao;

public class SessaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Instant instante;
	private String observacoes;
	
	public SessaoDTO() {
	}
	
	public SessaoDTO(Sessao obj) {
		id = obj.getId();
		instante = obj.getInstante();
		observacoes = obj.getObservacoes();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getInstante() {
		return instante;
	}

	public void setInstante(Instant instante) {
		this.instante = instante;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
}
