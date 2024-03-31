package co.istad.ite2mbanking.feature.user.dto;

import java.math.BigDecimal;

public record UserEditRequest(
        String name,
        Integer pin,
        String phoneNumber,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String employeeType,
        String position,
        String companyName,
        String mainSourceOfIncome,
        BigDecimal monthlyIncomeRange
) {
}
