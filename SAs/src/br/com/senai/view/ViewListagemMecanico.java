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

import br.com.senai.core.domain.Mecanico;
import br.com.senai.core.domain.Servico;
import br.com.senai.core.service.MecanicoService;
import br.com.senai.view.components.table.MecanicoTableModel;
import br.com.senai.view.components.table.OrdemTableModel;

public class ViewListagemMecanico extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable tbMecanico;
	private JScrollPane spTable;
	private MecanicoService service;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewListagemMecanico frame = new ViewListagemMecanico();
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
	public ViewListagemMecanico() {
		this.service = new MecanicoService();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 875, 566);
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
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCadastroMecanico view = new ViewCadastroMecanico();
				view.setVisible(true);
			}
		});
		btnAdicionar.setBounds(757, 11, 89, 26);
		contentPane.add(btnAdicionar);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Mecanico> mecanicos = service.listarMecanico();
				MecanicoTableModel model = new MecanicoTableModel(mecanicos);
				tbMecanico.setModel(model);
			}
		});
		btnListar.setBounds(757, 93, 89, 29);
		contentPane.add(btnListar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tbMecanico.getSelectedRow();
				try {
					if (linhaSelecionada >= 0) {
						MecanicoTableModel model = (MecanicoTableModel) tbMecanico.getModel();
						Mecanico mecanicoSelecionado = model.getPor(linhaSelecionada);
						ViewCadastroMecanico view = new ViewCadastroMecanico();
						view.setMecanico(mecanicoSelecionado);
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
				int linhaSelecionada = tbMecanico.getSelectedRow();
				if (linhaSelecionada >= 0) {
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja remover a linha selecionada");
					if (opcao == 0) {
						MecanicoTableModel model = (MecanicoTableModel)tbMecanico.getModel();
						Mecanico mecanicoSelecionado = model.getPor(linhaSelecionada);
						try {
							model.removerPor(linhaSelecionada);
							service.excluirPor(mecanicoSelecionado.getId());
							tbMecanico.updateUI();
							JOptionPane.showMessageDialog(contentPane, "Mecânico removido com sucesso!");
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(contentPane, ex.getMessage());
						}
				
					}
				}
			}
		});
		btnRemover.setBounds(757, 488, 89, 26);
		contentPane.add(btnRemover);
		
		MecanicoTableModel model = new MecanicoTableModel(new ArrayList<Mecanico>());
		tbMecanico = new JTable(model);
		spTable = new JScrollPane(tbMecanico);
		spTable.setBounds(10, 161, 836, 224);
		contentPane.add(spTable);
		
	}
}
