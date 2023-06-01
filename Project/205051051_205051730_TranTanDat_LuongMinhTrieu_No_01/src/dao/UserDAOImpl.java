package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import _class.DatabaseConnector;
import model.User;

public class UserDAOImpl implements UserDAO {
	@Override
	public User login(String username, String password) {
	    String query = "SELECT * FROM users WHERE username = ? AND password = ?";
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, username);
	        statement.setString(2, password);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                String retrievedUsername = resultSet.getString("username");
	                String retrievedPassword = resultSet.getString("password");
	                // Thực hiện việc khởi tạo đối tượng User từ dữ liệu truy vấn
	                User user = new User(retrievedUsername, retrievedPassword);
	                return user;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}


	@Override
	public User getPasswordByUsername(String username) {
		// TODO Auto-generated method stub
		String query = "SELECT password FROM users WHERE username = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	String retrievedPassword = resultSet.getString("password");
                // Thực hiện việc khởi tạo đối tượng User từ dữ liệu truy vấn
                User user = new User(retrievedPassword);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

}
