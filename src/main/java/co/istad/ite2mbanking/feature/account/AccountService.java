package co.istad.ite2mbanking.feature.account;

import co.istad.ite2mbanking.feature.account.dto.AccountCreateRequest;
import co.istad.ite2mbanking.feature.account.dto.AccountRenameRequest;
import co.istad.ite2mbanking.feature.account.dto.AccountResponse;
import co.istad.ite2mbanking.feature.account.dto.AccountTransferLimitRequest;
import org.springframework.data.domain.Page;

public interface AccountService {

    void createNew(AccountCreateRequest accountCreateRequest);

    AccountResponse findByActNo(String actNo);

    // findByActNo using mapper
    AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest);

    // hidden account
    void hideAccount(String actNo);

    Page<AccountResponse> findList(int page, int size);

    void updateTransferLimit(String actNo, AccountTransferLimitRequest accountTransferLimitRequest);


}