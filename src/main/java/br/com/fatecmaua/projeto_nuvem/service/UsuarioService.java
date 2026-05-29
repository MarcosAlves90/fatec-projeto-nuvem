package br.com.fatecmaua.projeto_nuvem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fatecmaua.projeto_nuvem.model.Usuario;
import br.com.fatecmaua.projeto_nuvem.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repU;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private CachingService cacheU;
	
	//Buscar todos
	public List<Usuario> listarTodos(){
		return cacheU.findAll();
	}
	
	//Buscar por ID
	public Usuario buscarPorId(Long id) {
		return cacheU.findById(id).orElseThrow(null);
	}
	
	//Atualizar
	public Usuario atualizarUsuario(Long id, Usuario usuario) {
		Optional<Usuario> op = cacheU.findById(id);
		
		if(op.isPresent()) {
			Usuario usuario_banco = op.get();
			if(usuario.getSenha() != null) {
				usuario.setSenha(encoder.encode(usuario.getSenha()));
			}
			usuario_banco.transferirUsuario(usuario);
			repU.save(usuario_banco);
			cacheU.removerCache();
			return usuario_banco;
		} else {
			return null;
		}
		
	}
	
	//Remover
	public Usuario removerUsuario(Long id) {
		
		Usuario usuario = cacheU.findById(id).orElseThrow(null);
		
		if(usuario != null) {
			repU.deleteById(id);
			cacheU.removerCache();
			return usuario;
		} else {
			return null;
		}
		
	}
	
	//Inserir
	public Usuario inserirUsuario(Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		repU.save(usuario);
		cacheU.removerCache();
		return usuario;	
	}
	
	

}
