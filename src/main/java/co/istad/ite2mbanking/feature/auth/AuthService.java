package co.istad.ite2mbanking.feature.auth;

import co.istad.ite2mbanking.feature.auth.dto.AuthResponse;
import co.istad.ite2mbanking.feature.auth.dto.LoginRequest;
import co.istad.ite2mbanking.feature.auth.dto.RefreshTokenRequest;

public interface AuthService {
    // refresh
    AuthResponse refresh(RefreshTokenRequest refreshTokenRequest);
    AuthResponse login(LoginRequest loginRequest);
}
