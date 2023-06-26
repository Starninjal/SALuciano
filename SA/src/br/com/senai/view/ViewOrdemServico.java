package br.com.senai.view;

	import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.senai.core.dao.ManagerDb;
import br.com.senai.core.domain.Atendente;
import br.com.senai.core.domain.Cliente;
import br.com.senai.core.domain.Mecanico;
import br.com.senai.core.domain.Peca;
import br.com.senai.core.domain.Servico;
import br.com.senai.core.service.AtendenteService;
import br.com.senai.core.service.ClienteService;
import br.com.senai.core.service.MecanicoService;
import br.com.senai.core.service.OrdemServicoService;
import br.com.senai.core.service.PecaService;

public class ViewOrdemServico extends JFrame {

	private JPanel contentPane;
	private JComboBox<Cliente> cbCliente;
	private JComboBox<Atendente> cbAtendente;
	private JComboBox<Mecanico> cbMecanico;
	private AtendenteService atendenteService;
	private JLabel lblCliente;
	private ClienteService clienteService;
	private MecanicoService mecanicoService;	
	private ArrayList<Integer> pecas = new ArrayList<>();
	private int qtdVezes = 0;
	private String[] arrayPecas;
	private Integer[] arrayPeca;
	
	
	DefaultListModel dm = new DefaultListModel<Cliente>();
	private JTextField txtNomeServico;
	private JLabel lblDataConclusao;

	private JLabel lblNomeProprietario;
	private JLabel lblTelefoneDoProprietrio;
	private OrdemServicoService service;
	private int qtdVezesOrdem = 0;

	private JTextField txtTelefone;
	private JTextField txtNomeProprietario;
	private JTextField txtPlaca;
	private JLabel lblPlacaDoAutomvel;
	private JLabel lblHoraAbertura;
	private JFormattedTextField ftfHoraAbertura;
	private JLabel lblHoraConclusao;
	private JFormattedTextField ftfHoraConclusao;
	private Servico ordemServico;
	private LocalDateTime dataHoraDeAbertura;
	private LocalDateTime dataHoraConclusao;
	private PecaService servicePeca;
	private JList<Peca> listPeca;
	private Integer[] arrayOrdem;	
	DefaultListModel dmPeca = new DefaultListModel<Peca>();
	
	DefaultListModel dmOrdem = new DefaultListModel<Servico>();
	private JButton btnSalvar_1;
	
	private JList<Servico> listOrdem;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewOrdemServico frame = new ViewOrdemServico();
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
	public ViewOrdemServico() {
		this.service = new OrdemServicoService();
		this.servicePeca = new PecaService();
		setTitle("Cadastro Ordem de serviço");
		this.mecanicoService = new MecanicoService();
		this.clienteService = new ClienteService();
		this.atendenteService = new AtendenteService();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 765, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbAtendente = new JComboBox<Atendente>();
		cbAtendente.setBounds(10, 97, 207, 22);
		contentPane.add(cbAtendente);
		
		 cbMecanico = new JComboBox();
		 cbMecanico.setBounds(481, 97, 205, 22);
		contentPane.add(cbMecanico);
		
		JFormattedTextField ftfDataAbertura = new JFormattedTextField();
		ftfDataAbertura.setBounds(10, 291, 140, 33);
		contentPane.add(ftfDataAbertura);
		
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(375, 165, 311, 28);
		txtTelefone.setColumns(10);
		contentPane.add(txtTelefone);
		
		txtNomeProprietario = new JTextField();
		txtNomeProprietario.setBounds(10, 165, 311, 28);
		txtNomeProprietario.setColumns(10);
		contentPane.add(txtNomeProprietario);
		
		
		
		JFormattedTextField ftfDataConclusao = new JFormattedTextField();
		ftfDataConclusao.setBounds(375, 291, 158, 33);
		contentPane.add(ftfDataConclusao);
		
		lblNomeProprietario = new JLabel("Nome do Proprietário");
		lblNomeProprietario.setBounds(10, 140, 355, 14);
		contentPane.add(lblNomeProprietario);
		
		lblTelefoneDoProprietrio = new JLabel("Telefone do Proprietário");
		lblTelefoneDoProprietrio.setBounds(375, 140, 367, 14);
		contentPane.add(lblTelefoneDoProprietrio);
		
		ftfHoraConclusao = new JFormattedTextField();
		ftfHoraConclusao.setBounds(560, 291, 138, 33);
		contentPane.add(ftfHoraConclusao);
		
		
		txtNomeServico = new JTextField();
		txtNomeServico.setBounds(10, 231, 311, 28);
		contentPane.add(txtNomeServico);
		txtNomeServico.setColumns(10);
		
		txtPlaca = new JTextField();
		txtPlaca.setBounds(375, 231, 311, 28);
		txtPlaca.setColumns(10);
		contentPane.add(txtPlaca);
		
		

		ftfHoraAbertura = new JFormattedTextField();
		ftfHoraAbertura.setBounds(183, 291, 138, 33);
		contentPane.add(ftfHoraAbertura);
		
		 cbCliente = new JComboBox();
		 cbCliente.setBounds(240, 97, 207, 23);
		 cbCliente.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		List<Cliente> clientes = clienteService.listarCliente();
		 		for (int i = 0; i < clientes.size(); i++) {
		 			
					if(cbCliente.getSelectedItem().toString().equals(clientes.get(i).getNomeCliente())) {
						txtNomeProprietario.setText(clientes.get(i).getNomeCliente());
						txtTelefone.setText(clientes.get(i).getTelefone());
					}
				}
		 	}
		 });
		contentPane.add(cbCliente);
		
		try {
			MaskFormatter mascaraDataAbertura = new MaskFormatter("##/##/####");
			mascaraDataAbertura.install(ftfDataAbertura);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
		try {
			MaskFormatter mascaraDataConclusao = new MaskFormatter("##/##/####");
			mascaraDataConclusao.install(ftfDataConclusao);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			MaskFormatter mascaraHoraAbertura = new MaskFormatter("##:##");
			
			mascaraHoraAbertura.install(ftfHoraAbertura);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			MaskFormatter mascaraHoraConclusao = new MaskFormatter("##:##");
			
			mascaraHoraConclusao.install(ftfHoraConclusao);
		} catch (Exception e) {
			// TODO: handle exception
		}
			
			
			
			
			
			
	
			
			
			JButton btnSalvar = new JButton("Salvar");
			btnSalvar.setBounds(10, 435, 140, 33);
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					for (qtdVezes = 0; qtdVezes < listCliente.getSelectedIndices().length; qtdVezes++) {
//					}
//					arrayPecas = new String[qtdVezes];
//					Cliente cliente01  = listCliente.getSelectedValue();
//					JOptionPane.showMessageDialog(null, cliente01.getCpf());
//				
//					List<Cliente> clientess = listCliente.getSelectedValuesList();
//					for (int i = 0; i < qtdVezes; i++) {
//						arrayPecas[i] = clientess.get(i).getCpf();
//					}
//					for (int i = 0; i < arrayPecas.length; i++) {
//						JOptionPane.showMessageDialog(contentPane, arrayPecas[i]);
//					}
//				
//						
//						
//					
//					
//					for (int i = 0; i < ar.length; i++) {
//						
//						JOptionPane.showMessageDialog(contentPane, pecas.get(i));
//					}
					try {
						String dataDeAbertura = ftfDataAbertura.getText();
						String horaDeAbertura = ftfHoraAbertura.getText();
						String dataConclusao = ftfDataConclusao.getText();
						String horaConclusao = ftfHoraConclusao.getText();
						String [] camposData = dataDeAbertura.split("/");
						String [] camposHora = horaDeAbertura.split(":");

						dataHoraDeAbertura =  LocalDateTime.of(
								Integer.parseInt(camposData[2]),
								Integer.parseInt(camposData[1]),
								Integer.parseInt(camposData[0]),
								Integer.parseInt(camposHora[0]),
								Integer.parseInt(camposHora[1]));
						
						String [] camposDaData = dataConclusao.split("/");
						String [] camposDaHora = horaConclusao.split(":");
						dataHoraConclusao = LocalDateTime.of(
								Integer.parseInt(camposDaData[2]),
								Integer.parseInt(camposDaData[1]),
								Integer.parseInt(camposDaData[0]),
								Integer.parseInt(camposDaHora[0]),
								Integer.parseInt(camposDaHora[1]));
						
						
						
						
						
						String nomePlaca = txtPlaca.getText();
						
						Cliente clienteSelecionado = (Cliente) cbCliente.getSelectedItem();
						
						Atendente atendenteSelecionado = (Atendente) cbAtendente.getSelectedItem();
						
						Mecanico mecanicoSelecionado = (Mecanico) cbMecanico.getSelectedItem();
						
						String nomeServico = txtNomeServico.getText();
						
					
						
						
						if(ordemServico == null) {
							ordemServico = new Servico(nomeServico, atendenteSelecionado, clienteSelecionado, mecanicoSelecionado, nomePlaca, dataHoraDeAbertura, dataHoraConclusao);
						} else {
							ordemServico.setNomeServico(nomeServico);
							ordemServico.setAtendente(atendenteSelecionado);
							ordemServico.setCliente(clienteSelecionado);
							ordemServico.setMecanico(mecanicoSelecionado);
							ordemServico.setNomePlaca(nomePlaca);
							ordemServico.setDataAbertura(dataHoraDeAbertura);
							ordemServico.setDataConclusao(dataHoraConclusao);
						}
						
						
						service.salvar(ordemServico);
						
						JOptionPane.showMessageDialog(contentPane, "Ordem de Serviço cadastrada com sucesso!");
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(contentPane, e2.getMessage());
					}
					
					
					

					
					
					
					
					
				}
			});
			contentPane.add(btnSalvar);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Atendente");
		lblNewLabel.setBounds(10, 72, 162, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblMecnico = new JLabel("Mecânico");
		lblMecnico.setBounds(482, 72, 67, 14);
		contentPane.add(lblMecnico);
		carregarComboAtendente();
		carregarComboCliente();
		carregarComboMecanico();
		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(240, 72, 127, 14);
		contentPane.add(lblCliente);
		
		JLabel lblNomeServico = new JLabel("Nome do Serviço");
		lblNomeServico.setBounds(10, 205, 194, 14);
		contentPane.add(lblNomeServico);


		JLabel lblDataAbertura = new JLabel("Data da Abertura");
		lblDataAbertura.setBounds(10, 270, 311, 14);
		contentPane.add(lblDataAbertura);
		
		lblDataConclusao = new JLabel("Data de Conclusao Prevista");
		lblDataConclusao.setBounds(375, 270, 158, 14);
		contentPane.add(lblDataConclusao);
		
		
		
		lblPlacaDoAutomvel = new JLabel("Placa do Automóvel");
		lblPlacaDoAutomvel.setBounds(375, 205, 194, 14);
		contentPane.add(lblPlacaDoAutomvel);
		
		lblHoraAbertura = new JLabel("Hora da Abertura");
		lblHoraAbertura.setBounds(183, 272, 138, 14);
		contentPane.add(lblHoraAbertura);
		
		lblHoraConclusao = new JLabel("Hora da Conclusão");
		lblHoraConclusao.setBounds(560, 272, 138, 14);
		contentPane.add(lblHoraConclusao);
		
		listPeca = new JList();
		listPeca.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listPeca.setBounds(309, 335, 173, 133);
		contentPane.add(listPeca);
		
		btnSalvar_1 = new JButton("Salvar");
		btnSalvar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (qtdVezes = 0; qtdVezes < listPeca.getSelectedIndices().length; qtdVezes++) {
					
				}
				
				for (qtdVezesOrdem = 0; qtdVezesOrdem < listOrdem.getSelectedIndices().length; qtdVezesOrdem++) {
				}
					arrayPeca = new Integer[qtdVezes];
					arrayOrdem = new Integer[qtdVezes];
					Servico ordem01 = listOrdem.getSelectedValue();
					Peca peca01= listPeca.getSelectedValue();
					
				
					List<Peca> pecas = listPeca.getSelectedValuesList();
					for (int i = 0; i < qtdVezes; i++) {
						arrayPeca[i] = listPeca.getSelectedValuesList().get(i).getId();
						JOptionPane.showMessageDialog(contentPane, arrayPeca[i]);
					}
					
					for (int i = 0; i < qtdVezes; i++) {
						
					
					List<Servico> ordens = listOrdem.getSelectedValuesList();
					
					for (int i2 = 0; i < qtdVezesOrdem; i++) {
						arrayOrdem[i] = listOrdem.getSelectedValuesList().get(i).getId();
						JOptionPane.showMessageDialog(contentPane, arrayOrdem[i]); 
					}
					}
					
					
				
					for (int i = 0; i < arrayOrdem.length; i++) {
						Connection conexao = ManagerDb.getInstance().getConexao();
						
						PreparedStatement ps = null;
						
						try {
							ps = conexao.prepareStatement("INSERT INTO servico_has_peca(fk_servico, fk_peca) VALUES (?, ?)");
							ps.setInt(1, arrayOrdem[i]);
							ps.setInt(2, arrayPeca[i]);
							ps.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							ManagerDb.getInstance().fechar(ps);
						}
					}
						
						
					
					
					for (int i = 0; i < arrayPeca.length; i++) {
						
					}
					
					for (int j = 0; j < arrayOrdem.length; j++) {
						
					}
			}
		});
		btnSalvar_1.setBounds(138, 375, 140, 33);
		contentPane.add(btnSalvar_1);
		
		listOrdem = new JList();
		listOrdem.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listOrdem.setBounds(544, 335, 173, 133);
		contentPane.add(listOrdem);
	
		addList();
		
		addListPeca();
		
		addListOrdem();
		
	}

	public void carregarComboAtendente() {
		List<Atendente> atendentes = atendenteService.listarAtendente();
		for(int i = 0; i < atendentes.size(); i++) {
			cbAtendente.addItem(atendentes.get(i));
		}
	}
	
	public void carregarComboCliente() {
		List<Cliente> clientes = clienteService.listarCliente();
		for (int i = 0; i < clientes.size(); i++) {
			cbCliente.addItem(clientes.get(i));
		}
	}
	
	public void carregarComboMecanico() {
		List<Mecanico> mecanicos = mecanicoService.listarMecanico();
		for (int i = 0; i < mecanicos.size(); i++) {
			
			cbMecanico.addItem(mecanicos.get(i));
		}
	}
	
	public void addList() {
		List<Cliente> clientes = clienteService.listarCliente();
		for (int i = 0; i < clientes.size(); i++) {
			dm.addElement(clientes.get(i));
		}
	}
	
	public void setOrdem(Servico ordemServico) {
		List<Servico> ordens = service.listarTodas();
		this.ordemServico = ordemServico;
		this.txtNomeServico.setText(ordemServico.getNomeServico());
		this.txtPlaca.setText(ordemServico.getNomePlaca());
		for (int i = 0; i < ordens.size(); i++) {
			if(ordens.get(i).getCliente().getNomeCliente().equals(ordemServico.getCliente().getNomeCliente())) {
				cbCliente.setSelectedItem(ordens.get(i).getCliente().getNomeCliente());
			}
			if(ordens.get(i).getAtendente().getNomeAtendente().equals(ordemServico.getAtendente().getNomeAtendente())) {
				cbAtendente.setSelectedItem(ordens.get(i).getAtendente().getNomeAtendente());
			}
			
			if(ordens.get(i).getMecanico().getNomeMecanico().equals(ordemServico.getMecanico().getNomeMecanico())) {
				cbMecanico.setSelectedItem(ordens.get(i).getMecanico().getNomeMecanico());
			}
		}
		
		this.txtNomeProprietario.setText(ordemServico.getCliente().getNomeCliente());
		this.txtTelefone.setText(ordemServico.getCliente().getTelefone());
		
		
		
	}
	
	public void addListPeca() {
		List<Peca> pecas = servicePeca.listarTodas();
		listPeca.setModel(dmPeca);
		for (int i = 0; i < pecas.size(); i++) {
			dmPeca.addElement(pecas.get(i));
		}
	}
	
	public void addListOrdem() {
		List<Servico> ordens = service.listarTodas();
		listOrdem.setModel(dmOrdem);
		for (int i = 0; i < ordens.size(); i++) {
			dmOrdem.addElement(ordens.get(i));
		}
	}
}
