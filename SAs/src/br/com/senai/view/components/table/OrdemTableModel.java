package br.com.senai.view.components.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.core.domain.Servico;

public class OrdemTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private final int QTDE_COLUNAS = 7;
	
	private List<Servico> ordens;	
	
	public OrdemTableModel(List<Servico> ordens)  {
		this.ordens = ordens;
	}
	
	@Override
	public int getRowCount() {

		return ordens.size();
		
	}

	public String getColumnName(int column) {
		if (column == 0) {
			return "ID_Servico";
		} else if (column == 1) {
			return "NomeServiço";
		} else if (column == 2 ) {
			return "Mecanico";
		} else if(column == 3) {
			return "Cliente";
		} else if(column == 4) {
			return "Atendente";
		} else if(column == 5) {
			return "PlacaCarro";
		} else if(column == 6) {
			return "DataDeAbertura";
		} else if(column == 7) {
			return "DataDeConclusão";
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
			return ordens.get(rowIndex).getId();
		} else if (columnIndex == 1) {
			return ordens.get(rowIndex).getNomeServico();
		} else if(columnIndex == 2) {
			return ordens.get(rowIndex).getMecanico().getNomeMecanico();
		} else if(columnIndex == 3) {
			return ordens.get(rowIndex).getCliente().getNomeCliente();
		} else if(columnIndex == 4) {
			return ordens.get(rowIndex).getAtendente().getNomeAtendente();
		} else if(columnIndex == 5) {
			return ordens.get(rowIndex).getNomePlaca();
		} else if(columnIndex == 6) {
			return ordens.get(rowIndex).getDataAbertura();
		}
			throw new IllegalArgumentException("Índice inválido");

	}
	
	public Servico getPor(int rowIndex) {
		return ordens.get(rowIndex);
	}
	
	public void removerPor(int rowIndex) {
		this.ordens.remove(rowIndex);
	}
	
}
