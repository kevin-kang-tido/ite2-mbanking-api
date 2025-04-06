package co.istad.ite2mbanking.feature.auth.dto;

public record AuthResponse(
        String type,
        String accessToken,
        String refreshToken

) {
}
