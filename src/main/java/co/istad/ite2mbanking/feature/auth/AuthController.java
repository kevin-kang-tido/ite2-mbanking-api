package co.istad.ite2mbanking.feature.auth;

import co.istad.ite2mbanking.feature.auth.dto.AuthResponse;
import co.istad.ite2mbanking.feature.auth.dto.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final  AuthService authService;
    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest loginRequest){

        return authService.login(loginRequest);
    }

}
