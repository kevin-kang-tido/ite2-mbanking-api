package co.istad.ite2mbanking.feature.user;


import co.istad.ite2mbanking.domain.Role;
import co.istad.ite2mbanking.feature.user.dto.UserDetailsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);

}
