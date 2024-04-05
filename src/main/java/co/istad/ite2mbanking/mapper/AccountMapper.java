package co.istad.ite2mbanking.mapper;

import co.istad.ite2mbanking.domain.Account;
import co.istad.ite2mbanking.feature.account.dto.AccountCreateRequest;
import co.istad.ite2mbanking.feature.account.dto.AccountResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);

    AccountResponse toAccountResponse(Account account);


}
