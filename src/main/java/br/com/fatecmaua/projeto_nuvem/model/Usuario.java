package br.com.fatecmaua.projeto_nuvem.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String senha;
	private String role;
	@Enumerated(EnumType.STRING)
	private StatusUsuarioEnum status;
	@ManyToOne
	@JoinColumn(name = "fk_pessoa")
	private Pessoa pessoa;

	public Usuario() {

	}

	public Usuario(Long id, String email, String senha, String role, StatusUsuarioEnum status, Pessoa pessoa) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.role = role;
		this.status = status;
		this.pessoa = pessoa;
	}
	
	public void transferirUsuario(Usuario usuario) {
		setEmail(usuario.getEmail());
		setSenha(usuario.getSenha());
		setRole(usuario.getRole());
		setStatus(usuario.getStatus());
		setPessoa(usuario.getPessoa());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public StatusUsuarioEnum getStatus() {
		return status;
	}

	public void setStatus(StatusUsuarioEnum status) {
		this.status = status;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
