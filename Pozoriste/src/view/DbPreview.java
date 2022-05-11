package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import common.Helper;
import common.HelperPrikaz;
import java.awt.Color;

public class DbPreview extends JFrame {

	static Helper Helper = new Helper();
	HelperPrikaz HelperPrikaz = new HelperPrikaz();
	
	private JPanel contentPane;		
	private JTable tableData;
	private int idForDelete;
	private String TableName;
	private String Last;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DbPreview frame = new DbPreview();
					frame.setVisible(true);
					DbPreview  CW = (DbPreview) Helper.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public void PregledPozorista() {
		
		String query = "SELECT p.ID, CONCAT_WS('',p.naziv) as 'Naziv pozorista', adresa, CONCAT_WS('',m.naziv) as 'Naziv mesta' FROM pozoriste p , mesto m WHERE p.ptt=m.ptt ORDER BY p.ID";
			
		HelperPrikaz.PopuniTabelu(query,tableData);
		
		Last = "pozorista";
		TableName = "pozoriste";
	}
	

	public void PregledSala() {
		
		String query = "SELECT s.ID, CONCAT_WS('',s.Ime) as 'Naziv sale', CONCAT_WS('',p.naziv) as 'Naziv pozorista' FROM sala s, pozoriste p WHERE s.ID_P = p.ID ORDER BY s.ID";
		
		HelperPrikaz.PopuniTabelu(query,tableData);
		
		Last = "sale";
		TableName = "sala";
	}

	
	public void PregledPredstava() {
		
		String query = "SELECT *  FROM predstave ORDER BY ID";
		
		HelperPrikaz.PopuniTabelu(query, tableData);
		
		Last = "predstave";
		TableName = "predstave";
	}

	
	public void PregledIzvodjenja() {

		String query = "SELECT i.ID, CONCAT_WS('',p.Naziv) as 'Naziv predstave', CONCAT_WS('',s.Ime) as 'Naziv sale', i.Dan  FROM izvodjenje_predstave i, predstave p, sala s WHERE i.ID_S = s.ID AND i.ID_P = p.ID ORDER BY i.ID";
		
		HelperPrikaz.PopuniTabelu(query, tableData);
		
		Last = "izvodjenje";
		TableName = "izvodjenje_predstave";
	}
	
	
	public void PregledRezervacija() {

		String query = "SELECT CONCAT_WS('',r.ID) as 'ID rezervacije', CONCAT_WS(' ',k.Ime, k.Prezime) as 'Ime i prezime',  CONCAT_WS('',p.Naziv) as 'Naziv predstave' "
				+ "FROM rezervacije r,korisnik k,  predstave p, izvodjenje_predstave i"
				+ " WHERE r.ID_K=k.ID AND r.ID_I = i.ID AND i.ID_P = p.ID "
				+ "ORDER BY r.ID";
		
		HelperPrikaz.PopuniTabelu(query, tableData);
		
		Last = "rezervacija";
		TableName = "rezervacije";
	}
	
	
	public void initComponents() {
		setTitle("Pregled baze ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Pozorista");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				PregledPozorista();	
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(10, 11, 160, 40);
		contentPane.add(btnNewButton);
		
		JButton btnSale = new JButton("Sale");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PregledSala();
			}
		});
		btnSale.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSale.setBounds(206, 11, 160, 40);
		contentPane.add(btnSale);
		
		JButton btnPredstave = new JButton("Predstave");
		btnPredstave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PregledPredstava();			
			}
		});
		btnPredstave.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPredstave.setBounds(407, 11, 160, 40);
		contentPane.add(btnPredstave);
		
		JButton btnIzvodjenjePredstava = new JButton("Izvodjenje predstava");
		btnIzvodjenjePredstava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PregledIzvodjenja();					
			}
		});
		btnIzvodjenjePredstava.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnIzvodjenjePredstava.setBounds(612, 11, 160, 40);
		contentPane.add(btnIzvodjenjePredstava);
		
		JButton btnRezervacija = new JButton("Rezervacije");
		btnRezervacija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PregledRezervacija();
			}
		});
		btnRezervacija.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnRezervacija.setBounds(814, 11, 160, 40);
		contentPane.add(btnRezervacija);
		
		JButton btnNewButton_1 = new JButton("Refresh");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Last == "pozorista") {
					PregledPozorista();
				}
				else if(Last == "sale") {
					PregledSala();
				}
				else if(Last == "predstave") {
					PregledPredstava();
				}
				else if(Last == "izvodjenje") {
					PregledIzvodjenja();
				}
				else if(Last == "rezervacija") {
					PregledRezervacija();
				}	        										
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.setBounds(885, 99, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Delete");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String query = "DELETE FROM "+TableName+" WHERE id = "+idForDelete+"";
					Connection connect = Helper.DBSetup();
				try {					
					Statement st = connect.createStatement();
					st.execute(query);
											
					if(Last == "pozorista") {
						PregledPozorista();
					}
					else if(Last == "sale") {
						PregledSala();
					}
					else if(Last == "predstave") {
						PregledPredstava();
					}
					else if(Last == "izvodjenje") {
						PregledIzvodjenja();
					}
					else if(Last == "rezervacija") {
						PregledRezervacija();
					}
																							
					connect.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(885, 133, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 868, 351);
		contentPane.add(scrollPane);
		
		tableData = new JTable();
		tableData.setBackground(Color.WHITE);
		tableData.setFont(new Font("Times New Roman", Font.BOLD, 14));
		scrollPane.setViewportView(tableData);
		tableData.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tableData.setRowSelectionAllowed(true);
		tableData.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));			
		
		JButton btnNewButton_2 = new JButton("Poceta");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Pozorista PozoristaPage = new Pozorista();
		    	PozoristaPage.setVisible(true);
		    	Pozorista CW = (Pozorista) Helper.CenterWindow(PozoristaPage);
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_2.setBounds(885, 395, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Log out");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login LoginPage = new Login();
				LoginPage.setVisible(true);
				Login CW = (Login) Helper.CenterWindow(LoginPage);
			}
		});
		btnNewButton_2_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_2_1.setBounds(885, 427, 89, 23);
		contentPane.add(btnNewButton_2_1);
		
		ListSelectionModel rowSelectionModel = tableData.getSelectionModel();

		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {
		        			        
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        		        
		        if(lsm.isSelectionEmpty()) {
		        	//JOptionPane.showMessageDialog(null, "No Selection");
		        }
		        else {
		        	int selRow = tableData.getSelectedRow();
		        	idForDelete = Integer.parseInt(tableData.getModel().getValueAt(selRow, 0).toString());
		        	//JOptionPane.showMessageDialog(null,String.valueOf(idForDelete));
		        }
		      }

		    });						
	}						

	
	public DbPreview() {
		initComponents();
	}


}
