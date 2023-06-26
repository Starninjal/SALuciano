package br.com.senai.core.domain;

public class Mecanico {
	private int id;
	
	private String nomeMecanico;
	
	public Mecanico(String nomeMecanico) {
		this.nomeMecanico = nomeMecanico;
	}
	
	public boolean isJaInserido() {
		return getId() > 0;
	}
	
	public Mecanico(int id, String nomeMecanico) {
		this(nomeMecanico);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeMecanico() {
		return nomeMecanico;
	}

	public void setNomeMecanico(String nomeMecanico) {
		this.nomeMecanico = nomeMecanico;
	}
	
	public String toString() {
		return getNomeMecanico();
	}

	
	
}
