package co.istad.ite2mbanking.feature.account;

import co.istad.ite2mbanking.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository <Account,Long>{


   Optional <Account> findByActNo(String actNo);
}
