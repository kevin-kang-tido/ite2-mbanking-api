package co.istad.ite2mbanking.feature.cardType.dto;

import jakarta.validation.constraints.NotNull;

public record CardTypeDetailsResponse(
        @NotNull
        String name,
        Boolean isDeleted
){
}
