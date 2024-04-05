package co.istad.ite2mbanking.feature.user.dto;

import java.time.LocalDate;

public record UserResponse(

        String uuid,
        String name,
        LocalDate dob,
        String profileImage,
        String phoneNumber,
        String studentIdCard
) {
}

