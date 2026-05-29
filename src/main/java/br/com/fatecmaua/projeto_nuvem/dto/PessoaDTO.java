package br.com.fatecmaua.projeto_nuvem.dto;

public class PessoaDTO {

	private Long id;
	private String nome;
	private String emailAlternativo;

	public PessoaDTO() {

	}

	public PessoaDTO(Long id, String nome, String emailAlternativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.emailAlternativo = emailAlternativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmailAlternativo() {
		return emailAlternativo;
	}

	public void setEmailAlternativo(String emailAlternativo) {
		this.emailAlternativo = emailAlternativo;
	}

}
