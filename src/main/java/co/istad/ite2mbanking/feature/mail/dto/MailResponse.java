package co.istad.ite2mbanking.feature.mail.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

public record MailResponse(
        @NotBlank
        String message

) {
}
