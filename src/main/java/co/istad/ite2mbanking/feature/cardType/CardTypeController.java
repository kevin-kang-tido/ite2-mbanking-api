package co.istad.ite2mbanking.feature.cardType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/card-type")
public class CardTypeController {
    private  final  CardTypeService cardTypeService;
    @GetMapping
    ResponseEntity<?> findCardType(){
        return ResponseEntity.accepted().body(
                Map.of(
                        "CardType",cardTypeService.findCardTypes()
                )
        );
    }
    @GetMapping("/{name}")
    ResponseEntity<?> findCardTypeByName(@Valid @PathVariable String name){
        return new ResponseEntity<>(
            Map.of(
                    "data","cardType has been found",
                    "cardType",cardTypeService.findCardTypeByName(name)
            ),HttpStatus.ACCEPTED
        );
    }





}
