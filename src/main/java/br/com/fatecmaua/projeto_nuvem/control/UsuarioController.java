package br.com.fatecmaua.projeto_nuvem.control;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecmaua.projeto_nuvem.dto.UsuarioDTO;
import br.com.fatecmaua.projeto_nuvem.mapper.UsuarioMapperManual;
import br.com.fatecmaua.projeto_nuvem.model.Usuario;
import br.com.fatecmaua.projeto_nuvem.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioMapperManual mapperManual;
	
	@GetMapping("/todos")
	public ResponseEntity<List<UsuarioDTO>> listarTodos(){
		
		List<UsuarioDTO> lista = service.listarTodos()
				.stream()
				.map(mapperManual::toDTO)
				.collect(Collectors.toList());
		
		return ResponseEntity
				.status(HttpStatus.OK).body(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		return ResponseEntity
				.ok(service.buscarPorId(id));
	}
	
	@PutMapping("/{id}/atualizar")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id,
		      						@RequestBody Usuario usuario) {
		return ResponseEntity
				.ok(service.atualizarUsuario(id, usuario));
	}
	
	@DeleteMapping("/{id}/remover")
	public ResponseEntity<UsuarioDTO> removerUsuario(@PathVariable Long id) {
		mapperManual.toDTO(service.removerUsuario(id));
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/novo")
	public ResponseEntity<Usuario> inserirUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(service.inserirUsuario(usuario));
	}
	

}
