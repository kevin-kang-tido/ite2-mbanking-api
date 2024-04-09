package co.istad.ite2mbanking.feature.account;


import co.istad.ite2mbanking.feature.account.dto.AccountCreateRequest;
import co.istad.ite2mbanking.feature.account.dto.AccountRenameRequest;
import co.istad.ite2mbanking.feature.account.dto.AccountResponse;
import co.istad.ite2mbanking.feature.account.dto.AccountTransferLimitRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@RestController
public class AccountController {
    private  final AccountService accountService;
    @GetMapping
    Page<AccountResponse> findList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "23") int size


    ){
        return  accountService.findList(page,size);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        accountService.createNew(accountCreateRequest);
    }
    @GetMapping("/{actNo}")
    AccountResponse findByActNo(@PathVariable String actNo) {
        return accountService.findByActNo(actNo);
    }


    @PutMapping("/{actNo}/hide")
    void hideAccountByActNo(@PathVariable String actNo) {
         accountService.hideAccount(actNo);
    }

    // rename
    @PutMapping("/{actNo}/rename")
    AccountResponse reNameAccountByActNo (@PathVariable  String actNo,@Valid @RequestBody AccountRenameRequest accountRenameRequest){
        return accountService.renameByActNo(actNo,accountRenameRequest);
    }
    // update account
    @PutMapping("/{actNo}/account-transfer-limit")
    void updateTransferLimit(@PathVariable String actNo, @Valid @RequestBody AccountTransferLimitRequest accountTransferLimitRequest){
            accountService.updateTransferLimit(actNo,accountTransferLimitRequest);
    }






}
