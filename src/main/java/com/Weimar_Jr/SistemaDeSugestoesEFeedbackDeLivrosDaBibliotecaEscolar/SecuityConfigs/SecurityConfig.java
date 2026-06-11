package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.SecuityConfigs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final FiltroDeAutorizacaoJwt filtroDeAutorizacaoJwt;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/aluno").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/aluno").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/admin/atualizar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/admin/cpf/**", "/admin/email/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/livros").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/livros/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/livros/{id}").hasRole("ADMIN")
                        .requestMatchers("/livros/emprestar-livro/**", "/livros/devolver-livro/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/feedbacks/por-aluno/{idAluno}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/feedbacks/{id}").hasRole("ADMIN")
                        .requestMatchers("/feedbacks/ocultar-nome-no-feedback/**",
                                "/feedbacks/exibir-nome-no-feedback/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/feedbacks/{id}").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PATCH, "/aluno/atualizar").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.POST, "/feedbacks").hasRole("ALUNO")
                        .requestMatchers(HttpMethod.GET, "/aluno/meus-feedbacks").hasRole("ALUNO")

                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(filtroDeAutorizacaoJwt, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}