package br.com.fatecmaua.projeto_nuvem.model;

public enum StatusEnum {
	
	ATIVA("Ativa"), INATIVA("Inativa"), 
	SUSPENSA("Suspensa"), SOB_INVESTIGACAO("Sob Investigação"),
	EM_ATUALIZACAO("Em Atualização");
	
	StatusEnum(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return this.descricao;
	}
	

}
