package co.istad.ite2mbanking.feature.accountType.dto;

import jakarta.validation.constraints.NotNull;

public record AccountTypeResponse(
        @NotNull
        String name,
        @NotNull
        String alias,
        String description,
        Boolean isDeleted

) {
}
