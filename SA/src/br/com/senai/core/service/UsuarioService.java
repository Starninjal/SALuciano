package br.com.senai.core.service;

import br.com.senai.core.dao.DaoUsuario;
import br.com.senai.core.dao.FactoryDao;
import br.com.senai.core.domain.Usuario;

public class UsuarioService {
	private DaoUsuario dao;
	
	public UsuarioService() {
		this.dao = FactoryDao.getInstance().getDaoPostgresqlUsuario();
	}
	
	public Usuario autenticar(Usuario usuario) {
		return dao.autenticar(usuario);
	}
	
	
}
