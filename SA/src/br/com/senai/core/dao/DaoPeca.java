package br.com.senai.core.dao;

import java.util.List;

import br.com.senai.core.domain.Peca;

public interface DaoPeca {
	public void salvar(Peca peca);
	
	public void alterar(Peca peca);
	
	public void excluirPor(int idPeca);
	
	public List<Peca> listarTodas();
	
}
