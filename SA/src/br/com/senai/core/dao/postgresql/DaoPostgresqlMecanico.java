package br.com.senai.core.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.senai.core.dao.DaoMecanico;
import br.com.senai.core.dao.ManagerDb;
import br.com.senai.core.domain.Mecanico;

public class DaoPostgresqlMecanico implements DaoMecanico{

	
	private Connection conexao;
	
	public DaoPostgresqlMecanico() {
		this.conexao = ManagerDb.getInstance().getConexao();
		
	}
	
	private static String INSERT = "INSERT INTO mecanico(nome_mecanico) VALUES (?)";
	
	@Override
	public void inserir(Mecanico mecanico) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = conexao.prepareStatement(INSERT);
			ps.setString(1, mecanico.getNomeMecanico());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao inserir o mecânico. Motivo: " + e.getMessage());
		} finally {
			ManagerDb.getInstance().fechar(ps);
		}
	}

	@Override
	public void alterar(Mecanico mecanico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluirPor(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Mecanico> listarMecanicos() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Mecanico> mecanicos = new ArrayList<>();
		try {
			rs = ps.executeQuery();
			if(rs.next()) {
				return mecanicos;
			}
		} catch(Exception e) {
			throw new RuntimeException("Ocorreu um erro ao listar o mecânico. Motivo: " + e.getMessage());
		}
		return mecanicos;
		
	}

}
