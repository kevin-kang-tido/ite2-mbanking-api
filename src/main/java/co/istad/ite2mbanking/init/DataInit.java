package co.istad.ite2mbanking.init;

import co.istad.ite2mbanking.domain.AccountType;
import co.istad.ite2mbanking.domain.Authority;
import co.istad.ite2mbanking.domain.Role;
import co.istad.ite2mbanking.feature.accountType.AccountTypeRepository;
import co.istad.ite2mbanking.feature.user.AuthorityRepository;
import co.istad.ite2mbanking.feature.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final RoleRepository roleRepository;
    private  final AccountTypeRepository accountTypeRepository;
    private  final AuthorityRepository authorityRepository;

    @PostConstruct
    void initRole() {
        // Auto generate role (USER, CUSTOMER, STAFF, ADMIN)
        if (roleRepository.count() < 1) {

            Authority userRead = new Authority();
            userRead.setName("user:read");
            Authority userWrite = new Authority();
            userWrite.setName("user:write");
            Authority transactionRead = new Authority();
            transactionRead.setName("transaction:read");
            Authority transactionWrite = new Authority();
            transactionWrite.setName("transaction:write");
            Authority accountRead = new Authority();
            accountRead.setName("account:read");
            Authority accountWrite = new Authority();
            accountWrite.setName("account:write");
            Authority accountTypeRead = new Authority();
            accountTypeRead.setName("accountType:read");
            Authority accountTypeWrite = new Authority();
            accountTypeWrite.setName("accountType:write");

            Role user = new Role();
            user.setName("USER");
            user.setAuthorities(List.of(
                    userRead, transactionRead,
                    accountRead, accountTypeRead
            ));

            Role customer = new Role();
            customer.setName("CUSTOMER");
            customer.setAuthorities(List.of(
                    userWrite, transactionWrite,
                    accountWrite
            ));

            Role staff = new Role();
            staff.setName("STAFF");
            staff.setAuthorities(List.of(
                    accountTypeWrite
            ));

            Role admin = new Role();
            admin.setName("ADMIN");
            admin.setAuthorities(List.of(
                    userWrite, accountWrite,
                    accountTypeWrite
            ));

            roleRepository.saveAll(
                    List.of(user, customer, staff, admin)
            );
        }

    }




    @PostConstruct
    void intAccountType(){
        if (accountTypeRepository.count()<1){
            AccountType savingAccountType = new AccountType();
            savingAccountType.setName("Saving Account");
            savingAccountType.setAlias("saving account");
            savingAccountType.setIsDeleted(false);
            savingAccountType.setDescription("It allows individuals to deposit and store their money while earning a certain rate of interest on the deposited amount. ");
            accountTypeRepository.save(savingAccountType);


            AccountType PayrollAccountType = new AccountType();
            PayrollAccountType.setName("Payroll  Account");
            PayrollAccountType.setAlias("payroll account");
            PayrollAccountType.setIsDeleted(false);
            PayrollAccountType.setDescription("It allows individuals to deposit and store their money while earning a certain rate of interest on the deposited amount. ");
            accountTypeRepository.save(PayrollAccountType);

            AccountType cardAccountType = new AccountType();
            cardAccountType.setName("Card Account");
            cardAccountType.setAlias("card account");
            cardAccountType.setIsDeleted(false);
            cardAccountType.setDescription("It allows individuals to deposit and store their money while earning a certain rate of interest on the deposited amount. ");
            accountTypeRepository.save(cardAccountType);

        }

    }
}