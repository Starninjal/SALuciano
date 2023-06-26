package br.com.senai.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import br.com.senai.core.domain.Mecanico;
import br.com.senai.core.service.MecanicoService;
import br.com.senai.view.components.table.MecanicoTableModel;

public class ViewMecanicoListagem extends JFrame {

	private JPanel contentPane;
	private List<Mecanico> mecanicos;
	private MecanicoService service;
	private JScrollPane spTable;
	private JTable tableMecanico;
	private JButton btnAlterar;
	private JButton btnExcluir;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMecanicoListagem frame = new ViewMecanicoListagem();
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
	public ViewMecanicoListagem() {
		this.service = new MecanicoService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		
		
		JButton btnNewButton = new JButton("Listar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Mecanico> mecanicos = service.listarMecanico();
					
					MecanicoTableModel model = new MecanicoTableModel(mecanicos);
					tableMecanico.setModel(model);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
				}
				
			}
		});
		
		MecanicoTableModel model = new MecanicoTableModel(new ArrayList<Mecanico>());
		tableMecanico = new JTable(model);
		spTable = new JScrollPane(tableMecanico);
		spTable.setBounds(23, 128, 743, 304);
		contentPane.add(spTable);
		
		
		btnNewButton.setBounds(691, 80, 89, 23);
		contentPane.add(btnNewButton);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tableMecanico.getSelectedRow();
				if (linhaSelecionada >= 0) {
					MecanicoTableModel model = (MecanicoTableModel)tableMecanico.getModel();
					Mecanico mecanicoSelecionada = model.getPor(linhaSelecionada);
					ViewCadastroMecanico view = new ViewCadastroMecanico();
					view.setMecanico(mecanicoSelecionada);
					view.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Selecione uma linha para editar");
				}
			}
		});
		btnAlterar.setBounds(556, 443, 89, 23);
		contentPane.add(btnAlterar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tableMecanico.getSelectedRow();
				if (linhaSelecionada >= 0) {
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja remover a linha selecionada");
					if (opcao == 0) {
						MecanicoTableModel model = (MecanicoTableModel)tableMecanico.getModel();
						Mecanico mecanicoSelecionada = model.getPor(linhaSelecionada);
						try {
							model.removerPor(linhaSelecionada);
							service.excluirPor(mecanicoSelecionada.getId());
							tableMecanico.updateUI();
							JOptionPane.showMessageDialog(contentPane, "Cidade removida com sucesso!");
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(contentPane, ex.getMessage());
						}
				
					}
				}
			}
		});
		btnExcluir.setBounds(677, 443, 89, 23);
		contentPane.add(btnExcluir);
		
	}
}
