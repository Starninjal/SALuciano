package br.com.senai.core.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.senai.core.dao.DaoMecanico;
import br.com.senai.core.dao.ManagerDb;
import br.com.senai.core.domain.Mecanico;

public class DaoPostgresqlMecanico implements DaoMecanico{

	
	private Connection conexao;
	
	public DaoPostgresqlMecanico() {
		this.conexao = ManagerDb.getInstance().getConexao();
		
	}
	
	private static String INSERT = "INSERT INTO mecanico(nome_mecanico) VALUES (?)";
	
	
	private static String SELECT = "SELECT id_mecanico, nome_mecanico from mecanico order by mecanico.id_mecanico";
	
	private final String UPDATE = "UPDATE mecanico SET nome_mecanico = ? where id_mecanico = ?";
	private final String DELETE = "DELETE FROM mecanico WHERE id_mecanico = ?";
	
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
			JOptionPane.showMessageDialog(null, mecanico.getId());
			boolean isALteracaoOK = ps.executeUpdate() == 1;
			if (isALteracaoOK) {
				this.conexao.commit();	
			} else {
				this.conexao.rollback();
			}
			ManagerDb.getInstance().configurarAutoCommitDa(conexao, true);
		} catch(Exception e) {
			throw new RuntimeException("Ocorreu um erro ao alterar o contato. Motivo: " + e.getMessage());
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
			boolean isExclusaoOK = ps.executeUpdate() == 1;
			if(isExclusaoOK) {
				this.conexao.commit();
			} else {
				this.conexao.rollback();
			}
			ManagerDb.getInstance().configurarAutoCommitDa(conexao, true);
		} catch(Exception e) {
			throw new RuntimeException("Ocorreu um erro ao excluir o contato. Motivo: " + e.getMessage());
			
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
		} catch(Exception e) {
			throw new RuntimeException("Ocorreu um erro ao listar o mecânico. Motivo: " + e.getMessage());
		} 
		return mecanicos;
		
		
	}
	
	public Mecanico extrairDo(ResultSet rs) {
		try {
			int idMecanico = rs.getInt("id_mecanico");
			String nomeMecanico = rs.getString("nome_mecanico");
			return new Mecanico(idMecanico, nomeMecanico);
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao extrair o mecânico do ResultSet. mOtivo: " + e.getMessage());
		}
		
	}

}
