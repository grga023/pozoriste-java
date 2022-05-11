package common;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.Window;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;



public class Helper {	
	
	public Connection DBSetup() {
		
		Connection conn = null;
		 
		String db ="jdbc:mysql://localhost:3306/pozoriste";
		String usr = "root";
		String pass = "grga";
		
	    try {
	        conn = DriverManager.getConnection(db,usr,pass);
	        if (conn != null) {
	            DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
	            System.out.println("Driver name: " + dm.getDriverName());
	            System.out.println("Driver version: " + dm.getDriverVersion());
	            System.out.println("Product name: " + dm.getDatabaseProductName());
	            System.out.println("Product version: " + dm.getDatabaseProductVersion());
	        }
	        else {
	        	System.out.println("CONNECTION LOST.");
	        }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return conn;
		}
		
		public Object CenterWindow(Window frame) {
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
			int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
			frame.setLocation(x, y);
			return null;
		}
}
