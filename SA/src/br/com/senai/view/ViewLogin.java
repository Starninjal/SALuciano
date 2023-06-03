package br.com.senai.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class ViewLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogin frame = new ViewLogin();
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
	public ViewLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(10, 43, 83, 14);
		contentPane.add(lblUsuario);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(140, 11, 1, 1);
		contentPane.add(layeredPane);
		
		textField = new JTextField();
		textField.setBounds(76, 42, 335, 20);
		contentPane.add(textField);
		textField.setColumns(10);	
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(10, 97, 83, 14);
		contentPane.add(lblSenha);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(76, 96, 335, 20);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.setBounds(150, 212, 123, 38);
		contentPane.add(btnNewButton);
	}
}
