package co.istad.ite2mbanking.feature.cardType;

import co.istad.ite2mbanking.domain.CardType;
import co.istad.ite2mbanking.feature.cardType.dto.CardTypCreateRequest;
import co.istad.ite2mbanking.feature.cardType.dto.CardTypeDetailsResponse;

import java.util.List;

public interface CardTypeService {
    List<CardTypeDetailsResponse> findCardTypes();
    CardTypeDetailsResponse findCardTypeByName(String name);
}
