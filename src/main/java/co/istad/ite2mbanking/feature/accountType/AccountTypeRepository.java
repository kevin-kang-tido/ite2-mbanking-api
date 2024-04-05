package co.istad.ite2mbanking.feature.accountType;


import co.istad.ite2mbanking.domain.Account;
import co.istad.ite2mbanking.domain.AccountType;
import co.istad.ite2mbanking.feature.account.dto.AccountCreateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType,Long> {
  boolean existsAccountTypeByName(String name);
  boolean existsAccountTypeByAlias(String alias);

    Optional<AccountType> findByAlias(String alias);
}
