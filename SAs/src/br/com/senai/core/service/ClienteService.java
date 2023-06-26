package br.com.senai.core.service;

import java.util.List;

import br.com.senai.core.dao.DaoCliente;
import br.com.senai.core.dao.FactoryDao;
import br.com.senai.core.domain.Cliente;

public class ClienteService {
	private DaoCliente dao;
	
	public ClienteService() {
		dao = FactoryDao.getInstance().getDaoPostgresqlCliente();
	}
	
	public List<Cliente> listarCliente() {
		return dao.listarCliente();
	}
}
