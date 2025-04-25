package com.grepp.nbe561team01.infra.config;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import com.grepp.nbe561team01.app.model.auth.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Value("${remember-me.key}")
    private String rememberMeKey;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {

                boolean isAdmin = authentication.getAuthorities()
                    .stream()
                    .anyMatch(authority ->
                        authority.getAuthority().equals("ROLE_ADMIN"));

                if (isAdmin) {
                    response.sendRedirect("/admin");
                    return;
                }

                response.sendRedirect("/");
            }
        };

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // csrf 설정 (delete 요청 등에서 필요에 따라 활성화)
         http.csrf(csrf -> csrf.disable());

        http
            .authorizeHttpRequests((requests) -> requests

                .requestMatchers(GET, "/user/signin", "/user/signup").anonymous()
                .requestMatchers(POST, "/user/signin", "/user/signup").anonymous()
                .requestMatchers(GET, "admin/signup").permitAll()
                .requestMatchers(POST, "admin/signup").permitAll()
                .requestMatchers(GET, "/api/**").permitAll()
                .requestMatchers(GET, "/", "/user/logout", "/user/mypage").hasAnyRole("ADMIN", "USER")
                .requestMatchers(PUT, "/**").permitAll()  // PUT
                .requestMatchers(DELETE, "/**").permitAll()  // DELETE
                .requestMatchers("/assets/**").permitAll() // 정적 리소스 허용
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/user/signin")
                .usernameParameter("email")
                .failureUrl("/user/signin?error=true")
                .loginProcessingUrl("/user/signin")
                .defaultSuccessUrl("/")
                .successHandler(successHandler())
                .permitAll()
            )
            .rememberMe(rememberMe -> rememberMe.key(rememberMeKey))
            .logout(logout -> logout
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/user/signin")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );


        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
