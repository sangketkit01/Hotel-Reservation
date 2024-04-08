package Program;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectCreate {

	public static Connection getCon() {
		try {
			Connection connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/hotel.users?users=root&password=0627457454New");
			return connect;
		} catch (Exception e) {
			return null;
		}
	}

}
