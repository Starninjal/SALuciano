package br.com.senai.core.dao;

import java.util.List;

import br.com.senai.core.domain.Mecanico;

public interface DaoMecanico {
	public void inserir(Mecanico mecanico);
	
	public void alterar(Mecanico mecanico);
	
	public void excluirPor(int id);
	
	public List<Mecanico> listarMecanicos();
}
