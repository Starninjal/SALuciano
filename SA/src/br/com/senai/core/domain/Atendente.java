package br.com.senai.core.domain;

public class Atendente {
	private int id;
	
	private String nomeAtendente;
	
	public Atendente(int id, String nomeAtendente) {
		this.id = id;
		this.nomeAtendente = nomeAtendente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeAtendente() {
		return nomeAtendente;
	}

	public void setNomeAtendente(String nomeAtendente) {
		this.nomeAtendente = nomeAtendente;
	}
	
	public String toString() {
		return getNomeAtendente();
	}
}
