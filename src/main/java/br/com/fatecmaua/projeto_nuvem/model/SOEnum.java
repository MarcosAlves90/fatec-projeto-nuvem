package br.com.fatecmaua.projeto_nuvem.model;

public enum SOEnum {
	
	LINUX_UBUNTU("Ubuntu"), WINDOWS_11("MS Windows 11") ,
	WINDOWS_10("MS Windows 10"), MAC_OS("Mac OS"), 
	LINUX_CENT_OS_7("CentOS 7"),LINUX_CENT_OS_8("CentOS 8");
	
	SOEnum(String nova_descricao){
		this.descricao = nova_descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return this.descricao;
	}

}
