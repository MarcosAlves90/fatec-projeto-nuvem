package br.com.fatecmaua.projeto_nuvem.model;

public enum StatusUsuarioEnum {

	ATIVO("Ativo"), INATIVO("Inativo"), 
	BLOQUEADO("Bloqueado"), PENDENTE("Pendente");
	
	private String descricao;
	
	private StatusUsuarioEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
