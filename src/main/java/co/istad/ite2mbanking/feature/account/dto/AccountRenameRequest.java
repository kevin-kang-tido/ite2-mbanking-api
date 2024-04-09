package co.istad.ite2mbanking.feature.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountRenameRequest(
        @NotBlank(message = "New name is required!")
                @Size(message = "Account must be less then one ")
        String newName

) {
}
