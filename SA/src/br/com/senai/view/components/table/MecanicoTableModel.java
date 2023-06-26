package br.com.senai.view.components.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.core.domain.Mecanico;

public class MecanicoTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private final int QTDE_COLUNAS = 2;
	
	private List<Mecanico> mecanicos;
	
	public MecanicoTableModel(List<Mecanico> mecanicos) {
		this.mecanicos = mecanicos;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return mecanicos.size();
	}
	
	public String getColumnName(int column) {
		if(column == 0) {
			return "ID Mecanico";
		} else if(column == 1) {
			return "Nome Mecanico";
		}
			throw new IllegalArgumentException("Índice invalido.");
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return QTDE_COLUNAS;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex == 0) {
			return mecanicos.get(rowIndex).getId();
		} else if(columnIndex == 1) {
			return mecanicos.get(rowIndex).getNomeMecanico();
		}
			throw new IllegalArgumentException("Índice inválido.");
	}
	
	public Mecanico getPor(int rowIndex) {
		return mecanicos.get(rowIndex);
	}
	
	public void removerPor(int rowIndex) {
		this.mecanicos.remove(rowIndex);
	}

}
