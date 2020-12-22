package com.pi2.appfisio.dto;

import java.io.Serializable;
import java.util.Date;

import com.pi2.appfisio.domain.Sessao;

public class SessaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date data;
	private String observacoes;
	
	public SessaoDTO() {
	}
	
	public SessaoDTO(Sessao obj) {
		id = obj.getId();
		data = obj.getData();
		observacoes = obj.getObservacoes();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
}
