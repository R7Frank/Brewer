package com.algaworks.brewer.model;

public enum Origem {
	
	NACIONAL("Nacional"),
	INTERNACIONAL("Internacional");
	
	public String descricao;
	
	Origem(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
		
	}

}
