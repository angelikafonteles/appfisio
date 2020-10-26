package com.pi2.appfisio.domain.enums;

public enum TecnicaTerapeutica {
	
	ALOGAMENTOMEMBROINFERIOR(1, "Alongamento membro inferior"),
	ALOGAMENTOMEMBROSUPERIOR(2, "Alongamento membro superior"),
	ALOGAMENTOCADEIALATERIAL(3, "Alongamento cadeia lateral"),
	ALOGAMENTOCADEIAPOSTERIOR(4, "Alongamento cadeia posterior"),
	ALOGAMENTOCADEIAANTERIOR(5, "Alogamento cadeia anterior"),
	ALONGAMENTOPASSIVODEMEMBROSSUPERIORES(6, "Alongamento passivo membros superiores"),
	ALONGAMENTOPASSIVODEMEMBROSINFERIORES(7, "Alongamento passivo membros inferiores"),
	ALONGAMENTODAMUSCULATURARESPIRATORIA(8, "Alongamento da musculatura respiratória"),
	CONSCIENTIZACAORESPIRATORIA(9, "Conscientização respiratória"),
	EAPARTESANAL(10, "EAP artesanal"),
	EXERCICIOSCALISTENICOS(11, "Exercícios calistênicos"),
	ONDASCURTAS(12, "Ondas curtas"),
	TENSCONVENCIONAL(13, "TENS convencional"),
	TENSBURST(14, "TENS burst");
	
	private int cod;
	private String descricao;
	
	private TecnicaTerapeutica(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TecnicaTerapeutica toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TecnicaTerapeutica x : TecnicaTerapeutica.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
