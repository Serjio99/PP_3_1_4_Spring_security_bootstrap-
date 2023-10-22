package PP_3_1_4_Spring_security_bootstrap_version3.repositories;

import PP_3_1_4_Spring_security_bootstrap_version3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("Select u from User u left join fetch u.roles where u.name=:username")
    User findByUsername(String username);
}