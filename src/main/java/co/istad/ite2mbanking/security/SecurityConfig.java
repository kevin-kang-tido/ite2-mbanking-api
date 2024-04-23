package co.istad.ite2mbanking.security;


import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.KeyGenerator;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private  final PasswordEncoder passwordEncoder;
    private  final UserDetailsService userDetailsService;

    //(get data form database)
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

      return  provider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // here write your logic
        httpSecurity.authorizeHttpRequests(request ->request
                         .requestMatchers("/api/v1/auth/**").permitAll()
                        // ALLOW ony user have role staff
                        .requestMatchers(HttpMethod.GET,"/api/v1/account-type/**").hasAuthority("STAFF")
                        // allow only user have role admin
                         .requestMatchers(HttpMethod.GET,"/api/v1/users/**","/api/v1/accounts/**").hasAuthority("SCOPE_ROLE_ADMIN")
                         .requestMatchers(HttpMethod.POST,"/api/v1/users/**").hasAuthority("SCOPE_ROLE_ADMIN")
                         .requestMatchers(HttpMethod.PUT,"/api/v1/users/**","/api/v1/accounts/**").hasAuthority("SCOPE_ROLE_ADMIN")
                         .requestMatchers(HttpMethod.DELETE,"/api/v1/users/**","/api/v1/accounts/**").hasAuthority("SCOPE_ROLE_ADMIN")
                        .anyRequest()
                        .authenticated()
                );
        // security mechanism
//        httpSecurity.httpBasic(Customizer.withDefaults());

          httpSecurity.oauth2ResourceServer(jwt -> jwt
                  .jwt(Customizer.withDefaults()));


        // disable csrf
        httpSecurity.csrf(token->token.disable());
        httpSecurity.sessionManagement(session->session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return httpSecurity.build();
    }

    //generate key pair
    @Bean
    KeyPair keyPair(){
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    @Bean
    RSAKey rsaKey(KeyPair keyPair){
        return new RSAKey.Builder((RSAPublicKey)keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    JWKSource<SecurityContext> jwkSource(RSAKey rsaKey){
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, jwtSourceContext) -> jwkSelector.select(jwkSet);
    };

    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource){

        return new NimbusJwtEncoder(jwkSource);
    }


    @Bean
    JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }




}


