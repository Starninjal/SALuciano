package br.com.senai.core.domain;

public class Servico {
	private int id;
	
	private String nomeServico;
	
	private Atendente atendente;
	
	private Cliente cliente;
	
	private Mecanico mecanico;
	
	public Servico(String nomeServico, Atendente atendente, Cliente cliente, Mecanico mecanico) {
		this.nomeServico = nomeServico;
		this.atendente = atendente;
		this.cliente = cliente;
		this.mecanico = mecanico;
	}
	
	public Servico(int id, String nomeServico, Atendente atendente, Cliente cliente, Mecanico mecanico) {
		this(nomeServico, atendente, cliente, mecanico);
		this.id = id;
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
	
}
