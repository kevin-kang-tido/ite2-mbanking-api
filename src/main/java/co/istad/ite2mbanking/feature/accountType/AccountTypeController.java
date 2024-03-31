package co.istad.ite2mbanking.feature.accountType;


import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-type")
public class AccountTypeController {
    private  final  AccountTypeService accountTypeService;
    @GetMapping
    ResponseEntity<?> findAllAccountType(){
        return ResponseEntity.accepted().body(
                Map.of(
                        "AccountType",accountTypeService.findAllAccountTypes()
                )
        );
    }
    @GetMapping("/{name}")
    ResponseEntity<?> findAccountTypeByName(@Valid @PathVariable String name){

        return new ResponseEntity<>(
                Map.of(
                        "AccountType",accountTypeService.findAccountTypeByName(name)
                ), HttpStatus.ACCEPTED
        );

    }
    @GetMapping("/alias{alias}")
    ResponseEntity<?> findAccountByName(@Valid @PathVariable String alias){
        return new ResponseEntity<>(
                Map.of(
                        "AccountType",accountTypeService.findAccountTypeByAlias(alias)

                )
                ,HttpStatus.ACCEPTED
        );
    }

}
