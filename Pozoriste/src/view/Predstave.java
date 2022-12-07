package view;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import common.Helper;
import common.HelperPrikaz;
import java.awt.Color;

public class Predstave extends JFrame {

	static Helper Helper = new Helper();
	HelperPrikaz HelperPrikaz = new HelperPrikaz();
	
	private JPanel contentPane;
	private JTable tablePredstave;
	private JTextField txtNazivP;
	private JTextField txtOcena;
	private JTextField txtReditelj;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Predstave frame = new Predstave();
					frame.setVisible(true);
					Predstave CW = (Predstave) Helper.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void PrikazPodataka() {
	
		String query = "SELECT *  FROM predstave";
		
		HelperPrikaz.PopuniTabelu(query, tablePredstave);
		
	}
	

	public void initComponents() {
		setTitle("Predstave");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(550, 11, 424, 439);
		contentPane.add(scrollPane);
		
		tablePredstave = new JTable();
		scrollPane.setViewportView(tablePredstave);
		
		txtNazivP = new JTextField();
		txtNazivP.setBounds(245, 78, 151, 30);
		contentPane.add(txtNazivP);
		txtNazivP.setColumns(10);
		
		txtOcena = new JTextField();
		txtOcena.setColumns(10);
		txtOcena.setBounds(245, 158, 151, 30);
		contentPane.add(txtOcena);
		
		txtReditelj = new JTextField();
		txtReditelj.setColumns(10);
		txtReditelj.setBounds(245, 238, 151, 30);
		contentPane.add(txtReditelj);
		
		JLabel lblNewLabel = new JLabel("Naziv predstave");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 78, 151, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblOcena = new JLabel("Ocena");
		lblOcena.setHorizontalAlignment(SwingConstants.CENTER);
		lblOcena.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOcena.setBounds(10, 158, 151, 30);
		contentPane.add(lblOcena);
		
		JLabel lblReditelj = new JLabel("Reditelj");
		lblReditelj.setHorizontalAlignment(SwingConstants.CENTER);
		lblReditelj.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblReditelj.setBounds(10, 238, 151, 30);
		contentPane.add(lblReditelj);
		
		JButton btnNPregledBaze = new JButton("Pregled podataka");
		btnNPregledBaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				DbPreview PregBazePage = new DbPreview();
				PregBazePage.setVisible(true);
				DbPreview CW = (DbPreview) Helper.CenterWindow(PregBazePage);
				
			}
		});
		btnNPregledBaze.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNPregledBaze.setBounds(10, 394, 151, 30);
		contentPane.add(btnNPregledBaze);
		
		JButton btnPreskoci = new JButton("Preskoci >>");
		btnPreskoci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				IzvodjenjePredstava IzvodjenjePage = new IzvodjenjePredstava();
				IzvodjenjePage.setVisible(true);
				IzvodjenjePredstava CW = (IzvodjenjePredstava)  Helper.CenterWindow(IzvodjenjePage);
				
			}
		});
		btnPreskoci.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPreskoci.setBounds(196, 394, 151, 30);
		contentPane.add(btnPreskoci);
		
		JButton btnDalje = new JButton("Dalje >>");
		btnDalje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String NazivP = txtNazivP.getText();
				String Ocena = txtOcena.getText();
				String Reditelj = txtReditelj.getText();
				
				String sql = "INSERT INTO predstave (Naziv,Ocena,Reditelj) VALUES ('"+NazivP+"', '"+Float.parseFloat(Ocena)+"', '"+Reditelj+"')";
				Connection connect = Helper.DBSetup();
				
				try { 
					Statement stm = connect.createStatement();
				    stm.execute(sql);
				    
				    dispose();
					IzvodjenjePredstava IzvodjenjePage = new IzvodjenjePredstava();
					IzvodjenjePage.setVisible(true);
					IzvodjenjePredstava CW = (IzvodjenjePredstava)  Helper.CenterWindow(IzvodjenjePage);
				    
				    connect.close();
				    
	
				}   
				catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
				
				
			}
		});
		
		btnDalje.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnDalje.setBounds(380, 394, 151, 30);
		contentPane.add(btnDalje);
		
		JButton btnNewButton = new JButton("Nazad");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Sale SalePage = new Sale();
				SalePage.setVisible(true);
				Sale CW = (Sale) Helper.CenterWindow(SalePage);			
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(0, 0, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}
	

	public Predstave() {
		initComponents();
		PrikazPodataka();
	}
}
