package co.istad.ite2mbanking.feature.account.dto;

import co.istad.ite2mbanking.domain.AccountType;
import co.istad.ite2mbanking.feature.accountType.dto.AccountTypeResponse;
import co.istad.ite2mbanking.feature.user.dto.UserResponse;

import java.math.BigDecimal;

public record AccountResponse (
        String actNo,
        String actName,
        String alias,
        BigDecimal balance,
        BigDecimal transferLimit,
        AccountTypeResponse accountType,
        UserResponse user



){

}
