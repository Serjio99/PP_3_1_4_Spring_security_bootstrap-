package PP_3_1_4_Spring_security_bootstrap_version3.service;

import PP_3_1_4_Spring_security_bootstrap_version3.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> listRoles();

    Role getById(int id);

    void save(Role role);

}