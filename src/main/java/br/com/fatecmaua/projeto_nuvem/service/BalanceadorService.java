package br.com.fatecmaua.projeto_nuvem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatecmaua.projeto_nuvem.model.InstanciaVirtual;
import br.com.fatecmaua.projeto_nuvem.model.StatusEnum;
import br.com.fatecmaua.projeto_nuvem.repository.InstanciaVirtualRepository;

@Service
public class BalanceadorService {

	@Autowired
	private InstanciaVirtualRepository repository;

	private int iterador = 0;

	public InstanciaVirtual balancearCarga() {

		List<InstanciaVirtual> ativas =
		repository.findByStatus(StatusEnum.ATIVA);
		
		if(ativas.isEmpty()) {
			return null;
		}
		
		if(iterador >= ativas.size()) {
			iterador = 0;
		}
		
		InstanciaVirtual iv = ativas.get(iterador);
		
		if(iv.getQtdRequisicoes() == null) {
			iv.setQtdRequisicoes(0);
		}
		
		iv.setQtdRequisicoes
		(iv.getQtdRequisicoes() + 1);
		
		repository.save(iv);
		
		iterador++;
		
		return iv;
		
	}

}
