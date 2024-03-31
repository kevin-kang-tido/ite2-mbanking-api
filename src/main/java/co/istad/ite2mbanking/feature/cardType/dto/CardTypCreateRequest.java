package co.istad.ite2mbanking.feature.cardType.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CardTypCreateRequest(
        @NotNull
        @Size(max = 40)
        String name,
        Boolean isDeleted

) {
}
