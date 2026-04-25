package br.com.fatecmaua.projeto_nuvem.control;

import java.util.List;

import org.hibernate.dialect.function.xml.XmlTableSetReturningFunctionTypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecmaua.projeto_nuvem.model.InstanciaVirtual;
import br.com.fatecmaua.projeto_nuvem.service.InstanciaVirtualService;

@RestController
@RequestMapping("/instancia-virtual")
public class InstanciaVirtualController {

	@Autowired
	private InstanciaVirtualService service;

	// criar
	@PostMapping(value = "/nova")
	public InstanciaVirtual criarInstancia(@RequestBody 
			InstanciaVirtual nova_instancia) {
		return service.criar(nova_instancia);
	}
	// buscarTodas
	@GetMapping(value = "/todas")
	public List<InstanciaVirtual> buscarTodas(){
		return service.buscarTodas();
	}
	// buscarPorID
	@GetMapping(value = "/{id}")
	public InstanciaVirtual buscarPorID(@PathVariable Long id) {
		return service.buscarPorID(id);
	}
	// ativar
	@PatchMapping(value = "/{id}/ativar")
	public String ativar(@PathVariable Long id) {
		return service.ativar(id);
	}
	// inativar
	@PatchMapping(value = "/{id}/inativar")
	public String inativar(@PathVariable Long id) {
		return service.inativar(id);
	}
	// calcularElasticidadeCustoHora
	@PostMapping(value = "/simular-elasticidade")
	public Double 
	calcularElasticidadeCustoHora(@RequestBody InstanciaVirtual iv) {
		return service.calcularElasticidadeCustoHora(iv);
	}

}
