package view;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;


import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import common.Helper;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	static Helper Helper = new Helper();
	
	
	private JPanel contentPane;
	private JTextField txtKorisnickoime;
	private JPasswordField txtLozinka;
	public int ID;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					Login  CW =  (Login) Helper.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void initComponents() {

		setBackground(new Color(255, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 358);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DOBRODOSLI");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 111, 354, 32);
		contentPane.add(lblNewLabel);
		
		JLabel KorisnickoIme = new JLabel("KORISNICKO IME");
		KorisnickoIme.setBackground(new Color(128, 0, 0));
		KorisnickoIme.setFont(new Font("Times New Roman", Font.BOLD, 14));
		KorisnickoIme.setHorizontalAlignment(SwingConstants.CENTER);
		KorisnickoIme.setBounds(10, 180, 135, 17);
		contentPane.add(KorisnickoIme);
		
		JLabel Lozinka = new JLabel("LOZINKA");
		Lozinka.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Lozinka.setHorizontalAlignment(SwingConstants.CENTER);
		Lozinka.setBounds(10, 225, 135, 17);
		contentPane.add(Lozinka);
		
		txtLozinka = new JPasswordField();
		txtLozinka.setBounds(197, 224, 147, 18);
		contentPane.add(txtLozinka);
		
		txtKorisnickoime = new JTextField();
		txtKorisnickoime.setBounds(197, 179, 147, 18);
		contentPane.add(txtKorisnickoime);
		txtKorisnickoime.setColumns(10);
		
				
		
		JButton btnLogin = new JButton("ULOGUJ SE");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection connect = Helper.DBSetup();
				
				try{			      				      				      
				      String username = txtKorisnickoime.getText();
				      char[] password = txtLozinka.getPassword();
				      String pw = "";
				      for(int i = 0; i < password.length; i++ ) {
				    	  pw+=password[i];
				      }
				      Statement stm = connect.createStatement();
				      String sqlA = "select * from korisnik where Username = '"+username+"' and Password = '"+pw+"' AND Role = 'Admin'";
				      ResultSet rsA = stm.executeQuery(sqlA);
				      
				      //Admin interface
				      if(rsA.next()) {
				    	 dispose();
				    	 Pozorista PozoristaPage = new Pozorista();
				    	 PozoristaPage.setVisible(true);
				    	 Pozorista CW = (Pozorista) Helper.CenterWindow(PozoristaPage);
				    	 
				      }
				      //User interface
				      else {
					     String sql = "select * from korisnik where Username = '"+username+"' and Password = '"+pw+"'";
					     ResultSet rs = stm.executeQuery(sql);			     
					     
					     if(rs.next()) {	
					    	 
						     ID = rs.getInt("ID");
						     
					    	 dispose();
					    	 KorisnikPregled KorisnikPage = new KorisnikPregled(ID);
					    	 KorisnikPage.setVisible(true);
					    	 KorisnikPregled CW = (KorisnikPregled) Helper.CenterWindow(KorisnikPage);
					    	 
				    	  }
				    	  else {
				    		  dispose();
							  LoginGreska LogGreska = new LoginGreska();
							  LogGreska.setVisible(true);
							  LoginGreska CW = (LoginGreska) Helper.CenterWindow(LogGreska);
							  
							  KorisnickoIme.setText("");
							  Lozinka.setText("");
				    	  }
				      }
				      	
				      connect.close();
				    }
					catch(Exception eis){ System.out.println(e);}
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLogin.setBounds(10, 282, 135, 25);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTRUJ SE");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		    	Registracija RegPage = new Registracija();
		    	RegPage.setVisible(true);
		    	Registracija CW = (Registracija) Helper.CenterWindow(RegPage);
			}
		});
		btnRegister.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnRegister.setBounds(197, 284, 147, 23);
		contentPane.add(btnRegister);
		
		JLabel pngZastava = new JLabel("");
		pngZastava.setIcon(new ImageIcon(Login.class.getResource("/resources/MicrosoftTeams-image (1).png")));
		pngZastava.setBounds(214, 11, 140, 93);
		contentPane.add(pngZastava);
		
		JLabel pngLogo = new JLabel("");
		pngLogo.setIcon(new ImageIcon(Login.class.getResource("/resources/MicrosoftTeams-image (2).png")));
		pngLogo.setBounds(0, 11, 106, 93);
		contentPane.add(pngLogo);
	
	}
	
	public Login() {
		setForeground(Color.BLACK);
		setTitle("POZORISTA SRBIJE");
		setResizable(false);
		initComponents();
		
	}
}
