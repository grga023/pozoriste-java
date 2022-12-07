package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import common.Helper;
import common.HelperPrikaz;

import java.awt.Color;

public class Pozorista extends JFrame {

	static Helper Helper = new Helper();
	HelperPrikaz HelperPrikaz = new HelperPrikaz();
	
	private JPanel contentPane;
	private JTable tablePozoriste;
	private JTextField txtNazivP;
	private JTextField txtAdresaP;
	private JComboBox cbxPtt;
	private String NazivP;
	private String AdresaP;
	private int ID;
	private JButton btnIzmeni;

	
	public static void CenterWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pozorista frame = new Pozorista();
					frame.setVisible(true);
					Pozorista CW = (Pozorista) Helper.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void getPtt() {
		
		Connection connect = Helper.DBSetup();
		String sqlPTT = "SELECT * FROM mesto";
		
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
	
	
	public void PrikazPodataka() {
		
		String query = "SELECT p.ID, CONCAT_WS('',p.naziv) as 'Naziv pozorista', adresa, CONCAT_WS('',m.naziv) as 'Naziv mesta' FROM pozoriste p , mesto m WHERE p.ptt=m.ptt";
		
		HelperPrikaz.PopuniTabelu(query, tablePozoriste);
		
	}
	
	public void initComponents() {
		setTitle("Pozorista ");
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
		
		tablePozoriste = new JTable();
		scrollPane.setViewportView(tablePozoriste);
		
		JLabel lblNewLabel = new JLabel("Naziv pozorista");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 78, 151, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblAdresaPozorista = new JLabel("Adresa pozorista");
		lblAdresaPozorista.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresaPozorista.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAdresaPozorista.setBounds(10, 158, 151, 30);
		contentPane.add(lblAdresaPozorista);
		
		JLabel lblPostanskiBroj = new JLabel("Postanski broj");
		lblPostanskiBroj.setHorizontalAlignment(SwingConstants.CENTER);
		lblPostanskiBroj.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPostanskiBroj.setBounds(10, 238, 151, 30);
		contentPane.add(lblPostanskiBroj);
		
		txtNazivP = new JTextField();
		txtNazivP.setBounds(245, 78, 151, 30);
		contentPane.add(txtNazivP);
		txtNazivP.setColumns(10);
		
		txtAdresaP = new JTextField();
		txtAdresaP.setColumns(10);
		txtAdresaP.setBounds(245, 158, 151, 30);
		contentPane.add(txtAdresaP);
		
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
				Sale SalePage = new Sale();
				SalePage.setVisible(true);
				Sale CW = (Sale) Helper.CenterWindow(SalePage);
				
				
			}
		});
		btnPreskoci.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPreskoci.setBounds(196, 394, 151, 30);
		contentPane.add(btnPreskoci);
		
		cbxPtt = new JComboBox();
		cbxPtt.setBounds(245, 239, 151, 30);
		contentPane.add(cbxPtt);
		
		JButton btnDalje = new JButton("Dalje >>");
		btnDalje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String NazivP = txtNazivP.getText();
				String AdresaP = txtAdresaP.getText();
				String ptt = cbxPtt.getSelectedItem().toString();

				Connection connect = Helper.DBSetup();
				String sql = "INSERT INTO pozoriste (Naziv, Adresa, PTT) VALUES ('"+NazivP+"', '"+AdresaP+"', '"+Integer.valueOf(ptt)+"')";
				
				if(NazivP.isEmpty() || AdresaP.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Morate popuniti sva polja.");
				}
				else {
					try { 
						Statement stm = connect.createStatement();
				    	stm.execute(sql);
				    
				    	dispose();
						Sale SalePage = new Sale();
						SalePage.setVisible(true);
						Sale CW = (Sale) Helper.CenterWindow(SalePage);
						
				    
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
		
		btnIzmeni = new JButton("Izmeni");
		btnIzmeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String NazivP = txtNazivP.getText();
				String AdresaP = txtAdresaP.getText();
				String ptt = cbxPtt.getSelectedItem().toString();
				
				Connection connect = Helper.DBSetup();
				String sql = "UPDATE pozoriste SET Naziv = '"+NazivP+"', Adresa = '"+AdresaP+"', PTT = '"+Integer.valueOf(ptt)+"' WHERE ID = '"+ID+"'";
						
				
				if(NazivP.isEmpty() || AdresaP.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Morate popuniti sva polja.");
				}
				else {
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
				
				
			}
		});
		btnIzmeni.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnIzmeni.setBounds(380, 353, 151, 30);
		contentPane.add(btnIzmeni);
		
		ListSelectionModel rowSelectionModel = tablePozoriste.getSelectionModel();

		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {
		        			        
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        		        
		        if(lsm.isSelectionEmpty()) {
		        	//JOptionPane.showMessageDialog(null, "No Selection");
		        }
		        else {
		        	int selRow = tablePozoriste.getSelectedRow();
		        	NazivP = tablePozoriste.getModel().getValueAt(selRow, 1).toString();
		        	AdresaP = tablePozoriste.getModel().getValueAt(selRow, 2).toString();
		        	ID = Integer.parseInt(tablePozoriste.getModel().getValueAt(selRow, 0).toString());
		        	//JOptionPane.showMessageDialog(null,String.valueOf(ID));
		        	txtNazivP.setText(NazivP);
		        	txtAdresaP.setText(AdresaP);
		        	
		        }
		      }

		    });
	
	}


	public Pozorista() {
		initComponents();
		getPtt();
		PrikazPodataka();
	}
}
