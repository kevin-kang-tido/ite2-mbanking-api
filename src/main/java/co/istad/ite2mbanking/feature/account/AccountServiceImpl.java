package co.istad.ite2mbanking.feature.account;

import co.istad.ite2mbanking.domain.Account;
import co.istad.ite2mbanking.domain.AccountType;
import co.istad.ite2mbanking.domain.User;
import co.istad.ite2mbanking.domain.UserAccount;
import co.istad.ite2mbanking.feature.account.dto.AccountCreateRequest;
import co.istad.ite2mbanking.feature.account.dto.AccountResponse;
import co.istad.ite2mbanking.feature.accountType.AccountTypeRepository;
import co.istad.ite2mbanking.feature.user.UserRepository;
import co.istad.ite2mbanking.feature.user.dto.UserResponse;
import co.istad.ite2mbanking.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        UserAccount userAccount = userAccountRepository.findByAccount(account)
                .orElseThrow( ()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "UserAccount has been not found!"
                ));
        User user = userAccount.getUser();
        UserResponse userResponse = new UserResponse(
                user.getUuid(),
                user.getName(),
                user.getDob(),
                user.getProfileImage(),
                user.getPhoneNumber(),
                user.getStudentIdCard()
        );


        return new AccountResponse(
                account.getActNo(),
                account.getActName(),
                account.getAlias(),
                account.getBalance(),
                account.getTransferLimit(),
                account.getAccountType().getName(),
                userResponse
        );
    }
}
