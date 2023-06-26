package br.com.senai.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.senai.core.domain.Mecanico;
import br.com.senai.core.service.MecanicoService;

public class ViewCadastroMecanico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeMecanico;
	private Mecanico mecanico;
	private MecanicoService service;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCadastroMecanico frame = new ViewCadastroMecanico();
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
	public ViewCadastroMecanico() {
		service = new MecanicoService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(contentPane);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomeMecanico_1 = new JLabel("Nome Mecânico");
		lblNomeMecanico_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNomeMecanico_1.setBounds(36, 142, 210, 19);
		contentPane.add(lblNomeMecanico_1);
		
		txtNomeMecanico = new JTextField();
		txtNomeMecanico.setColumns(10);
		txtNomeMecanico.setBounds(212, 143, 265, 19);
		contentPane.add(txtNomeMecanico);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = txtNomeMecanico.getText();
					if(mecanico == null) {
						mecanico = new Mecanico(nome);
					} else {
						mecanico.setNomeMecanico(nome);
					}
					service.salvar(mecanico);
					JOptionPane.showMessageDialog(contentPane, "Mecânico cadastrado com sucesso!");
				} catch(Exception e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
				}
			}
		});
		btnNewButton.setBounds(319, 439, 166, 61);
		contentPane.add(btnNewButton);
		
	
		
		 
	}
	
	public void setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
		this.txtNomeMecanico.setText(mecanico.getNomeMecanico());
		
	}
}
