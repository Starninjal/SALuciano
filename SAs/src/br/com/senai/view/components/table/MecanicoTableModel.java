package br.com.senai.view.components.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.core.domain.Mecanico;
import br.com.senai.core.domain.Servico;

public class MecanicoTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private final int QTDE_COLUNAS = 2;
	
	private List<Mecanico> mecanicos;	
	
	public MecanicoTableModel(List<Mecanico> mecanicos)  {
		this.mecanicos = mecanicos;
	}
	
	@Override
	public int getRowCount() {

		return mecanicos.size();
		
	}

	public String getColumnName(int column) {
		if (column == 0) {
			return "ID_Mecanico";
		} else if(column == 1) {
			return "Nome_Mecanico";
		}
			throw new IllegalArgumentException("Índice inválido");
	}
	
	@Override
	public int getColumnCount() {

		return QTDE_COLUNAS;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return mecanicos.get(rowIndex).getId();
		} else if(columnIndex == 1) {
			return mecanicos.get(rowIndex).getNomeMecanico();
		}
			throw new IllegalArgumentException("Índice inválido");

	}
	
	public Mecanico getPor(int rowIndex) {
		return mecanicos.get(rowIndex);
	}
	
	public void removerPor(int rowIndex) {
		this.mecanicos.remove(rowIndex);
	}
	
}
