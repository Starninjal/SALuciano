package br.com.senai.core.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.senai.core.dao.DaoUsuario;
import br.com.senai.core.dao.ManagerDb;
import br.com.senai.core.domain.Usuario;

public class DaoPostgresqlUsuario implements DaoUsuario {
	
	private final String SELECT = "SELECT u.usuario_usu, u.senha_usu FROM usuario u WHERE u.usuario_usu= ? and u.senha_usu = ? and tipo_usu = 'Atendente'";

	private Connection conexao;
	
	public DaoPostgresqlUsuario() {
		conexao = ManagerDb.getInstance().getConexao();
	}
	
	@Override
	public Usuario autenticar(Usuario usuario) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement(SELECT);
			ps.setString(1, usuario.getNomeUsuario());
			ps.setString(2, usuario.getSenhaUsuario());
			rs = ps.executeQuery();
			
			return validar(rs, usuario);
			
			
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao autenticar o usuário. Motivo: " + e.getMessage());
		}
	}
	
	public Usuario validar(ResultSet rs, Usuario usuario) throws SQLException {
		if(rs.next()) {
			usuario.setTrue(true);
			return usuario;
		} else {
			usuario.setTrue(false);
			throw new RuntimeException("Usuario e/ou senha inválidos! Tente novamente com as informações corretas.");

		}
		
		
	}

}
