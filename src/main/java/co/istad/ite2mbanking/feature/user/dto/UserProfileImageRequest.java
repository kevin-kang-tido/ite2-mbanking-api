package co.istad.ite2mbanking.feature.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserProfileImageRequest(
        @NotBlank(message = "profile image in needed")
        String mediaName
) {
}
