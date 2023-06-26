package br.com.senai.core.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.senai.core.dao.DaoAtendente;
import br.com.senai.core.dao.ManagerDb;
import br.com.senai.core.domain.Atendente;

public class DaoPostgresqlAtendente implements DaoAtendente {

	private Connection conexao;

	public DaoPostgresqlAtendente() {
		conexao = ManagerDb.getInstance().getConexao();
	}
	

	private final String SELECT = "SELECT id_atendente, nome_atendente from atendente order by id_atendente";
	

	@Override
	public List<Atendente> listarAtendente() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Atendente> atendentes = new ArrayList<>();
		try {
			ps = conexao.prepareStatement(SELECT);
			rs = ps.executeQuery();
			while(rs.next()) {
				atendentes.add(extrairDo(rs));
			}
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao listar o atendente. Motivo:" + e.getMessage());
		} finally {
			ManagerDb.getInstance().fechar(ps);
			ManagerDb.getInstance().fechar(rs);
		}
		return atendentes;
	}

	public Atendente extrairDo(ResultSet rs) {
		
		try {
			int id = rs.getInt("id_atendente");
			String nomeAtendente = rs.getString("nome_atendente");
			return new Atendente(id, nomeAtendente);
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao extrair o atendente do rs. Motivo: " + e.getMessage());
		}

	
	}
}


