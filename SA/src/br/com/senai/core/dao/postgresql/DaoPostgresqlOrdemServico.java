package br.com.senai.core.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.senai.core.dao.DaoOrdemServico;
import br.com.senai.core.dao.ManagerDb;
import br.com.senai.core.domain.Atendente;
import br.com.senai.core.domain.Cliente;
import br.com.senai.core.domain.Mecanico;
import br.com.senai.core.domain.Servico;

public class DaoPostgresqlOrdemServico implements DaoOrdemServico{
	
	private Connection conexao;
	
	public DaoPostgresqlOrdemServico() {
		this.conexao = ManagerDb.getInstance().getConexao();
	}
	
		private final String INSERT = "INSERT INTO servico(nome_serv, fk_mecanico, fk_cliente, fk_atendente, placa_carro, data_abertura, data_conclusao) VALUES (?, ?, ?, ?, ?, ?, ?)";

		private final String SELECT = "SELECT "
										+ " s.id_serv, s.nome_serv, s.placa_carro, s.data_abertura, s.data_conclusao, c.cpf, c.nome_cliente, c.telefone, a.id_atendente, a.nome_atendente, m.id_mecanico, "
										+ " m.nome_mecanico "
									+ "FROM "
									+ "		servico s "
									+ "		INNER JOIN cliente c on c.cpf = s.fk_cliente "
									+ "		INNER JOIN atendente a on a.id_atendente = s.fk_atendente "
									+ "		INNER JOIN mecanico m on m.id_mecanico = s.fk_mecanico "
									+ "ORDER BY s.id_serv";
		
		private final String UPDATE = "UPDATE servico SET nome_serv = ?, placa_carro = ?, fk_mecanico = ?, fk_cliente = ?, fk_atendente = ?, data_abertura = ?, data_conclusao = ? where id_serv = ?";
		private final String DELETE = "DELETE FROM servico WHERE id_serv = ?";
		
		
		
	@Override
	public void salvar(Servico ordem) {
		// TODO Auto-generated method stub
		
		PreparedStatement ps = null;
		try {
			ps = conexao.prepareStatement(INSERT);
			ps.setString(1, ordem.getNomeServico());
			ps.setInt(2, ordem.getMecanico().getId());
			ps.setString(3, ordem.getCliente().getCpf());
			ps.setInt(4, ordem.getAtendente().getId());
			ps.setString(5, ordem.getNomePlaca());
			ps.setTimestamp(6, java.sql.Timestamp.valueOf(ordem.getDataAbertura()));
			ps.setTimestamp(7, java.sql.Timestamp.valueOf(ordem.getDataConclusao()));
			ps.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException("Ocorreu um erro ao inserir a ordem de serviço. Motivo: " + e.getMessage());
		} finally {
			ManagerDb.getInstance().fechar(ps);
		}
	}

	@Override
	public void alterar(Servico ordem) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ManagerDb.getInstance().configurarAutoCommitDa(conexao, false);
			ps = conexao.prepareStatement(UPDATE);
			ps.setString(1, ordem.getNomeServico());
			ps.setString(2, ordem.getNomePlaca());
			ps.setInt(3, ordem.getMecanico().getId());
			ps.setString(4, ordem.getCliente().getCpf());
			ps.setInt(5, ordem.getAtendente().getId());
			ps.setTimestamp(6, java.sql.Timestamp.valueOf(ordem.getDataAbertura()));
			ps.setTimestamp(7, java.sql.Timestamp.valueOf(ordem.getDataConclusao()));
			ps.setInt(8, ordem.getId());
			boolean isALteracaoOK = ps.executeUpdate() == 1;
			if (isALteracaoOK) {
				this.conexao.commit();	
			} else {
				this.conexao.rollback();
			}
			ManagerDb.getInstance().configurarAutoCommitDa(conexao, true);
		} catch(Exception e) {
			throw new RuntimeException("Ocorreu um erro ao alterar a ordem de serviço. Motivo: " + e.getMessage());
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
			throw new RuntimeException("Ocorreu um erro ao excluir a ordem de serviço. Motivo: " + e.getMessage());
			
		} finally {
			ManagerDb.getInstance().fechar(ps);
		}
	}

	@Override
	public List<Servico> listarPor(String filtro) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement(filtro);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Servico> listarTodas() {
		// TODO Auto-generated method stub
		List<Servico> ordens = new ArrayList<Servico>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement(SELECT);
			rs = ps.executeQuery();
			while(rs.next()) {
				ordens.add(extrairDo(rs));
			}
			return ordens;
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao listar a ordem de serviço. Motivo: " + e.getMessage());
		} finally {
			ManagerDb.getInstance().fechar(ps);
			ManagerDb.getInstance().fechar(rs);
		}
	}
	
	
	public Servico extrairDo(ResultSet rs) {
		try {
			int idServico = rs.getInt("s.id_serv");
			String nomeServico = rs.getString("s.nome_serv");
			String placa = rs.getString("s.placa_carro");
			LocalDateTime dataAbertura = rs.getTimestamp("s.data_abertura").toLocalDateTime();
			LocalDateTime dataConclusao = rs.getTimestamp("s.data_conclusao").toLocalDateTime();
			
			String cpf= rs.getString("c.cpf");
			String nomeCliente = rs.getString("c.nome_cliente");
			String telefone = rs.getString("c.telefone");
			
			Cliente cliente = new Cliente(cpf, nomeCliente, telefone);
			
			int idAtendente = rs.getInt("a.id_atendente");
			String nomeAtendente = rs.getString("a.nome_atendente");
			Atendente atendente = new Atendente(idAtendente, nomeAtendente);
			
			int idMecanico = rs.getInt("m.id_mecanico");
			String nome_mecanico = rs.getString("m.nome_mecanico");
			Mecanico mecanico = new Mecanico(idMecanico, nome_mecanico);
			
			return new Servico(idServico, nomeServico, atendente, cliente, mecanico, placa, dataAbertura, dataConclusao);
			
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao extrair a ordem do rs. Motivo: " + e.getMessage());
		}

	}


}
