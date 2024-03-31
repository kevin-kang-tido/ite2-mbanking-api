package co.istad.ite2mbanking.feature.user.dto;

import jakarta.validation.constraints.NotNull;

public record UserEditPassword (
        @NotNull
        String oldPassword,
        @NotNull
        String newPassword,
        @NotNull
        String confirmedPassword
){
}
