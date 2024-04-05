package co.istad.ite2mbanking.feature.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record AccountCreateRequest(
        @NotBlank(message = "Alias is required")
        String alias,
        @NotNull(message = "First Balance is required 5$ up")
        BigDecimal balance,
        @NotBlank(message = "AccountType is required")
        String accountTypeAlias,
        @NotBlank(message = "uuid is required!")
        String userUuid,
        String cardNumber
) {
}
