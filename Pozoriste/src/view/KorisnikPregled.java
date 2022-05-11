package view;


import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import common.Helper;
import common.HelperPrikaz;

public class KorisnikPregled extends JFrame {
	
	Login Id = new Login();
	static Helper Helper = new Helper();
	HelperPrikaz HelperPrikaz = new HelperPrikaz();
	
	JPanel contentPane;
	private JTable tableDostupnePredstave;
	private JTextField txtNazivPredstave;
	private int idForInsert;
	private String NazivP;
	public int IDK;
	public JSpinner spinnerKol;
	public int BrK;
	private JTextField textField;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KorisnikPregled frame = new KorisnikPregled(0);
					frame.setVisible(true);
					KorisnikPregled CW = (KorisnikPregled) Helper.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void PrikazKor() {
		Connection connect = Helper.DBSetup();
		String query = "SELECT CONCAT_WS(' ', Ime, prezime) as IiP FROM korisnik WHERE ID = '"+IDK+"'";
		Statement stm;
		try {
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()) {
				String IiP = rs.getString("IiP");
				textField.setText(IiP);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void PrikazPodataka() {
		
		String query = "SELECT i.ID, CONCAT_WS('',p.Naziv) as 'Naziv predstave', CONCAT_WS('',s.Ime) as 'Naziv sale', i.Dan FROM izvodjenje_predstave i, predstave p, sala s WHERE i.ID_P = p.ID AND i.ID_S = s.ID ORDER BY i.ID";
		
		HelperPrikaz.PopuniTabelu(query, tableDostupnePredstave);
	}
	
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 532);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		spinnerKol = new JSpinner();
		spinnerKol.setBackground(Color.WHITE);
		spinnerKol.setModel(new SpinnerNumberModel(1, 1, 999, 1));
		spinnerKol.setFont(new Font("Times New Roman", Font.BOLD, 20));
		spinnerKol.setBounds(661, 200, 54, 23);
		contentPane.add(spinnerKol);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 492, 370);
		contentPane.add(scrollPane);
		
		tableDostupnePredstave = new JTable();
		scrollPane.setViewportView(tableDostupnePredstave);
		
		JLabel lblNewLabel = new JLabel("DOSTUPNE PREDSTAVE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 45, 452, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("DOBRODOSLI");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 725, 47);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IZABRANA PREDSTAVA:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2.setBounds(512, 80, 203, 35);
		contentPane.add(lblNewLabel_2);
		
		txtNazivPredstave = new JTextField();
		txtNazivPredstave.setHorizontalAlignment(SwingConstants.CENTER);
		txtNazivPredstave.setEditable(false);
		txtNazivPredstave.setColumns(10);
		txtNazivPredstave.setBorder(null);
		txtNazivPredstave.setBackground(Color.CYAN);
		txtNazivPredstave.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtNazivPredstave.setBounds(512, 126, 203, 35);
		contentPane.add(txtNazivPredstave);
		
		JButton btnRezervisi = new JButton("REZERVISI");
		btnRezervisi.setForeground(Color.BLACK);
		btnRezervisi.setBackground(Color.CYAN);
		btnRezervisi.setBorder(null);
		btnRezervisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BrK = (Integer)spinnerKol.getValue();
				
				Connection connect = Helper.DBSetup();
				String sql = "INSERT INTO rezervacije (ID_I, ID_K) VALUES (  "+idForInsert+", "+IDK+" )";
				
				try {
					Statement stm = connect.createStatement();
					
					for(int i = 0; i<BrK; i++) {
						stm.execute(sql);
					}
					
					if(BrK==1) {
						JOptionPane.showMessageDialog(null,String.valueOf("Uspesno sete rezervisali kartu."));
					}
					else {
						JOptionPane.showMessageDialog(null,String.valueOf("Uspesno sete rezervisali karte."));
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,String.valueOf("Doslo je do greske pokusatjte ponovo."));
				}																
			}
		});
		btnRezervisi.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btnRezervisi.setBounds(512, 408, 203, 42);
		contentPane.add(btnRezervisi);
		
		
		JLabel lblNewLabel_3 = new JLabel("Broj Karata :");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(512, 195, 150, 35);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login LoginPage = new Login();
				LoginPage.setVisible(true);
				Login CW = (Login) Helper.CenterWindow(LoginPage);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(626, 460, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("KORISNIK:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 461, 80, 21);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textField.setBorder(null);
		textField.setEditable(false);
		textField.setBackground(Color.CYAN);
		textField.setBounds(93, 461, 150, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		ListSelectionModel rowSelectionModel = tableDostupnePredstave.getSelectionModel();

		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {
		        			        
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        		        
		        if(lsm.isSelectionEmpty()) {
		        	//JOptionPane.showMessageDialog(null, "No Selection");
		        }
		        else {
		        	int selRow = tableDostupnePredstave.getSelectedRow();
		        	NazivP = tableDostupnePredstave.getModel().getValueAt(selRow, 1).toString();
		        	idForInsert = Integer.parseInt(tableDostupnePredstave.getModel().getValueAt(selRow, 0).toString());
		        	//JOptionPane.showMessageDialog(null,String.valueOf(idForInsert));
		        	txtNazivPredstave.setText(NazivP);
		        }
		      }

		    });
		
	}
	
	
	public KorisnikPregled(int ID) {
		setResizable(false);
		setBackground(Color.WHITE);
		this.IDK = ID;
		initComponents();
		PrikazPodataka();
		PrikazKor();
	}
}
