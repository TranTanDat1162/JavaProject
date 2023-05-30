package _class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	
	private static final String URLSV = "jdbc:mysql://localhost:3306";
	private static final String URL = "jdbc:mysql://localhost:3306/shop_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        try {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			Createdb.Initiate(URLSV, USERNAME, PASSWORD);
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
    }
}
