package br.com.senai.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.senai.core.domain.Peca;
import br.com.senai.core.service.PecaService;

public class ViewCadastroPeca extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomePeca;
	private JTextField txtMarcaPeca;
	private JTextField txtQtdEstoque;
	private PecaService service;
	private Peca peca;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCadastroPeca frame = new ViewCadastroPeca();
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
	public ViewCadastroPeca() {
		this.service = new PecaService();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 657, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(contentPane);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomePeca = new JLabel("Nome Peça");
		lblNomePeca.setBounds(14, 48, 117, 14);
		contentPane.add(lblNomePeca);
		
		txtNomePeca = new JTextField();
		txtNomePeca.setBounds(10, 73, 621, 34);
		contentPane.add(txtNomePeca);
		txtNomePeca.setColumns(10);
		
		JLabel lblMarcaPea = new JLabel("Marca Peça");
		lblMarcaPea.setBounds(14, 118, 117, 14);
		contentPane.add(lblMarcaPea);
		
		txtMarcaPeca = new JTextField();
		txtMarcaPeca.setColumns(10);
		txtMarcaPeca.setBounds(10, 143, 621, 34);
		contentPane.add(txtMarcaPeca);
		
		JLabel lblMarcaPea_1 = new JLabel("Marca Peça");
		lblMarcaPea_1.setBounds(14, 153, 117, 14);
		contentPane.add(lblMarcaPea_1);
		
		txtQtdEstoque = new JTextField();
		txtQtdEstoque.setColumns(10);
		txtQtdEstoque.setBounds(14, 223, 49, 34);
		contentPane.add(txtQtdEstoque);
		
		JLabel lblQuantidadeEmEstoque = new JLabel("Quantidade em Estoque");
		lblQuantidadeEmEstoque.setBounds(18, 198, 117, 14);
		contentPane.add(lblQuantidadeEmEstoque);
		
		JButton Salvar = new JButton("Salvar");
		Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nomePeca = txtNomePeca.getText();
					String marcaPeca = txtMarcaPeca.getText();
					int qtdEstoque = Integer.parseInt(txtQtdEstoque.getText());
					
					if(peca == null) {
						peca = new Peca(nomePeca, marcaPeca, qtdEstoque);
					} else {
						peca.setMarcaPeca(marcaPeca);
						peca.setNomePeca(nomePeca);
						peca.setQtdEstoque(qtdEstoque);
					}
					
					service.salvar(peca);
					JOptionPane.showMessageDialog(contentPane, "Peça inserida com sucesso!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
				}
			}
		});
		Salvar.setBounds(260, 332, 141, 34);
		contentPane.add(Salvar);
	}
	
	public void setPeca(Peca peca) {
		this.peca = peca;
		txtMarcaPeca.setText(peca.getMarcaPeca());
		txtNomePeca.setText(peca.getNomePeca());
		txtQtdEstoque.setText(String.valueOf(peca.getQtdEstoque()));
	}
}
