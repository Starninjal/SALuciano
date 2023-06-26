package br.com.senai.core.service;

import java.util.List;

import br.com.senai.core.dao.DaoPeca;
import br.com.senai.core.dao.FactoryDao;
import br.com.senai.core.domain.Peca;

public class PecaService {
	private DaoPeca dao;
	
	public PecaService() {
		this.dao = FactoryDao.getInstance().getDaoPostgresqlPeca();
	}
	
	public void salvar(Peca peca) {
		if(peca.isJaInserido()) {
			this.dao.alterar(peca);
		} else {
			this.dao.salvar(peca);
		}
	}
	
	public void excluirPor(int idPeca) {
		boolean isIdPecaInvalida = idPeca < 0;
		if(isIdPecaInvalida) {
			throw new IllegalArgumentException("O id não pode ser menor que 0.");
		} else {
			dao.excluirPor(idPeca);
		}
	}
	
	public List<Peca> listarTodas() {
		return dao.listarTodas();
	}
}
