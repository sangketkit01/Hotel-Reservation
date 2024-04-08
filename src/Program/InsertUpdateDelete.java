package Program;
import java.sql.*;
import javax.swing.*;

public class InsertUpdateDelete {
	public static void setData(String Query,String msg) {
		Connection con = null;
		Statement st = null;
		try {
			
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?user=root&password=0627457454New");
			st = con.createStatement();
			st.executeUpdate(Query);
			if(!msg.equals(""))JOptionPane.showMessageDialog(null,msg);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		finally {
			try {
				con.close();
				st.close();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
}
}