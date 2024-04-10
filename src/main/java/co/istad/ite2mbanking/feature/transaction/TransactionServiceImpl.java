package co.istad.ite2mbanking.feature.transaction;

import co.istad.ite2mbanking.domain.Account;
import co.istad.ite2mbanking.domain.Transaction;
import co.istad.ite2mbanking.feature.account.AccountRepository;
import co.istad.ite2mbanking.feature.transaction.dto.TransactionCreateRequest;
import co.istad.ite2mbanking.feature.transaction.dto.TransactionResponse;
import co.istad.ite2mbanking.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService{
    private  final  TransactionRepository transactionRepository;
    private  final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;
    @Override
    public TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest) {
        log.info("(TransactionCreateRequest transactionCreateRequest) : {}",transactionCreateRequest);
        // validate account have or not
        // load accout
        Account owner = accountRepository.findByActNo(transactionCreateRequest.ownerActNo())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account owner has been not found!"
                ));
        Account transferReceiver = accountRepository.findByActNo(transactionCreateRequest.transferReceiverActNo()            )
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account transfer receiver has been not found!!"
                ));
       // check amount owner money
        if (owner.getBalance().doubleValue() < transactionCreateRequest.amount().doubleValue()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Insufficient balance"
            );
        }
        // check limit to tranfer
        if (transactionCreateRequest.amount().doubleValue() >= owner.getTransferLimit().doubleValue()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Transaction has been over limit to transfer!"

            );
        }
        //
        owner.setBalance(owner.getBalance().subtract(transactionCreateRequest.amount()));

        transferReceiver.setBalance(transferReceiver.getBalance().add(transactionCreateRequest.amount()));

        Transaction transaction = transactionMapper.fromTransactionCreateRequest(transactionCreateRequest);
        transaction.setOwner(owner);
        transaction.setTransferReceiver(transferReceiver);
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionAt(LocalDateTime.now());
        transaction.setStatus(true);
        transaction = transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(transaction);

    }

    @Override
    public Page<TransactionResponse> findAll(int page, int size, Sort sortParam, String transactionType) {
        if (page <0){
            throw  new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page must be qual or  greater then  0"
            );
        }
        if (size <1){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Size must be greater then 1"
            );
        }
        // Create a Pageable object with the given page, size, and sort parameters
//        Pageable pageable = Pageable.ofSize(size).withPage(page).sorted(sortParam);

        Specification<Transaction> spec = Specification.where(null);
        if (transactionType != null && !transactionType.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("transactionType"), transactionType));
        }
        // Construct Pageable with sorting
        Pageable pageable = PageRequest.of(page, size, sortParam);
        // Fetch page of transactions based on criteria
        Page<Transaction> transactionPage = transactionRepository.findAll(spec, pageable);
        // Map Transaction entities to TransactionResponse objects
        return transactionPage.map(transactionMapper::toTransactionResponse);
    }
}
