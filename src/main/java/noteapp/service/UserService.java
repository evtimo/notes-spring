package noteapp.service;

import noteapp.model.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    void register(User user);

    void save(User user);

    List<User> getUsers();

}
