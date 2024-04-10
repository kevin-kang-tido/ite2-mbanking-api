package co.istad.ite2mbanking.mapper;

import co.istad.ite2mbanking.domain.Transaction;
import co.istad.ite2mbanking.feature.transaction.dto.TransactionCreateRequest;
import co.istad.ite2mbanking.feature.transaction.dto.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = AccountMapper.class)
public interface TransactionMapper {
    // dak form
    // if dto dak to
    TransactionResponse toTransactionResponse(Transaction  transaction);
    Transaction fromTransactionCreateRequest(TransactionCreateRequest transactionCreateRequest);


}
