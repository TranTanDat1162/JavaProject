package logindao;

import model.User;

public interface UserDAO {
    User login(String username, String password);
    User getPasswordByUsername(String username);
	boolean updateUser(User user);
}

