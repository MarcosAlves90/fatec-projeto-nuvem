package br.com.fatecmaua.projeto_nuvem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_instancia_virtual")
public class InstanciaVirtual {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer virtualCpu;
	private Double armazenamentoGb;
	private Double memoriaRamGb;
	@Enumerated(EnumType.STRING)
	private SOEnum sistemaOperacional;
	private Double custoHora;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	private Integer qtdRequisicoes;

	public InstanciaVirtual() {

	}

	public InstanciaVirtual(Long id, String nome, Integer virtualCpu, Double armazenamentoGb, Double memoriaRamGb,
			SOEnum sistemaOperacional, Double custoHora, StatusEnum status, Integer qtdRequisicoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.virtualCpu = virtualCpu;
		this.armazenamentoGb = armazenamentoGb;
		this.memoriaRamGb = memoriaRamGb;
		this.sistemaOperacional = sistemaOperacional;
		this.custoHora = custoHora;
		this.status = status;
		this.qtdRequisicoes = qtdRequisicoes;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getVirtualCpu() {
		return virtualCpu;
	}

	public void setVirtualCpu(Integer virtualCpu) {
		this.virtualCpu = virtualCpu;
	}

	public Double getArmazenamentoGb() {
		return armazenamentoGb;
	}

	public void setArmazenamentoGb(Double armazenamentoGb) {
		this.armazenamentoGb = armazenamentoGb;
	}

	public Double getMemoriaRamGb() {
		return memoriaRamGb;
	}

	public void setMemoriaRamGb(Double memoriaRamGb) {
		this.memoriaRamGb = memoriaRamGb;
	}

	public SOEnum getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(SOEnum sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public Double getCustoHora() {
		return custoHora;
	}

	public void setCustoHora(Double custoHora) {
		this.custoHora = custoHora;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Integer getQtdRequisicoes() {
		return qtdRequisicoes;
	}

	public void setQtdRequisicoes(Integer qtdRequisicoes) {
		this.qtdRequisicoes = qtdRequisicoes;
	}

}
