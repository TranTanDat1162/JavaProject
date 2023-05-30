package _class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Createdb {
	public static void Initiate (String URLSV, String USERNAME, String PASSWORD) throws SQLException  {
		String db ="CREATE DATABASE `shop_management`;";		
		String tbl="CREATE TABLE `shop_management`.users ("
				+ "  id int NOT NULL AUTO_INCREMENT,"
				+ "  username varchar(50) DEFAULT NULL,"
				+ "  password varchar(100) DEFAULT NULL,"
				+ "  PRIMARY KEY (id),"
				+ "  UNIQUE KEY username (username))";
		Connection conn = DriverManager.getConnection(URLSV, USERNAME, PASSWORD);
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(db);
		stmt.executeUpdate(tbl);
	}
}
