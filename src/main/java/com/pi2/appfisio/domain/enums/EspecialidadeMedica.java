package com.pi2.appfisio.domain.enums;

public enum EspecialidadeMedica {
	
	NEUROLOGIA(1, "Neurologia"),
	TRAUMATOLOGIA(2, "Traumatologia");
	
	private int cod;
	private String descricao;
	
	private EspecialidadeMedica(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EspecialidadeMedica toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(EspecialidadeMedica x : EspecialidadeMedica.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
