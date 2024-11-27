package com.virginiabank.bankdemo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	 http
         .authorizeRequests() // Use this method for older versions
         .anyRequest().authenticated()
         .and()
         .formLogin()
         .loginPage("/login")
         .permitAll()
         .and()
         .logout()
         .logoutUrl("/logout")
         .logoutSuccessUrl("/login?logout");

     return http.build();
//     
//        http.authorizeHttpRequests(auth -> auth
//                .anyRequest().authenticated()
//            )
//            .sessionManagement(session -> session
//                .maximumSessions(1) // Allow only one session per user
//                .expiredUrl("/login?expired=true") // Redirect to login on session expiration
//            );
//
//        return http.build();
    }

    /**
     * Register a session event publisher to handle session lifecycle events like timeout.
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}

