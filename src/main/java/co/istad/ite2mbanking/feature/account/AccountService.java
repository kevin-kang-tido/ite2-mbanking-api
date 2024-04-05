package co.istad.ite2mbanking.feature.account;

import co.istad.ite2mbanking.feature.account.dto.AccountCreateRequest;
import co.istad.ite2mbanking.feature.account.dto.AccountResponse;

public interface AccountService  {
    void createNew(AccountCreateRequest accountCreateRequest);
    AccountResponse findByActNo (String actNo);


}
