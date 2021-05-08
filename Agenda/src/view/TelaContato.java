package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaContato extends JFrame {

	private JPanel contentPane;
	private JLabel lblStatus;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEmail;
	private JButton btnUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaContato frame = new TelaContato();
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
	public TelaContato() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});

		setTitle("Agenda de contatos");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaContato.class.getResource("/icones/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(TelaContato.class.getResource("/icones/dberror.png")));
		lblStatus.setBounds(392, 218, 32, 32);
		contentPane.add(lblStatus);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(35, 11, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(35, 45, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Fone");
		lblNewLabel_2.setBounds(35, 86, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setBounds(35, 130, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(91, 8, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(91, 42, 236, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtFone = new JTextField();
		txtFone.setBounds(91, 83, 147, 20);
		contentPane.add(txtFone);
		txtFone.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(91, 127, 236, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarContato();
			}
		});
		btnPesquisar.setEnabled(false);
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setBorder(null);
		btnPesquisar.setBackground(SystemColor.control);
		btnPesquisar.setToolTipText("Pesquisar Contato");
		btnPesquisar.setIcon(new ImageIcon(TelaContato.class.getResource("/icones/read.png")));
		btnPesquisar.setBounds(346, 21, 64, 64);
		contentPane.add(btnPesquisar);

		btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirContato();
			}
		});
		btnCreate.setEnabled(false);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setToolTipText("Adicionar Contato");
		btnCreate.setBorder(null);
		btnCreate.setBackground(SystemColor.control);
		btnCreate.setForeground(SystemColor.control);
		btnCreate.setIcon(new ImageIcon(TelaContato.class.getResource("/icones/create.png")));
		btnCreate.setBounds(91, 168, 96, 64);
		contentPane.add(btnCreate);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarContato();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setToolTipText("Editor de Contato");
		btnUpdate.setBorder(null);
		btnUpdate.setIcon(new ImageIcon(TelaContato.class.getResource("/icones/update.png")));
		btnUpdate.setBackground(SystemColor.control);
		btnUpdate.setBounds(181, 168, 64, 64);
		contentPane.add(btnUpdate);

		btnDElete = new JButton("");
		btnDElete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarContato();
			}
		});
		btnDElete.setEnabled(false);
		btnDElete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDElete.setToolTipText("Excluir Contato");
		btnDElete.setBorder(null);
		btnDElete.setBackground(SystemColor.control);
		btnDElete.setIcon(new ImageIcon(TelaContato.class.getResource("/icones/delete.png")));
		btnDElete.setBounds(263, 168, 64, 64);
		contentPane.add(btnDElete);
	}// fim do construtor

	DAO dao = new DAO();
	private JButton btnPesquisar;
	private JButton btnCreate;
	private JButton btnDElete;

	/**
	 * status sa conexão
	 */
	private void status() {
		try {
			Connection con = dao.conectar();
			// status
			// System.out.println(con);
			// trocando iconene do banco (status da conexãio)
			if (con != null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbok.png")));
				btnPesquisar.setEnabled(true);

			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dberror.png")));
			}
			// encerrar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Selecionar contato
	 */
	private void selecionarContato() {
		String read = "select * from contatos where nome= ?";
		try {
			Connection con = dao.conectar();
			// preparar a instrução sql
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parametro (?) pelo nome do contato
			pst.setString(1, txtNome.getText());
			// resultado
			ResultSet rs = pst.executeQuery();
			// se existir um contato correspondente
			if (rs.next()) {
				txtId.setText(rs.getString(1));
				txtFone.setText(rs.getString(3));
				txtEmail.setText(rs.getString(4));
				btnUpdate.setEnabled(true);
				btnDElete.setEnabled(true);
				btnPesquisar.setEnabled(false);
			} else {
				// criar uma caixa de mensagem no java
				JOptionPane.showMessageDialog(null, "Contato inexistente");
				btnCreate.setEnabled(true);
				btnPesquisar.setEnabled(false);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Inserir um novo contato CRUD Create
	 */
	private void inserirContato() {
		// validalão dos campos
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o nome do contato");

		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o Fone do contato");

		} else if (txtNome.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "o campo não pode ter mais que 50 caracteres");

		} else if (txtFone.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "o campo não pode ter mais que 15 caracteres");

		} else if (txtEmail.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "o campo não pode ter mais que 50 caracteres");
		} else {
			String create = "insert into contatos (nome,fone,email )values (?,?,?)";

			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				// sub os param.(?,?,?) pelo cont de caixa
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				pst.execute();
				JOptionPane.showMessageDialog(null, "Contato adicionado");
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Editar contato CRUD Update
	 */
	private void alterarContato() {
		// validalão dos campos
				if (txtNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "preencha o nome do contato");

				} else if (txtFone.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "preencha o Fone do contato");

				} else if (txtNome.getText().length() > 50) {
					JOptionPane.showMessageDialog(null, "o campo não pode ter mais que 50 caracteres");

				} else if (txtFone.getText().length() > 15) {
					JOptionPane.showMessageDialog(null, "o campo não pode ter mais que 15 caracteres");

				} else if (txtEmail.getText().length() > 50) {
					JOptionPane.showMessageDialog(null, "o campo não pode ter mais que 50 caracteres");
				} else {
					String update = "update contatos set nome=?,fone=?,email=? where idcon=?";

					try {
						Connection con = dao.conectar();
						PreparedStatement pst = con.prepareStatement(update);
						// sub os param.(?,?,?) pelo cont de caixa
						pst.setString(1, txtNome.getText());
						pst.setString(2, txtFone.getText());
						pst.setString(3, txtEmail.getText());
						pst.setString(4, txtId.getText());
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Contato editado com sucesso");
						con.close();
						limpar();
					} catch (Exception e) {
						System.out.println(e);
					}
				}
	}

	/**
	 * Excruir contato CRUD Delete
	 */
	private void deletarContato() {
       String delete = "delete from contatos where idcon=?";
       
       //confimação de exclusão contato
       int confirma = JOptionPane.showConfirmDialog(null, 
    		   "Confirma a exclusão deste contato?","Atenção!",JOptionPane.YES_NO_OPTION);
       if (confirma == JOptionPane.YES_OPTION){
    	   try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, txtId.getText());
			pst.executeUpdate();
			limpar();
			JOptionPane.showMessageDialog(null, "Contato excluido");
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
       }else {
    	 limpar();
       }
	}

	/**
	 * Limpar campos e configurar botões
	 */
	private void limpar() {
		txtId.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);
		btnUpdate.setEnabled(false);
		btnCreate.setEnabled(false);
		btnDElete.setEnabled(false);
		btnPesquisar.setEnabled(true);

	}
}
