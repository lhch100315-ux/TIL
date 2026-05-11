package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 보안을 잠시 풀어서 테스트를 편하게 함
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/public/**").permitAll() // 여기는 로그인 없이 통과!
                        .anyRequest().authenticated() // 나머지는 다 로그인해!
                )
                .formLogin(login -> login
                        .defaultSuccessUrl("/main", true) // 로그인 성공하면 이동할 주소
                        .permitAll()
                );

        return http.build();
    }

    // 테스트를 위해 임시로 메모리에 아이디/비번을 저장하는 설정
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("test") // 아이디를 test로 설정!
                .password("1234") // 비번을 1234로 설정!
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}