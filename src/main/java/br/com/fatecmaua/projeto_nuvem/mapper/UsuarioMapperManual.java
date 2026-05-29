package br.com.fatecmaua.projeto_nuvem.mapper;

import org.springframework.stereotype.Component;
import br.com.fatecmaua.projeto_nuvem.dto.UsuarioDTO;
import br.com.fatecmaua.projeto_nuvem.model.Usuario;

@Component
public class UsuarioMapperManual {
	
	public UsuarioDTO toDTO(Usuario usuario) {
		UsuarioDTO dto = new UsuarioDTO(usuario);
		return dto;
	}

}
