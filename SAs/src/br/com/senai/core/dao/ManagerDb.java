package br.com.senai.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManagerDb {
	private static ManagerDb manager;
	
	private Connection conexao;
	
	public Connection getConexao() {
		return conexao;
	}
	
	private ManagerDb() {
		try {					
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina_mecanica", "root", "Btwimgood");
		} catch(Exception e) {
			throw new RuntimeException("Ocorreu um erro ao conectar ao banco. Motivo: " + e.getMessage());
		}
		
		
	}
	
	public void fechar(PreparedStatement ps) {
		try {
			if(ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao fechar o ps. Motivo: " +e.getMessage());
		}
	}
	
	public void fechar(ResultSet rs) {
		try {
			
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao fechar o rs. Motivo: " + e.getMessage());
		}
	}
	
	public void configurarAutoCommitDa(Connection conexao, boolean isHabilitado) {
		try {
			if(conexao != null) {
				conexao.setAutoCommit(isHabilitado);
			}
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível configurar o autoCommit da conexão. Motivo: " + e.getMessage());
		}
		
	}
	
	public static ManagerDb getInstance() {
		if(manager == null) {
			manager = new ManagerDb();
		}
			return manager;
	}
}
