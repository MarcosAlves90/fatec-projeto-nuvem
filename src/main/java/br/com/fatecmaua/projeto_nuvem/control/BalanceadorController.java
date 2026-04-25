package br.com.fatecmaua.projeto_nuvem.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecmaua.projeto_nuvem.model.InstanciaVirtual;
import br.com.fatecmaua.projeto_nuvem.service.BalanceadorService;

@RestController
@RequestMapping("/balanceador")
public class BalanceadorController {
	
	@Autowired
	private BalanceadorService service;
	
	@GetMapping()
	public String balancearCarga() {
		
		InstanciaVirtual iv = service.balancearCarga();
		
		if(iv == null) {
			return "Não existem instâncias ativas "
				 + "neste momento";
		} else {
			return "A requisição/sessão foi atribuída "
				+ "para instância:\n"
				+ "Nome: " + iv.getNome() + "\n"
				+ "SO: " + iv.getSistemaOperacional() + "\n"
				+ "Status: " + iv.getStatus().getDescricao()+"\n"
				+ "Qtd de Requisições: " + iv.getQtdRequisicoes();
		}
		
	}

}
