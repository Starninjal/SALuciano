package br.com.senai.core.dao;

import br.com.senai.core.dao.postgresql.DaoPostgresqlAtendente;
import br.com.senai.core.dao.postgresql.DaoPostgresqlCliente;
import br.com.senai.core.dao.postgresql.DaoPostgresqlMecanico;
import br.com.senai.core.dao.postgresql.DaoPostgresqlOrdemServico;
import br.com.senai.core.dao.postgresql.DaoPostgresqlPeca;
import br.com.senai.core.dao.postgresql.DaoPostgresqlPecaServico;
import br.com.senai.core.dao.postgresql.DaoPostgresqlUsuario;

public class FactoryDao {
	private static FactoryDao instance;
	
	public DaoMecanico getDaoPostgresqlMecanico() {
		return new DaoPostgresqlMecanico();
	}
	
	public DaoUsuario getDaoPostgresqlUsuario() {
		return new DaoPostgresqlUsuario();
	}
	
	public DaoCliente getDaoPostgresqlCliente() {
		return new DaoPostgresqlCliente();
	}

	public DaoAtendente getDaoPostgresqlAtendente() {
		return new DaoPostgresqlAtendente();
	}

	public DaoOrdemServico getDaoPostgresqlOrdemServico() {
		return new DaoPostgresqlOrdemServico();
	}

	public DaoPecaServico getDaoPoestgresqlPecaServico() {
		return new DaoPostgresqlPecaServico();
	}

	public DaoPeca getDaoPostgresqlPeca() {
		return new DaoPostgresqlPeca();
	}
	
	
	
	public static FactoryDao getInstance() {
		if(instance == null) {
			instance = new FactoryDao();
		}
		
			return instance;
	}
	

}

