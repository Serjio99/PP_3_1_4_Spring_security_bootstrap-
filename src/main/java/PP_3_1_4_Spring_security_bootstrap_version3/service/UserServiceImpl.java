package PP_3_1_4_Spring_security_bootstrap_version3.service;

import PP_3_1_4_Spring_security_bootstrap_version3.models.Role;
import PP_3_1_4_Spring_security_bootstrap_version3.models.User;
import PP_3_1_4_Spring_security_bootstrap_version3.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements PP_3_1_4_Spring_security_bootstrap_version3.service.UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(User user, int id){

        User userDB = userRepository.findById(id).get(); //Находу юзера в БД, кого хочу редактировать, до отправки на обновление в БД

        if (userDB.getPassword().equals(user.getPassword())) {//если пароль старый его не нужно перезаписывать просто обновляем юзера
            userRepository.saveAndFlush(user);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));//если пароль новый его нужно перезаписать
            userRepository.saveAndFlush(user);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    //задача UserDetailsService предоставить из БД юзера по имени
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),rolesToAuthorities(user.getRoles()));

    }

    //метод возвращает коллекцию прав доступа
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role>roles){
        return roles.stream().map(r->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}