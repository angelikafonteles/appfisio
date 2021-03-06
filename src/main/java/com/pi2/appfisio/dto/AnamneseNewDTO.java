package com.pi2.appfisio.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class AnamneseNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;


	private Integer id;
	private Date dataDaFicha;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String pressaoArterial;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String frequenciaCardiaca;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String historicoFamiliar;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String historiaPatologiaPregressa;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String historicoDoencaAtual;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String queixaPrincipal;
	private String observacoes;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String patologia;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String especialidade;
	
	private Integer pacienteId;
	
	public AnamneseNewDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getPatologia() {
		return patologia;
	}

	public void setPatologia(String patologia) {
		this.patologia = patologia;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public Integer getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Integer pacienteId) {
		this.pacienteId = pacienteId;
	}

}
