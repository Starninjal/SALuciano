package br.com.senai.core.dao;

import java.util.List;

import br.com.senai.core.domain.Servico;

public interface DaoOrdemServico {
	public void salvar(Servico ordem);
	
	public void alterar(Servico ordem);
	
	public void excluirPor(int id);
	
	public List<Servico> listarPor(String filtro);
	
	public List<Servico> listarTodas();
}
