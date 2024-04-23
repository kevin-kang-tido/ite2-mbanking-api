package co.istad.ite2mbanking.feature.auth;

import co.istad.ite2mbanking.feature.auth.dto.AuthResponse;
import co.istad.ite2mbanking.feature.auth.dto.LoginRequest;
import co.istad.ite2mbanking.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    // check password and username
    private  final DaoAuthenticationProvider daoAuthenticationProvider;
    private  final JwtEncoder jwtEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        // catch username and password
        Authentication auth = new UsernamePasswordAuthenticationToken(
                loginRequest.phoneNumber(),
                loginRequest.password()
        );
        // authenticate username and password
        auth = daoAuthenticationProvider.authenticate(auth);

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        log.info(userDetails.getUsername());
        log.info(userDetails.getUser().getName());
        userDetails.getAuthorities()
                .forEach(grantedAuthority -> System.out.println(grantedAuthority.getAuthority()));



        Instant now = Instant.now();
        // allow post in jwt
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));


        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(userDetails.getUsername())
                .subject("Access Resource")
                .audience(List.of("WEB","Banking"))
                .issuedAt(now)
                .expiresAt(now.plus(2, ChronoUnit.MINUTES))
                .issuer(userDetails.getUsername())
                .claim("scope",scope)
//                .claim("scope",scope)
                .build();

    String assessToken =     jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();


        return new AuthResponse(
                "Bearer",
                assessToken,
                ""
        );
    }
}
