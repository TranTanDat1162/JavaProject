package dao;

import model.User;

public interface UserDAO {
    User login(String username);
    User getPasswordByUsername(String username);
	boolean updateUser(User user);
}

