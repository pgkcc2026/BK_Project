package service;

import dao.UserDAO;
import model.User;

public class UserService {

    private UserDAO dao = new UserDAO();

    public boolean register(User user) {
        return dao.insertUser(user);
    }

    public User login(String email, String password) {
        return dao.findByEmailAndPassword(email, password);
    }
}