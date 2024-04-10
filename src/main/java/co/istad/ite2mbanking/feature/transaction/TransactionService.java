package co.istad.ite2mbanking.feature.transaction;

import co.istad.ite2mbanking.feature.transaction.dto.TransactionCreateRequest;
import co.istad.ite2mbanking.feature.transaction.dto.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface TransactionService {
    TransactionResponse transfer (TransactionCreateRequest transactionCreateRequest);


    Page<TransactionResponse> findAll (int page, int size, Sort sortParam,String transactionType);


}
