package co.istad.ite2mbanking.init;

import co.istad.ite2mbanking.domain.AccountType;
import co.istad.ite2mbanking.domain.Role;
import co.istad.ite2mbanking.feature.accountType.AccountTypeRepository;
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

    @PostConstruct
    void init() {
        // auto generate role (user.customer, sTUff , and admin)
        // Auto generate role (USER, CUSTOMER, STAFF, ADMIN)
        if (roleRepository.count() < 1) {
            Role user = new Role();
            user.setName("USER");

            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role staff = new Role();
            staff.setName("STAFF");

            Role admin = new Role();
            admin.setName("ADMIN");

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