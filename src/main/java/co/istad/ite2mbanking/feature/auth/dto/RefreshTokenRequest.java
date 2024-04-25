package co.istad.ite2mbanking.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank(message = "Refresh Token is required!")
        String refreshToken
) {
}
