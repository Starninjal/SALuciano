package br.com.senai.core.dao;

import br.com.senai.core.dao.postgresql.DaoPostgresqlMecanico;

public class FactoryDao {
	private static FactoryDao instance;
	private DaoMecanico dao;
	
	public DaoMecanico getDaoPostgresqlMecanico() {
		return new DaoPostgresqlMecanico();
	}
	
	public static FactoryDao getInstance() {
		if(instance == null) {
			instance = new FactoryDao();
		}
		
			return instance;
	}
}

