package co.istad.ite2mbanking.security;


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
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
        httpSecurity
                .authorizeHttpRequests(request ->request
                        // ALLOW ony user have role staff
                        .requestMatchers(HttpMethod.GET,"/api/v1/account-type/**").hasRole("STAFF")
                        // allow only user have role admin
                         .requestMatchers(HttpMethod.POST,"/api/v1/users/**","/api/v1/accounts/**").hasRole("ADMIN")
//                         .requestMatchers(HttpMethod.POST,"/api/v1/users/**").permitAll()
                         .requestMatchers(HttpMethod.GET,"/api/v1/users/**","/api/v1/accounts/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                );
        httpSecurity.httpBasic(Customizer.withDefaults());

        // disable csrf
        httpSecurity.csrf(token->token.disable());
        httpSecurity.sessionManagement(session->session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return httpSecurity.build();
    }
}


