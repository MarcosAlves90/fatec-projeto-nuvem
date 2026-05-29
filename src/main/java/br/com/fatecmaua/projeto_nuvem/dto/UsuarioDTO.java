package br.com.fatecmaua.projeto_nuvem.dto;

import br.com.fatecmaua.projeto_nuvem.model.StatusUsuarioEnum;
import br.com.fatecmaua.projeto_nuvem.model.Usuario;

public class UsuarioDTO {

	private String email;
	private StatusUsuarioEnum status;
	private PessoaDTO pessoaDTO;

	public UsuarioDTO() {

	}

	public UsuarioDTO(String email, StatusUsuarioEnum status, PessoaDTO pessoaDTO) {
		super();
		this.email = email;
		this.status = status;
		this.pessoaDTO = pessoaDTO;
	}
	
	public UsuarioDTO(Usuario usuario) {
		setEmail(usuario.getEmail());
		setStatus(usuario.getStatus());
		setPessoaDTO(new PessoaDTO());
		getPessoaDTO().setNome(usuario.getPessoa()
				.getNome());
		getPessoaDTO().setEmailAlternativo(usuario
				.getPessoa().getEmailAlternativo());
		getPessoaDTO().setId(usuario.getPessoa()
				.getId());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StatusUsuarioEnum getStatus() {
		return status;
	}

	public void setStatus(StatusUsuarioEnum status) {
		this.status = status;
	}

	public PessoaDTO getPessoaDTO() {
		return pessoaDTO;
	}

	public void setPessoaDTO(PessoaDTO pessoaDTO) {
		this.pessoaDTO = pessoaDTO;
	}

}
