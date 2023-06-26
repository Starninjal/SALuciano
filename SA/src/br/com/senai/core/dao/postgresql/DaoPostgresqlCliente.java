package br.com.senai.core.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.senai.core.dao.DaoCliente;
import br.com.senai.core.dao.ManagerDb;
import br.com.senai.core.domain.Cliente;

public class DaoPostgresqlCliente implements DaoCliente {
	
	private Connection conexao;
	
	public DaoPostgresqlCliente() {
		this.conexao = ManagerDb.getInstance().getConexao();
	}
	
	
	private final static String SELECT = "SELECT cpf, nome_cliente, telefone from cliente order by cliente.cpf";

	@Override
	public List<Cliente> listarCliente() {
		// TODO Auto-generated method stub
		List<Cliente> clientes = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement(SELECT);
			rs = ps.executeQuery();
			while(rs.next()) {
				clientes.add(extrairDo(rs));
			}
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao listar o cliente. Motivo: " + e.getMessage());
		}
		return clientes;
	}
	
	public Cliente extrairDo(ResultSet rs) {
		try {
			String cpf = rs.getString("cpf");
			String nomeCliente = rs.getString("nome_cliente");
			String telefone = rs.getString("telefone");
			return new Cliente(cpf, nomeCliente, telefone);
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao extrair o cliente do ResultSet. Motivo: " + e.getMessage());
		}
		
		
	}

}
