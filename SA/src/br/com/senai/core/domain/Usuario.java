package br.com.senai.core.domain;

public class Usuario {
	private int id;
	
	private String nomeUsuario;
	
	private String senhaUsuario;
	
	private boolean isTrue;
	
	public Usuario(String nomeUsuario, String senhaUsuario) {
		this.nomeUsuario = nomeUsuario;
		this.senhaUsuario = senhaUsuario;
	}
	
	public Usuario(int id, String nomeUsuario, String senhaUsuario) {
		this(nomeUsuario, senhaUsuario);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public boolean isTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
	
	
	
	
}
