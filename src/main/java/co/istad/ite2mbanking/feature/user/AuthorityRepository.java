package co.istad.ite2mbanking.feature.user;

import co.istad.ite2mbanking.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

}
