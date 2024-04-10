package co.istad.ite2mbanking.feature.transaction;


import co.istad.ite2mbanking.feature.transaction.dto.TransactionCreateRequest;
import co.istad.ite2mbanking.feature.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private  final  TransactionService transactionService;

    @PostMapping
    TransactionResponse transfer(@Valid @RequestBody TransactionCreateRequest transactionCreateRequest){
         return  transactionService.transfer(transactionCreateRequest);
    }
    @GetMapping
    public Page<TransactionResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false , defaultValue = "") String sortParam,
            @RequestParam(required = false,defaultValue = "") String transactionType // Added direction parameter
    ) {
        // Parse sorting parameters
        Sort.Direction direction = Sort.Direction.ASC;
        String[] sortParams = sortParam.split(":");
        if (sortParams.length == 2 && sortParams[1].equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }

        // Construct Sort object based on the sort parameter
        Sort sortObject = Sort.by(direction, "transactionAt");

        // Call service method with sorting and filtering parameters
        return transactionService.findAll(page,size,sortObject,transactionType);
    }


}
