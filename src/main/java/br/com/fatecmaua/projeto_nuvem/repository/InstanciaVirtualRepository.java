package br.com.fatecmaua.projeto_nuvem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fatecmaua.projeto_nuvem.model.InstanciaVirtual;
import br.com.fatecmaua.projeto_nuvem.model.StatusEnum;

public interface InstanciaVirtualRepository 
extends JpaRepository<InstanciaVirtual,Long> {

	//Não-automatizado
	@Query(value = "from InstanciaVirtual iv where "
			+ "iv.status = :status")
	public List<InstanciaVirtual> 
	buscarPorStatus(StatusEnum status);
	
	//Automatizado
	List<InstanciaVirtual> 
	findByStatus(StatusEnum status);
	
}
