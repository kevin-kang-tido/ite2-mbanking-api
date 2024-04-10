package co.istad.ite2mbanking.feature.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionCreateRequest(
        @NotBlank(message = "Transfer account of owner is require!")
        String ownerActNo,
        @NotBlank(message = "Transfer account of owner is require!")
        String transferReceiverActNo,
        @NotNull(message = "Transfer account is no receiver require!")
        @Positive
        BigDecimal amount,
        String remark


) {
}
