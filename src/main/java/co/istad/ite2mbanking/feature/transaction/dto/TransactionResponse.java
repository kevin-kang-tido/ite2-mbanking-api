package co.istad.ite2mbanking.feature.transaction.dto;

import co.istad.ite2mbanking.feature.account.dto.AccountSnippetResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        AccountSnippetResponse owner,
        AccountSnippetResponse transferReceiver,
        BigDecimal amount,
        String transactionType,
        String remark,
        Boolean status,
        LocalDateTime transactionAt

) {
}
