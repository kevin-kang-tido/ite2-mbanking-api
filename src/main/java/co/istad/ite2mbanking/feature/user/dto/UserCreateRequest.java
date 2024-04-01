package co.istad.ite2mbanking.feature.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record UserCreateRequest(
        @NotNull
        @Max(9999)
        @Positive
        Integer pin,
        @NotBlank
        String name,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String password,
        @NotBlank
        String confirmedPassword,
        @NotBlank
        @Size(max = 6)
        String gender,
        @NotNull
        LocalDate dob,
        @NotBlank
        @Size(max = 20)
        String nationalCardId,
        @Size(max = 20)
        String studentIdCard,
        @NotEmpty
        List<RoleRequest> roles




) {
}
