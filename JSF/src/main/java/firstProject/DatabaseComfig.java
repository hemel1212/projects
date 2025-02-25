package firstProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseComfig {
	static final String URL = "jdbc:oracle:thin:@//localhost:1521/ORCLpdb";
	static final String USERNAME = "orclpdbuser";
	static final String PASSWORD = "isdb62";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		Class.forName("oracle.jdbc.OracleDriver");

		return DriverManager.getConnection(URL, USERNAME, PASSWORD);

	}
}
