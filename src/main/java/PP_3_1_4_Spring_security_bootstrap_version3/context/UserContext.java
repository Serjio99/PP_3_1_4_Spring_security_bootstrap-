package PP_3_1_4_Spring_security_bootstrap_version3.context;

import PP_3_1_4_Spring_security_bootstrap_version3.models.Role;
import PP_3_1_4_Spring_security_bootstrap_version3.models.User;
import PP_3_1_4_Spring_security_bootstrap_version3.service.RoleService;
import PP_3_1_4_Spring_security_bootstrap_version3.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserContext implements ApplicationRunner {

    private final RoleService roleService;

    private final UserService userService;


    public UserContext(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        Set<Role> adminList = Set.of(admin);

        roleService.save(admin);
        roleService.save(user);

        User adminUser = new User();
        adminUser.setRoles(adminList);
        adminUser.setEmail("vit@mail.com");
        adminUser.setAge("22");
        adminUser.setName("Bob");
        adminUser.setSurname("Ivanov");
        adminUser.setPassword("123");
        userService.saveUser(adminUser);
    }
}