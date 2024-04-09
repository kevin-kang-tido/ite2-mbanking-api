package co.istad.ite2mbanking.mapper;

import co.istad.ite2mbanking.domain.Account;
import co.istad.ite2mbanking.domain.User;
import co.istad.ite2mbanking.domain.UserAccount;
import co.istad.ite2mbanking.feature.account.dto.AccountCreateRequest;
import co.istad.ite2mbanking.feature.account.dto.AccountResponse;
import co.istad.ite2mbanking.feature.user.dto.UserResponse;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring",uses={
        UserMapper.class,
        AccountTypeMapper.class

})
public interface AccountMapper {

    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);

//    AccountResponse toAccountResponse(Account account);

    //
    @Mapping( source = "userAccountList",target ="user",
    qualifiedByName = "mapUserResponse" )
    AccountResponse toAccountResponse(Account account);










}
