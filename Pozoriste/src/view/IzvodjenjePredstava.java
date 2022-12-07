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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import common.Helper;
import common.HelperPrikaz;
import java.awt.Color;

public class IzvodjenjePredstava extends JFrame {

	static Helper Helper = new Helper();
	HelperPrikaz HelperPrikaz = new HelperPrikaz();
	
	private JPanel contentPane;
	private JTable tableIzvodjenjeP;
	private JTable tableSale;
	private JTable tablePredstave;
	private JComboBox cbxIDP;
	private JComboBox cbxIDS;
	private JComboBox cbxDani;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzvodjenjePredstava frame = new IzvodjenjePredstava();
					frame.setVisible(true);
					IzvodjenjePredstava CW = (IzvodjenjePredstava) Helper.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void getIDP() {
		
		String sqlIDP = "SELECT * FROM predstave";
		Connection connect = Helper.DBSetup();
		
		try {
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sqlIDP);
			while(rs.next()) {
				cbxIDP.addItem(rs.getString("Naziv"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void PrikazPodataka() {
		
		Connection connect = Helper.DBSetup();
		String query = "SELECT i.ID, CONCAT_WS('',p.Naziv) as 'Naziv predstave', CONCAT_WS('',s.Ime) as 'Naziv sale', i.Dan  FROM izvodjenje_predstave i, predstave p, sala s WHERE i.ID_S = s.ID AND i.ID_P = p.ID";
		String query2 = "SELECT s.ID, CONCAT_WS('',s.Ime) as 'Naziv sale', CONCAT_WS('',p.naziv) as 'Naziv pozorista' FROM sala s, pozoriste p WHERE s.ID_P = p.ID";
		String query3 = "SELECT *  FROM predstave";
		
		HelperPrikaz.PopuniTabelu(query, tableIzvodjenjeP);
		HelperPrikaz.PopuniTabelu(query2, tableSale);
		HelperPrikaz.PopuniTabelu(query3, tablePredstave);
		
	}
	
	
	public void getIDS() {
		
		Connection connect = Helper.DBSetup();
		String sqlIDS = "SELECT * FROM sala";
		
		try {
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sqlIDS);
			while(rs.next()) {
				cbxIDS.addItem(rs.getString("Ime"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initComponents() {
		setTitle("Izvodjenje predstava");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdPredstave = new JLabel("ID sale");
		lblIdPredstave.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdPredstave.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblIdPredstave.setBounds(10, 78, 151, 30);
		contentPane.add(lblIdPredstave);
		
		JLabel lblIdSale = new JLabel("ID predstave");
		lblIdSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdSale.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblIdSale.setBounds(10, 158, 151, 30);
		contentPane.add(lblIdSale);
		
		JLabel lblDan= new JLabel("Dan Izvodjenja");
		lblDan.setHorizontalAlignment(SwingConstants.CENTER);
		lblDan.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDan.setBounds(10, 238, 151, 30);
		contentPane.add(lblDan);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(550, 30, 424, 119);
		contentPane.add(scrollPane);
		
		tableIzvodjenjeP = new JTable();
		scrollPane.setViewportView(tableIzvodjenjeP);
		
		JLabel lblNewLabel = new JLabel("Izvodjenje Predstava");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(699, 0, 142, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sale");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(699, 149, 142, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Predstave");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(699, 298, 142, 30);
		contentPane.add(lblNewLabel_2);
		
		cbxDani = new JComboBox();
		cbxDani.setModel(new DefaultComboBoxModel(new String[] {"Ponedeljak", "Utorak", "Sreda", "Cetvrtak", "Petak", "Subota", "Nedelja"}));
		cbxDani.setSelectedIndex(0);
		cbxDani.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cbxDani.setBounds(245, 238, 151, 30);
		contentPane.add(cbxDani);
		
		cbxIDP = new JComboBox();
		cbxIDP.setBounds(245, 158, 151, 30);
		contentPane.add(cbxIDP);
		
		cbxIDS = new JComboBox();
		cbxIDS.setBounds(245, 78, 151, 30);
		contentPane.add(cbxIDS);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(550, 179, 424, 119);
		contentPane.add(scrollPane_1);
		
		tableSale = new JTable();
		scrollPane_1.setViewportView(tableSale);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(550, 328, 424, 119);
		contentPane.add(scrollPane_2);
		
		tablePredstave = new JTable();
		scrollPane_2.setViewportView(tablePredstave);
		
		JButton btnNPregledBaze = new JButton("Pregled podataka");
		btnNPregledBaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				DbPreview PregBazePage = new DbPreview();
				PregBazePage.setVisible(true);
				DbPreview CW = (DbPreview) Helper.CenterWindow(PregBazePage);;
				
			}
		});
		btnNPregledBaze.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNPregledBaze.setBounds(10, 394, 151, 30);
		contentPane.add(btnNPregledBaze);
		
		JButton btnSaveChanges = new JButton("Sacuvaj");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ID_S = cbxIDS.getSelectedItem().toString();
				String ID_P = cbxIDP.getSelectedItem().toString();
				String Dan = cbxDani.getSelectedItem().toString();
				String sql = "INSERT INTO izvodjenje_predstave (ID_P,ID_S,Dan) VALUES ('"+Integer.valueOf(ID_P)+"', '"+Integer.valueOf(ID_S)+"', '"+Dan+"')";
				
				Connection connect = Helper.DBSetup();
				
				try {
					Statement stm = connect.createStatement();
				    stm.execute(sql);
				    
				    PrikazPodataka();
				    
				    connect.close();
				    
	
				}   
				catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
				
				
			}
		});
		
		btnSaveChanges.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSaveChanges.setBounds(380, 394, 151, 30);
		contentPane.add(btnSaveChanges);
		
		JButton btnNewButton = new JButton("Nazad");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    Predstave PredstavePage = new Predstave();
				PredstavePage.setVisible(true);
				Predstave CW = (Predstave) Helper.CenterWindow(PredstavePage);			
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(0, 0, 89, 23);
		contentPane.add(btnNewButton);
	}

	
	public IzvodjenjePredstava() {
		initComponents();
		getIDP();
		getIDS();
		PrikazPodataka();
	}
}
