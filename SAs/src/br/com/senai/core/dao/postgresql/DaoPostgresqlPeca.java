package br.com.senai.core.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.senai.core.dao.DaoPeca;
import br.com.senai.core.dao.ManagerDb;
import br.com.senai.core.domain.Peca;

public class DaoPostgresqlPeca implements DaoPeca {

    private Connection conexao;

    public DaoPostgresqlPeca() {
        conexao = ManagerDb.getInstance().getConexao();
    }

    private final static String INSERT = "INSERT INTO peca (nome_peca, marca_peca, qtd_estoque) VALUES (?, ?, ?)";
    
    private final static String UPDATE = "UPDATE peca SET nome_peca = ?, marca_peca = ?, qtd_estoque = ? WHERE id_peca = ?";
    
    private final static String DELETE = "DELETE FROM peca WHERE id_peca = ?";
    
    private final static String SELECT = "SELECT id_peca, nome_peca, marca_peca, qtd_estoque from peca order by id_peca ";
    


	@Override
	public void salvar(Peca peca) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = conexao.prepareStatement(INSERT);
			ps.setString(1, peca.getNomePeca());
			ps.setString(2, peca.getMarcaPeca());
			ps.setInt(3, peca.getQtdEstoque());
			ps.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException("Ocorreu um erro ao inserir a peça. Motivo: " + e.getMessage());
		} finally {
			ManagerDb.getInstance().fechar(ps);
		}
	}

	@Override
	public void alterar(Peca peca) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ManagerDb.getInstance().configurarAutoCommitDa(conexao, false);
			ps = conexao.prepareStatement(UPDATE);
			ps.setString(1, peca.getNomePeca());
			ps.setString(2, peca.getMarcaPeca());
			ps.setInt(3, peca.getQtdEstoque());
			ps.setInt(4, peca.getId());
			boolean isALteracaoOK = ps.executeUpdate() == 1;
			if (isALteracaoOK) {
				this.conexao.commit();	
			} else {
				this.conexao.rollback();
			}
			ManagerDb.getInstance().configurarAutoCommitDa(conexao, true);
		} catch(Exception e) {
			throw new RuntimeException("Ocorreu um erro ao alterar a peça. Motivo: " + e.getMessage());
		} finally {
			ManagerDb.getInstance().fechar(ps);
		}
	}

	@Override
	public void excluirPor(int idPeca) {
		PreparedStatement ps = null;
		try {
			ManagerDb.getInstance().configurarAutoCommitDa(conexao, false);
			ps = conexao.prepareStatement(DELETE);
			ps.setInt(1, idPeca);
			boolean isExclusaoOK = ps.executeUpdate() == 1;
			if(isExclusaoOK) {
				this.conexao.commit();
			} else {
				this.conexao.rollback();
			}
			ManagerDb.getInstance().configurarAutoCommitDa(conexao, true);
		} catch(Exception e) {
			throw new RuntimeException("Ocorreu um erro ao excluir a ordem de serviço. Motivo: " + e.getMessage());
			
		} finally {
			ManagerDb.getInstance().fechar(ps);
		}
	}

	@Override
	public List<Peca> listarTodas() {
		// TODO Auto-generated method stub
		List<Peca> pecas = new ArrayList<Peca>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement(SELECT);
			rs = ps.executeQuery();
			while(rs.next()) {
				pecas.add(extrairDo(rs));
			}
			return pecas;
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao listar as peças. Motivo: " + e.getMessage());
		}
	}
	
	public Peca extrairDo(ResultSet rs) {
		int idPeca;
		try {
			idPeca = rs.getInt("id_peca");
			String nomePeca = rs.getString("nome_peca");
			String marcaPeca = rs.getString("marca_peca");
			int qtdEstoque = rs.getInt("qtd_estoque");
			return new Peca(idPeca, nomePeca, marcaPeca, qtdEstoque);
		} catch (SQLException e) {
			throw new RuntimeException("Ocorreu um erro ao extrair as peças do resultSet. Motivo: " + e.getMessage());
		}
		
	}
}
