package br.com.senai.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.senai.core.domain.Usuario;
import br.com.senai.core.service.UsuarioService;

public class ViewLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtSenha;
	
	private UsuarioService service;
	
	private Usuario usuario;

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
		service = new UsuarioService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(contentPane);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(10, 44, 83, 14);
		contentPane.add(lblUsuario);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(140, 11, 1, 1);
		contentPane.add(layeredPane);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(76, 42, 335, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);	
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(10, 97, 83, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(76, 96, 335, 20);
		contentPane.add(txtSenha);
		
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nomeUsuario = txtUsuario.getText();
					
					String senhaUsuario = txtSenha.getText();
					
					if(usuario == null) {
						usuario = new Usuario(nomeUsuario, senhaUsuario);
					} else {
						usuario.setNomeUsuario(nomeUsuario);
						usuario.setSenhaUsuario(senhaUsuario);
					}
						
					service.autenticar(usuario);
					if(usuario.isTrue() == true) {
						JOptionPane.showMessageDialog(contentPane, "Usuario logado com sucesso!");
						ViewPrincipal view = new ViewPrincipal();
						view.setVisible(true);
						
					} 
					
					
				} catch(Exception e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
				}
			}
		});
		btnNewButton.setBounds(150, 212, 123, 38);
		contentPane.add(btnNewButton);
	}
}
