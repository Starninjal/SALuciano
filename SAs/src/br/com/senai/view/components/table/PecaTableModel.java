package br.com.senai.view.components.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.core.domain.Peca;

public class PecaTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private final int QTDE_COLUNAS = 3;
	
	private List<Peca> pecas;	
	
	public PecaTableModel(List<Peca> pecas)  {
		this.pecas = pecas;
	}
	
	@Override
	public int getRowCount() {

		return pecas.size();
		
	}

	public String getColumnName(int column) {
		if (column == 0) {
			return "ID_Peca";
		} else if(column == 1) {
			return "Nome_Peca";
		} else if(column == 2) {
			return "Marca_Peca";
		} else if(column == 3) {
			return "Quantidade_Estoque";
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
			return pecas.get(rowIndex).getId();
		} else if(columnIndex == 1) {
			return pecas.get(rowIndex).getNomePeca();
		} else if(columnIndex == 2) {
			return pecas.get(rowIndex).getMarcaPeca();
		} else if(columnIndex == 3) {
			return pecas.get(rowIndex).getQtdEstoque();
		}
			throw new IllegalArgumentException("Índice inválido");

	}
	
	public Peca getPor(int rowIndex) {
		return pecas.get(rowIndex);
	}
	
	public void removerPor(int rowIndex) {
		this.pecas.remove(rowIndex);
	}
	
}
