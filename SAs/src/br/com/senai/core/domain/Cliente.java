package br.com.senai.core.domain;

public class Cliente {
	private String cpf;
	
	private String nomeCliente;
	
	private String telefone;
	
	public Cliente(String cpf, String nomeCliente, String telefone) {
		this.cpf = cpf;
		this.nomeCliente = nomeCliente;
		this.telefone = telefone;
	}
	
	

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String toString() {
		return getNomeCliente();
	}
}
