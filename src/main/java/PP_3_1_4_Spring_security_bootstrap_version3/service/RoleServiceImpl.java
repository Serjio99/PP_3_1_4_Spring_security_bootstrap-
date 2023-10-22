package PP_3_1_4_Spring_security_bootstrap_version3.service;

import PP_3_1_4_Spring_security_bootstrap_version3.models.Role;
import PP_3_1_4_Spring_security_bootstrap_version3.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements PP_3_1_4_Spring_security_bootstrap_version3.service.RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository1) {
        this.roleRepository = roleRepository1;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getById(int id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

}