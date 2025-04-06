package co.istad.ite2mbanking.feature.token;

import co.istad.ite2mbanking.feature.auth.dto.AuthResponse;
import org.springframework.security.core.Authentication;


public interface TokenService {
    AuthResponse createToken(Authentication auth);

    String createAccessToken(Authentication auth);

    String createRefreshToken(Authentication auth);

}
