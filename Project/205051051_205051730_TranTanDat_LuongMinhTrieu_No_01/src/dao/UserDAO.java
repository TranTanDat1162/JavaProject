package dao;

public interface UserDAO {
    boolean login(String username, String password);
    String getPasswordByUsername(String username);
}

