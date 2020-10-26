package com.pi2.appfisio.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Anamnese implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private Date dataDaFicha;
	private String pressaoArterial;
	private String frequenciaCardiaca;
	private String historicoFamiliar;
	private String historiaPatologiaPregressa;
	private String historicoDoencaAtual;
	private String queixaPrincipal;
	private String observacoes;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="paciente_id")
	private Paciente paciente;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="patologia_id")
	private Patologia patologia;

	public Anamnese(String id, Date dataDaFicha, String pressaoArterial, String frequenciaCardiaca,
			String historicoFamiliar, String historiaPatologiaPregressa, String historicoDoencaAtual,
			String queixaPrincipal, String observacoes, Paciente paciente, Patologia patologia) {
		super();
		this.id = id;
		this.dataDaFicha = dataDaFicha;
		this.pressaoArterial = pressaoArterial;
		this.frequenciaCardiaca = frequenciaCardiaca;
		this.historicoFamiliar = historicoFamiliar;
		this.historiaPatologiaPregressa = historiaPatologiaPregressa;
		this.historicoDoencaAtual = historicoDoencaAtual;
		this.queixaPrincipal = queixaPrincipal;
		this.observacoes = observacoes;
		this.paciente = paciente;
		this.patologia = patologia;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDataDaFicha() {
		return dataDaFicha;
	}

	public void setDataDaFicha(Date dataDaFicha) {
		this.dataDaFicha = dataDaFicha;
	}

	public String getPressaoArterial() {
		return pressaoArterial;
	}

	public void setPressaoArterial(String pressaoArterial) {
		this.pressaoArterial = pressaoArterial;
	}

	public String getFrequenciaCardiaca() {
		return frequenciaCardiaca;
	}

	public void setFrequenciaCardiaca(String frequenciaCardiaca) {
		this.frequenciaCardiaca = frequenciaCardiaca;
	}

	public String getHistoricoFamiliar() {
		return historicoFamiliar;
	}

	public void setHistoricoFamiliar(String historicoFamiliar) {
		this.historicoFamiliar = historicoFamiliar;
	}

	public String getHistoriaPatologiaPregressa() {
		return historiaPatologiaPregressa;
	}

	public void setHistoriaPatologiaPregressa(String historiaPatologiaPregressa) {
		this.historiaPatologiaPregressa = historiaPatologiaPregressa;
	}

	public String getHistoricoDoencaAtual() {
		return historicoDoencaAtual;
	}

	public void setHistoricoDoencaAtual(String historicoDoencaAtual) {
		this.historicoDoencaAtual = historicoDoencaAtual;
	}

	public String getQueixaPrincipal() {
		return queixaPrincipal;
	}

	public void setQueixaPrincipal(String queixaPrincipal) {
		this.queixaPrincipal = queixaPrincipal;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Patologia getPatologia() {
		return patologia;
	}

	public void setPatologia(Patologia patologia) {
		this.patologia = patologia;
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
		Anamnese other = (Anamnese) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
