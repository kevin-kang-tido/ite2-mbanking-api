package co.istad.ite2mbanking.feature.accountType;

import co.istad.ite2mbanking.feature.accountType.dto.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeResponse> findAllAccountTypes();
    AccountTypeResponse findAccountTypeByName(String name);
    AccountTypeResponse findAccountTypeByAlias(String alias);

}
