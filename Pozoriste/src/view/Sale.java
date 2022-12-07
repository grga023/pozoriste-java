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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import common.Helper;
import common.HelperPrikaz;
import java.awt.Color;

public class Sale extends JFrame {
	
	static Helper Helper = new Helper();
	HelperPrikaz HelperPrikaz = new HelperPrikaz();	
	
	private JTable tablePozorista;
	private JPanel contentPane;
	private JTextField txtNazivS;
	private JTable tableSale;
	private JComboBox cbxId_P;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sale frame = new Sale();
					frame.setVisible(true);
					Sale CW = (Sale) Helper.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void PrikazPodataka() {
		
		String query = "SELECT s.ID, CONCAT_WS('',s.Ime) as 'Naziv sale', CONCAT_WS('',p.naziv) as 'Naziv pozorista' FROM sala s, pozoriste p WHERE s.ID_P = p.ID";
		String query2 = "SELECT p.ID, CONCAT_WS('',p.naziv) as 'Naziv pozorista', adresa, CONCAT_WS('',m.naziv) as 'Naziv mesta' FROM pozoriste p , mesto m WHERE p.ptt=m.ptt";
		
		HelperPrikaz.PopuniTabelu(query, tableSale);
		HelperPrikaz.PopuniTabelu(query2, tablePozorista);

	}
	
	
	public void getIDP() {
	
		Connection connect = Helper.DBSetup();
		String sqlID = "SELECT * FROM pozoriste";
				
		try {
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sqlID);
			while(rs.next()) {
				cbxId_P.addItem(rs.getString("Naziv"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initComponents() {
		//Konekcija sa bazom
		Connection connect = Helper.DBSetup();
		
		setTitle("Sale ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(550, 265, 424, 185);
		getContentPane().add(scrollPane);
		
		tablePozorista = new JTable();
		scrollPane.setViewportView(tablePozorista);
		contentPane.setLayout(null);
		
		//TextBox
		txtNazivS = new JTextField();
		txtNazivS.setBounds(245, 78, 151, 30);
		contentPane.add(txtNazivS);
		txtNazivS.setColumns(10);
		contentPane.add(txtNazivS);
		
		JLabel lblNewLabel = new JLabel("Naziv sale");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 78, 151, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblIdPozorista = new JLabel("ID pozorista");
		lblIdPozorista.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdPozorista.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblIdPozorista.setBounds(10, 158, 151, 30);
		contentPane.add(lblIdPozorista);

		
		cbxId_P = new JComboBox();
		cbxId_P.setBounds(245, 158, 151, 30);
		contentPane.add(cbxId_P);
		
		JButton btnDalje = new JButton("Dalje >>");
		btnDalje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String NazivS = txtNazivS.getText();
				String Id_P = cbxId_P.getSelectedItem().toString();
				
				if(NazivS.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Morate popuniti sva polja.");
				}
				else {	
					try { 											
						Statement stm = connect.createStatement();
					    String sql = "INSERT INTO sala (Ime, ID_P) VALUES ('"+NazivS+"', '"+Integer.valueOf(Id_P)+"');";
					    stm.execute(sql);
					    
					    dispose();
					    Predstave PredstavePage = new Predstave();
						PredstavePage.setVisible(true);
						Predstave CW = (Predstave) Helper.CenterWindow(PredstavePage);
					    
					    connect.close();
					    
		
					}   
					catch(SQLException ex) {
						System.out.println(ex.getMessage());
					}
				}	
			}
		});
		
		btnDalje.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnDalje.setBounds(380, 394, 151, 30);
		contentPane.add(btnDalje);
		
		JButton btnPreskoci = new JButton("Preskoci >>");
		btnPreskoci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Predstave PredstavePage = new Predstave();
				PredstavePage.setVisible(true);
				Predstave CW = (Predstave) Helper.CenterWindow(PredstavePage);
				
			}
		});
		btnPreskoci.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPreskoci.setBounds(196, 394, 151, 30);
		contentPane.add(btnPreskoci);
		
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
		
		JLabel lblNewLabel_1 = new JLabel("Pozorista");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(693, 239, 123, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sale");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(693, 14, 123, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(550, 43, 424, 185);
		contentPane.add(scrollPane_1);
		
		tableSale = new JTable();
		scrollPane_1.setViewportView(tableSale);	
		
		JButton btnNewButton = new JButton("Nazad");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Pozorista PozoristaPage = new Pozorista();
				PozoristaPage.setVisible(true);
				Pozorista CW = (Pozorista) Helper.CenterWindow(PozoristaPage);			
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(0, 0, 89, 23);
		contentPane.add(btnNewButton);
		
	}


	public Sale() {
		initComponents();
		getIDP();
		PrikazPodataka();
	}
}
