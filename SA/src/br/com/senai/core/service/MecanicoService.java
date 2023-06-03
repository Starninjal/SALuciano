package br.com.senai.core.service;

import java.util.List;

import br.com.senai.core.dao.DaoMecanico;
import br.com.senai.core.dao.FactoryDao;
import br.com.senai.core.domain.Mecanico;

public class MecanicoService {
	private DaoMecanico dao;
	
	public MecanicoService() {
		this.dao = FactoryDao.getInstance().getDaoPostgresqlMecanico();
	}
	
	public void salvar(Mecanico mecanico) {
		if(mecanico.isJaInserido()) {
			dao.alterar(mecanico);
		} else {
			dao.inserir(mecanico);
		}
	}
	
	public void validar(Mecanico mecanico) {
		if(mecanico != null) {
			boolean isNomeInvalido = mecanico.getNomeMecanico().length() < 0;
			if(isNomeInvalido) {
				throw new IllegalArgumentException("O nome não pode ser nulo!");
			}
		} else {
			throw new IllegalArgumentException("O mecanico é nulo!");
		}
	}
	
	public List<Mecanico> listarMecanico() {
		return dao.listarMecanicos();
	}
	
	public void excluirPor(int id) {
		if(id <= 0) {
			throw new IllegalArgumentException("O id é inválido!");
		} else {
			dao.excluirPor(id);
		}
	}
}
