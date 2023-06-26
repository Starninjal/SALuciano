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

import br.com.senai.core.domain.Servico;
import br.com.senai.core.service.OrdemServicoService;
import br.com.senai.view.components.table.OrdemTableModel;

public class ViewListagemOrdem extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JScrollPane spTable;
	private JTable tbOrdem;
	private OrdemServicoService service;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewListagemOrdem frame = new ViewListagemOrdem();
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
	public ViewListagemOrdem() {
		this.service = new OrdemServicoService();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 887, 562);
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
				List<Servico> ordens = service.listarTodas();
				OrdemTableModel model = new OrdemTableModel(ordens);
				tbOrdem.setModel(model);
			}
		});
		btnListar.setBounds(757, 93, 89, 29);
		contentPane.add(btnListar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewOrdemServico view = new ViewOrdemServico();
				view.setVisible(true);
			}
		});
		btnAdicionar.setBounds(757, 11, 89, 26);
		contentPane.add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tbOrdem.getSelectedRow();
				if (linhaSelecionada >= 0) {
					OrdemTableModel model = (OrdemTableModel)tbOrdem.getModel();
					Servico servicoSelecionado = model.getPor(linhaSelecionada);
					ViewOrdemServico view = new ViewOrdemServico();
					view.setOrdem(servicoSelecionado);
					view.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Selecione uma linha para editar");
				}
			}
		});
		btnEditar.setBounds(649, 488, 89, 26);
		contentPane.add(btnEditar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tbOrdem.getSelectedRow();
				if (linhaSelecionada >= 0) {
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja remover a linha selecionada");
					if (opcao == 0) {
						OrdemTableModel model = (OrdemTableModel)tbOrdem.getModel();
						Servico servicoSelecionado = model.getPor(linhaSelecionada);
						try {
							model.removerPor(linhaSelecionada);
							service.excluirPor(servicoSelecionado.getId());
							tbOrdem.updateUI();
							JOptionPane.showMessageDialog(contentPane, "Ordem de Serviço removida com sucesso!");
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(contentPane, ex.getMessage());
						}
				
					}
				}
			}
		});
		btnRemover.setBounds(757, 488, 89, 26);
		contentPane.add(btnRemover);
		
		OrdemTableModel model = new OrdemTableModel(new ArrayList<Servico>());
		tbOrdem = new JTable(model);
		spTable = new JScrollPane(tbOrdem);
		spTable.setBounds(10, 161, 836, 224);
		contentPane.add(spTable);
		
		

		
	}
}
