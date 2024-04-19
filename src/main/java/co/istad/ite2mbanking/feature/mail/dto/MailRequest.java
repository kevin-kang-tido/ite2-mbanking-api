package co.istad.ite2mbanking.feature.mail.dto;

import jakarta.validation.constraints.NotBlank;

public record MailRequest(
        @NotBlank(message =  "Email is required")
        String to ,
        @NotBlank(message =  "subject is required")
        String subject,
        @NotBlank(message =  "body is required")
        String text

) {
}
