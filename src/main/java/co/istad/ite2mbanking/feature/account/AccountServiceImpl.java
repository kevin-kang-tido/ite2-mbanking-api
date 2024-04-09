package co.istad.ite2mbanking.feature.account;

import co.istad.ite2mbanking.domain.Account;
import co.istad.ite2mbanking.domain.AccountType;
import co.istad.ite2mbanking.domain.User;
import co.istad.ite2mbanking.domain.UserAccount;
import co.istad.ite2mbanking.feature.account.dto.AccountCreateRequest;
import co.istad.ite2mbanking.feature.account.dto.AccountRenameRequest;
import co.istad.ite2mbanking.feature.account.dto.AccountResponse;
import co.istad.ite2mbanking.feature.account.dto.AccountTransferLimitRequest;
import co.istad.ite2mbanking.feature.accountType.AccountTypeRepository;
import co.istad.ite2mbanking.feature.user.UserRepository;
import co.istad.ite2mbanking.mapper.AccountMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements  AccountService{
    private  final AccountMapper accountMapper;
    private  final UserRepository userRepository;
    private  final AccountTypeRepository accountTypeRepository;
    private  final AccountRepository accountRepository;
    private final   UserAccountRepository userAccountRepository;

    @Override
    public void createNew(AccountCreateRequest accountCreateRequest) {
        // check account type
        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(()->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Invalid account Typ"
                    )
                );

        // checking use
        User user = userRepository.findByUuid(accountCreateRequest.userUuid())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User has been not found!"
                ));

        Account account = accountMapper.fromAccountCreateRequest(accountCreateRequest);
        account.setAccountType(accountType);
        account.setActName(user.getName());
        account.setActNo("123456789");
        account.setTransferLimit(BigDecimal.valueOf(1222));
        account.setIsHidden(false);

        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(account);
        userAccount.setUser(user);
        userAccount.setIsDeleted(false);
        userAccount.setCreatedAt(LocalDateTime.now());

        userAccountRepository.save(userAccount);

    }
    // find by account number

    @Override
    public AccountResponse findByActNo(String actNo) {

        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has been not found!"
                ));
        return accountMapper.toAccountResponse(account);
    }
    @Override
    public AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest) {
        //
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account is has been not found!"
                ));


        // check old alias and new alias
         if (account.getAlias() !=null && account.getAlias().equals(accountRenameRequest.newName())){
             throw new ResponseStatusException(
                     HttpStatus.CONFLICT,
                     "New name must not the same old Name!"
             );
         }

         account.setAlias(accountRenameRequest.newName());
         account= accountRepository.save(account);

        return accountMapper.toAccountResponse(account);
    }
     // hide account
    @Transactional
    @Override
    public void hideAccount(String actNo) {
        if (!accountRepository.existsByActNo(actNo)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account has been not found!"
            );
        }
        try{

            accountRepository.hideAccountByActNo(actNo);

        }catch (Exception e){
            throw  new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went wrong!Please contact developer kang!(ABA:12 232 12)"
            );
        }
    }
    @Override
    public Page<AccountResponse> findList(int page, int size) {
        // checking page
        if (page < 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page must be greater or equal 1 !"
            );
        }
        if (size < 1){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page must be greater or equal 1 !"
            );
        }
        Sort sortByActName = Sort.by(Sort.Direction.ASC,"actName");
        PageRequest pageRequest = PageRequest.of(page,size,sortByActName);

        Page<Account> accounts= accountRepository.findAll(pageRequest);

        return accounts.map(accountMapper::toAccountResponse);
    }

    @Override
    public void updateTransferLimit(String actNo, AccountTransferLimitRequest accountTransferLimitRequset) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(()->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Account has been not Found!"
                    )
                );
        account.setTransferLimit(accountTransferLimitRequset.transferLimit());
        accountRepository.save(account);

    }
}
