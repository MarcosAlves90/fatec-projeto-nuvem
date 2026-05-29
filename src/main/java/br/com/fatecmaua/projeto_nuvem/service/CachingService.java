package br.com.fatecmaua.projeto_nuvem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.fatecmaua.projeto_nuvem.model.Usuario;
import br.com.fatecmaua.projeto_nuvem.repository.UsuarioRepository;

@Service
public class CachingService {
	
	@Autowired
	private UsuarioRepository repU;
	
	//findAll
	@Cacheable(value = "buscarTodosUsuarios")
	public List<Usuario> findAll(){
		return repU.findAll();
	}
	
	//findById
	@Cacheable(value = "buscarUsuarioPorId", key = "#id")
	public Optional<Usuario> findById(Long id) {
		return repU.findById(id);
	}
	
	@CacheEvict(value = {"buscarTodosUsuarios"
			,"buscarUsuarioPorId"}, allEntries = true)
	public void removerCache() {
		System.out.println("Removendo cache!");
	}
	

}






