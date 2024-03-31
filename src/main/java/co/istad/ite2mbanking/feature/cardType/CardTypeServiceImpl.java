package co.istad.ite2mbanking.feature.cardType;

import co.istad.ite2mbanking.domain.CardType;
import co.istad.ite2mbanking.feature.cardType.dto.CardTypCreateRequest;
import co.istad.ite2mbanking.feature.cardType.dto.CardTypeDetailsResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardTypeServiceImpl implements CardTypeService{

    private  final  CardTypeRepository cardTypeRepository;
//    private  final

    @Override
    public List<CardTypeDetailsResponse> findCardTypes() {
        List<CardType> cardTypes = cardTypeRepository.findAll();

        return cardTypes.stream()
                .map(cardType -> new CardTypeDetailsResponse(
                        cardType.getName(),
                        cardType.getIsDeleted()
                )).toList();
    }

    @Override
    public CardTypeDetailsResponse findCardTypeByName(String name) {
        if(!cardTypeRepository.existsCardTypeByName(name)){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "CardType doesn't exist!"
            );
        }
        return cardTypeRepository.findAll().stream()
                .filter(cardType -> cardType.getName().equals(name))
                .map(cardType -> new CardTypeDetailsResponse(
                        cardType.getName(),
                        cardType.getIsDeleted()
                ) ).findFirst().orElseThrow();
    }

}
