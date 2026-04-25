package br.com.fatecmaua.projeto_nuvem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatecmaua.projeto_nuvem.model.InstanciaVirtual;
import br.com.fatecmaua.projeto_nuvem.model.StatusEnum;
import br.com.fatecmaua.projeto_nuvem.repository.InstanciaVirtualRepository;

@Service
public class InstanciaVirtualService {

	private Double PESO_V_CPU = 0.3;
	private Double PESO_ARMAZENAMENTO_GB = 0.005;
	private Double PESO_MEM_RAM_GB = 0.15;

	@Autowired
	private InstanciaVirtualRepository instanciaRepository;


	public InstanciaVirtual criar(InstanciaVirtual iv) {
		iv.setStatus(StatusEnum.ATIVA);
		iv.setCustoHora(calcularElasticidadeCustoHora(iv));
		instanciaRepository.save(iv);
		return iv;
	}
	
	public List<InstanciaVirtual> buscarTodas(){
		return instanciaRepository.findAll();
	}


	public InstanciaVirtual buscarPorID(Long id) {

		Optional<InstanciaVirtual> op = instanciaRepository.findById(id);

		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}

	}
	
	public String ativar(Long id) {

		InstanciaVirtual iv = buscarPorID(id);

		if (iv != null && iv.getStatus() != StatusEnum.ATIVA) {
			iv.setStatus(StatusEnum.ATIVA);
			instanciaRepository.save(iv);
			return "A instância " + iv.getNome() + " está " + 
			iv.getStatus().getDescricao();
		}

		return null;

	}

	public String inativar(Long id) {

		InstanciaVirtual iv = buscarPorID(id);

		if (iv != null && iv.getStatus() != StatusEnum.INATIVA) {
			iv.setStatus(StatusEnum.INATIVA);
			instanciaRepository.save(iv);
			return "A instância " + iv.getNome() + " está " +
			iv.getStatus().getDescricao();
		}

		return null;

	}

	public Double calcularElasticidadeCustoHora(InstanciaVirtual iv) {
		return (iv.getVirtualCpu() * PESO_V_CPU) + (iv.getArmazenamentoGb() * PESO_ARMAZENAMENTO_GB)
				+ (iv.getMemoriaRamGb() * PESO_MEM_RAM_GB);
	}

}
