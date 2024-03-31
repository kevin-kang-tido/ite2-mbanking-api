package co.istad.ite2mbanking.feature.cardType;

import co.istad.ite2mbanking.domain.CardType;
import co.istad.ite2mbanking.feature.cardType.dto.CardTypeDetailsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType,Long> {

    Boolean existsCardTypeByName(String name);
}
