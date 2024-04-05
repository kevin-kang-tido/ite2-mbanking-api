package co.istad.ite2mbanking.feature.account;

import co.istad.ite2mbanking.domain.Account;
import co.istad.ite2mbanking.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
   Optional <UserAccount> findByAccount(Account account);
}
