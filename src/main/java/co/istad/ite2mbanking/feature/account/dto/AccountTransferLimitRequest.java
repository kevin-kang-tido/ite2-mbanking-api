package co.istad.ite2mbanking.feature.account.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountTransferLimitRequest(
        @NotNull
        BigDecimal transferLimit
) {
}
