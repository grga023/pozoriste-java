package view;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

import java.awt.Font;
import java.sql.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import common.Helper;

public class Registracija extends JFrame {

	static Helper Helper = new Helper();
	
	private JPanel contentPane;
	private JTextField txtIme;
	private JTextField textPrezime;
	private JTextField textEmail;
	private JTextField textKorisnickoIme;
	private JTextField textPassword;
	private JComboBox<String> cbxPtt;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registracija frame = new Registracija();
					frame.setVisible(true);
					Registracija CW = (Registracija) Helper.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public void getPtt() {
		
		String sqlPTT = "SELECT * FROM mesto";
		Connection connect = Helper.DBSetup();
		
		try {
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sqlPTT);
			while(rs.next()) {
				cbxPtt.addItem(rs.getString("ptt"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}


	public void initComponents() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRACIJA");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(76, 11, 191, 55);
		contentPane.add(lblNewLabel);
		
		cbxPtt = new JComboBox<String>();
		cbxPtt.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cbxPtt.setBounds(150, 283, 183, 30);
		contentPane.add(cbxPtt);
		
		JLabel lblNewLabel_1 = new JLabel("IME");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 77, 126, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PREZIME");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 118, 126, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("EMAIL");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 159, 126, 30);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("KORISNICKO IME");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(10, 200, 126, 30);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("LOZINKA");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(10, 241, 126, 30);
		contentPane.add(lblNewLabel_1_4);
									
		//Registracija
		JButton btnRegister = new JButton("REGISTRUJ SE");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ime = txtIme.getText();
				String prezime = textPrezime.getText();
				String email = textEmail.getText();
				String username = textKorisnickoIme.getText();
				String password = textPassword.getText();
				String ptt = cbxPtt.getSelectedItem().toString();
				
				Connection connect = Helper.DBSetup();
				String sql = "INSERT INTO korisnik (Ime, Prezime, Email, Username, Password, PTT) VALUES ('"+ime+"', '"+prezime+"', '"+email+"', '"+username+"', '"+password+"', '"+Integer.valueOf(ptt)+"');";
				
				try { 
					Statement stm = connect.createStatement();
				    stm.execute(sql);
				    
				    JOptionPane.showMessageDialog(null, "Uspesno ste se registrovali!!");
				    
				    dispose();
				    Login LoginPage = new Login();
				    LoginPage.setVisible(true);
				    Login CW = (Login) Helper.CenterWindow(LoginPage);
				    
				    connect.close();
				    
	
				}   
				catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btnRegister.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnRegister.setBounds(10, 349, 137, 30);
		contentPane.add(btnRegister);
		
		
		//Povratak na logovanje
		JButton btnLogin = new JButton("ULOGUJ SE");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		    	Login LoginPage = new Login();
		    	LoginPage.setVisible(true);
		    	Login CW = (Login) Helper.CenterWindow(LoginPage);
			}
		});	
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLogin.setBounds(196, 349, 137, 30);
		contentPane.add(btnLogin);
		
		
		txtIme = new JTextField();
		txtIme.setBounds(150, 77, 183, 30);
		contentPane.add(txtIme);
		txtIme.setColumns(10);
		
		textPrezime = new JTextField();
		textPrezime.setColumns(10);
		textPrezime.setBounds(150, 118, 183, 30);
		contentPane.add(textPrezime);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(150, 159, 183, 30);
		contentPane.add(textEmail);
		
		textKorisnickoIme = new JTextField();
		textKorisnickoIme.setColumns(10);
		textKorisnickoIme.setBounds(150, 200, 183, 30);
		contentPane.add(textKorisnickoIme);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(150, 241, 183, 30);
		contentPane.add(textPassword);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("PTT");
		lblNewLabel_1_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4_1.setBounds(10, 283, 126, 30);
		contentPane.add(lblNewLabel_1_4_1);
		
	}
	

	public Registracija() {
	initComponents();
	getPtt();
	}
	}

