package br.com.senai.core.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	private final String INSERT = "INSERT INTO mecanico(nome_mecanico) VALUES (?)";
	
	private final String SELECT = "SELECT mecanico.id_mecanico, mecanico.nome_mecanico FROM mecanico";
	
	private final String UPDATE = "UPDATE mecanico SET nome_mecanico = ? WHERE id_mecanico = ?";
	
	private final String DELETE = "DELETE FROM MECANICO WHERE id_mecanico = ?";
	
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
		PreparedStatement ps = null;
		try {
			ManagerDb.getInstance().configurarAutoCommitDa(conexao, false);
			ps = conexao.prepareStatement(UPDATE);
			ps.setString(1, mecanico.getNomeMecanico());
			ps.setInt(2, mecanico.getId());
			boolean isAlteracaoOk = ps.executeUpdate() == 1;
			
			if(isAlteracaoOk) {
				conexao.commit();
			} else {
				conexao.rollback();
			}
			ManagerDb.getInstance().configurarAutoCommitDa(conexao, true);
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao alterar o mecânico. Motivo: " + e.getMessage());
		} finally {
			ManagerDb.getInstance().fechar(ps);
		}
	}

	@Override
	public void excluirPor(int id) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ManagerDb.getInstance().configurarAutoCommitDa(conexao, false);
			ps = conexao.prepareStatement(DELETE);
			ps.setInt(1, id);
			boolean isExclusaoOk = ps.executeUpdate() == 1;
			if(isExclusaoOk) {
				conexao.commit();
			} else {
				conexao.rollback();
			}
			ManagerDb.getInstance().fechar(ps);
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao excluir o mecânico. Motivo: " + e.getMessage());
		} finally {
			ManagerDb.getInstance().fechar(ps);
		}
	}

	@Override
	public List<Mecanico> listarMecanicos() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Mecanico> mecanicos = new ArrayList<>();
		try {
			ps = conexao.prepareStatement(SELECT);
			rs = ps.executeQuery();
			while(rs.next()) {
				mecanicos.add(extrairDo(rs));
			}
			return mecanicos;
		} catch(Exception e) {
			throw new RuntimeException("Ocorreu um erro ao listar o mecânico. Motivo: " + e.getMessage());
		}
		
	}
	
	
	
	public Mecanico extrairDo(ResultSet rs) {
	
		try {
			int id = rs.getInt("id_mecanico");
			String nome_mecanico = rs.getString("nome_mecanico");
			return new Mecanico(id, nome_mecanico);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Ocorreu um erro ao extrair o rs. Motivo: " + e.getMessage());
		}
	}

}
