package PP_3_1_4_Spring_security_bootstrap_version3.service;

import PP_3_1_4_Spring_security_bootstrap_version3.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(int id);

    void deleteUser(int id);

    public void updateUser(User user, int id);

    User findByUsername(String username);

}