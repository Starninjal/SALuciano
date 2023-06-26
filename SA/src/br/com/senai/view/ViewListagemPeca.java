package br.com.senai.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.senai.core.domain.Peca;
import br.com.senai.core.service.PecaService;
import br.com.senai.view.components.table.PecaTableModel;

public class ViewListagemPeca extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable tbPeca;
	private JScrollPane spTable;
	private PecaService service;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewListagemPeca frame = new ViewListagemPeca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewListagemPeca() {
		this.service = new PecaService();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 876, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(contentPane);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 91, 735, 32);
		contentPane.add(textField);
		
		JLabel lblNewLabel = new JLabel("Filtro");
		lblNewLabel.setBounds(10, 67, 46, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Peca> pecas = service.listarTodas();
				for (int i = 0; i < pecas.size(); i++) {
					JOptionPane.showMessageDialog(contentPane, pecas.get(i).getNomePeca());
				}
				PecaTableModel model = new PecaTableModel(pecas);
				tbPeca.setModel(model);
			}
		});
		btnListar.setBounds(757, 93, 89, 29);
		contentPane.add(btnListar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCadastroPeca view = new ViewCadastroPeca();
				view.setVisible(true);
			}
		});
		btnAdicionar.setBounds(757, 11, 89, 26);
		contentPane.add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tbPeca.getSelectedRow();
				try {
					if (linhaSelecionada >= 0) {
						PecaTableModel model = (PecaTableModel) tbPeca.getModel();
						Peca pecaSelecionada = model.getPor(linhaSelecionada);
						ViewCadastroPeca view = new ViewCadastroPeca();
						view.setPeca(pecaSelecionada);
						view.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(contentPane, "Selecione uma linha para editar");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		btnEditar.setBounds(649, 488, 89, 26);
		contentPane.add(btnEditar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tbPeca.getSelectedRow();
				if (linhaSelecionada >= 0) {
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja remover a linha selecionada");
					if (opcao == 0) {
						PecaTableModel model = (PecaTableModel)tbPeca.getModel();
						Peca pecaSelecionada = model.getPor(linhaSelecionada);
						try {
							model.removerPor(linhaSelecionada);
							service.excluirPor(pecaSelecionada.getId());
							tbPeca.updateUI();
							JOptionPane.showMessageDialog(contentPane, "Peça removido com sucesso!");
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(contentPane, ex.getMessage());
						}
				
					}
				}
			}
		});
		btnRemover.setBounds(757, 488, 89, 26);
		contentPane.add(btnRemover);
		
		
		
		PecaTableModel model = new PecaTableModel(new ArrayList<Peca>());
		tbPeca = new JTable(model);
		spTable = new JScrollPane(tbPeca);
		spTable.setBounds(10, 161, 836, 224);
		contentPane.add(spTable);
		
		
		
		
		
	}

}
