package noteapp.dao;

import noteapp.model.User;

import java.util.List;

public interface UserDao {

    User findByUsername(String username);

    void save(User user);

    List<User> getUsers();

}
