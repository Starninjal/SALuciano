package br.com.senai.core.domain;

public class ServicoPeca {
	public Servico servico;
	
	private Peca peca;
	
	public ServicoPeca(Servico servico, Peca peca) {
		this.servico = servico;
		this.peca = peca;
	}

	public Servico getServico() {
		return servico;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}
	
	
}
