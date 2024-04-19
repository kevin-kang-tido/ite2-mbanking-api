package co.istad.ite2mbanking.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private  final PasswordEncoder passwordEncoder;
    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){

       InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

       UserDetails userAdmin =  User.builder()
               .username("admin")
               .password(passwordEncoder.encode("admin"))
               .roles("USER","ADMIN")
               .build();

        UserDetails userEditer =  User.builder()
                .username("editor")
                .password(passwordEncoder.encode("editor"))
                .roles("USER","ADMIN")
                .build();
        UserDetails staff =  User.builder()
                .username("staff")
                .password(passwordEncoder.encode("staff"))
                .roles("STAFF")
                .build();
        UserDetails customer =  User.builder()
                .username("customer")
                .password(passwordEncoder.encode("customer"))
                .roles("CUSTOMER")
                .build();

        manager.createUser(userAdmin);
        manager.createUser(userEditer);
        manager.createUser(staff);
        manager.createUser(customer);

        return manager;
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


