package br.com.senai.core.dao;

import br.com.senai.core.dao.postgresql.DaoPostgresqlMecanico;
import br.com.senai.core.dao.postgresql.DaoPostgresqlUsuario;

public class FactoryDao {
	private static FactoryDao instance;
	
	public DaoMecanico getDaoPostgresqlMecanico() {
		return new DaoPostgresqlMecanico();
	}
	
	public DaoUsuario getDaoPostgresqlUsuario() {
		return new DaoPostgresqlUsuario();
	}
	
	public static FactoryDao getInstance() {
		if(instance == null) {
			instance = new FactoryDao();
		}
		
			return instance;
	}
}

