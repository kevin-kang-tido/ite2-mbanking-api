package co.istad.ite2mbanking.mapper;

import co.istad.ite2mbanking.domain.AccountType;
import co.istad.ite2mbanking.feature.accountType.dto.AccountTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {
    AccountTypeResponse toAccountTypeResponse(AccountType accountType);
   List <AccountTypeResponse> toAccountTypeResponseList(List<AccountType> accountType);


}
