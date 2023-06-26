package br.com.senai.core.domain;

import java.time.LocalDateTime;

public class Servico {
	private int id;
	
	private String nomeServico;
	
	private Atendente atendente;
	
	private Cliente cliente;
	
	private Mecanico mecanico;
	
	private String nomePlaca;
	
	private LocalDateTime dataAbertura;
	
	private LocalDateTime dataConclusao;
	

	public Servico(String nomeServico, Atendente atendente, Cliente cliente, Mecanico mecanico, String nomePlaca, LocalDateTime dataAbertura, LocalDateTime dataConclusao) {
		this.nomeServico = nomeServico;
		this.atendente = atendente;
		this.cliente = cliente;
		this.mecanico = mecanico;
		this.nomePlaca = nomePlaca;
		this.dataAbertura = dataAbertura;
		this.dataConclusao = dataConclusao;
	}
	
	public Servico(int id, String nomeServico, Atendente atendente, Cliente cliente, Mecanico mecanico, String nomePlaca, LocalDateTime dataAbertura, LocalDateTime dataConclusao) {
		this(nomeServico, atendente, cliente, mecanico, nomeServico, dataAbertura, dataConclusao);
		this.id = id;
	}
	
	
	public boolean isJaInserido() {
		return getId() > 0;
	}
	

	public int getId() {
		return id;
	}

	public String getNomeServico() {
		return nomeServico;
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public String getNomePlaca() {
		return nomePlaca;
	}

	public void setNomePlaca(String nomePlaca) {
		this.nomePlaca = nomePlaca;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDateTime dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	
	public String toString() {
		return getNomeServico();
	}
	
	
	
	
	
}
