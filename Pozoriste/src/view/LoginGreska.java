package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.Helper;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginGreska extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	static Helper Helper = new Helper();
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginGreska dialog = new LoginGreska();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginGreska() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.CYAN);
		contentPanel.setForeground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pogresno kolisnicko ime ili lozinka!!");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 424, 57);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\r\nAko nemate nalog registrujte se.\r\n");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 79, 424, 64);
		contentPanel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("REGISTRUJ SE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
		    	Registracija RegPage = new Registracija();
		    	RegPage.setVisible(true);
		    	Registracija CW = (Registracija) Helper.CenterWindow(RegPage);
			}
		});
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(254, 214, 170, 36);
		contentPanel.add(btnNewButton);
		
		JButton btnPokusajPonovo = new JButton("POKUSAJ PONOVO");
		btnPokusajPonovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    Login LoginPage = new Login();
			    LoginPage.setVisible(true);
			    Login CW = (Login) Helper.CenterWindow(LoginPage);
			}
		});
		btnPokusajPonovo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPokusajPonovo.setBackground(Color.CYAN);
		btnPokusajPonovo.setBorder(null);
		btnPokusajPonovo.setBounds(10, 214, 170, 36);
		contentPanel.add(btnPokusajPonovo);
	}
}
