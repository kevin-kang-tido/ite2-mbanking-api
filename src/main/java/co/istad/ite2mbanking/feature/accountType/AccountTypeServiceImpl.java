package co.istad.ite2mbanking.feature.accountType;

import co.istad.ite2mbanking.domain.Account;
import co.istad.ite2mbanking.domain.AccountType;
import co.istad.ite2mbanking.feature.accountType.dto.AccountTypeResponse;
import co.istad.ite2mbanking.feature.cardType.dto.CardTypeDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{
    private  final AccountTypeRepository accountTypeRepository;


    @Override
    public List<AccountTypeResponse> findAllAccountTypes() {

       List<AccountType> accountTypes = accountTypeRepository.findAll();

       return  accountTypes.stream()
               .map(accountType -> new AccountTypeResponse(
                       accountType.getAlias(),
                       accountType.getName(),
                       accountType.getDescription(),
                       accountType.getIsDeleted()
               )).toList();
    }

    @Override
    public AccountTypeResponse findAccountTypeByName(String name) {
        if (!accountTypeRepository.existsAccountTypeByName(name)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "AccountType doesn't exist!"
            );
        }

        return accountTypeRepository.findAll().stream()
                .filter(accountType -> accountType.getName().equals(name))
                .map(accountType -> new AccountTypeResponse(
                        accountType.getName(),
                        accountType.getAlias(),
                        accountType.getDescription(),
                        accountType.getIsDeleted()
                )).findFirst().orElseThrow();
    }

    @Override
    public AccountTypeResponse findAccountTypeByAlias(String alias) {
        if (!accountTypeRepository.existsAccountTypeByAlias(alias)){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "AccountType doesn't exist!!"
            );

        }
        return accountTypeRepository.findAll().stream()
                .filter(accountType -> accountType.getAlias().equals(alias))
                .map(accountType -> new AccountTypeResponse(
                        accountType.getName(),
                        accountType.getAlias(),
                        accountType.getDescription(),
                        accountType.getIsDeleted()
                )
                ).findFirst().orElseThrow();
    }
}
