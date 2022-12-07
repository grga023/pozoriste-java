package common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class HelperPrikaz {
	
	static Helper Helper = new Helper();
	
	public  void PopuniTabelu(String query, JTable tableData) {
		Connection connect = Helper.DBSetup();
		
		try {
			//Ciscenje table				
			tableData.setModel(new DefaultTableModel());
			
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tableData.getModel(); 
			
			int columns = rsmd.getColumnCount();
			String[] colName = new String[columns];
			
			for(int i = 0; i<columns; i++) {
				colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			
			String [] koloneDB = new String[columns];
			
			while(rs.next()) {
				for(int n = 1, j =0; n <=columns ; n++, j++) {
					koloneDB[j] = rs.getString(n);
				}
				model.addRow(koloneDB);
			}
			connect.close();
		}
		 catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}
