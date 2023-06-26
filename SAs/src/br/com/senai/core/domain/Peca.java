package br.com.senai.core.domain;

public class Peca {
	private int id;
	
	private String nomePeca;
	
	private String marcaPeca;
	
	private int qtdEstoque;
	
	
	public Peca(String nomePeca, String marcaPeca, int qtdEstoque) {
		this.nomePeca = nomePeca;
		this.marcaPeca = marcaPeca;
		this.qtdEstoque = qtdEstoque;
	}
	
	public Peca(int id, String nomePeca, String marcaPeca, int qtdEstoque) {
		this(nomePeca, marcaPeca, qtdEstoque);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isJaInserido() {
		return getId() > 0;
	}

	public String getNomePeca() {
		return nomePeca;
	}

	public void setNomePeca(String nomePeca) {
		this.nomePeca = nomePeca;
	}

	public String getMarcaPeca() {
		return marcaPeca;
	}

	public void setMarcaPeca(String marcaPeca) {
		this.marcaPeca = marcaPeca;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	
	public String toString() {
		return getNomePeca();
	}
}
