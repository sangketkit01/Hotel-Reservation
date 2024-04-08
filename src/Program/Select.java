package Program;
import java.sql.*;
import javax.swing.*;

public class Select {
	public static ResultSet getData(String Query) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {	
			con = DriverManager.getConnection("jdbc:mysql://localhost/hotel?user=root&password=0627457454New");
			st = con.createStatement();
			rs = st.executeQuery(Query);
			return rs;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;}}
	}