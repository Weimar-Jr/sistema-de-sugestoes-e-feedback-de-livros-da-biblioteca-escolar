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

    String admin = "ADMIN";
    String aluno = "ALUNO";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/aluno").permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/v3/api-docs").permitAll()
                        .requestMatchers(HttpMethod.GET, "/aluno").hasRole(admin)
                        .requestMatchers(HttpMethod.GET, "/admin").hasRole(admin)
                        .requestMatchers(HttpMethod.POST, "/admin").hasRole(admin)
                        .requestMatchers(HttpMethod.PATCH, "/admin/atualizar").hasRole(admin)
                        .requestMatchers(HttpMethod.DELETE, "/admin/**").hasRole(admin)
                        .requestMatchers(HttpMethod.GET, "/admin/cpf/**", "/admin/email/**").hasRole(admin)

                        .requestMatchers(HttpMethod.POST, "/livros").hasRole(admin)
                        .requestMatchers(HttpMethod.GET, "/livros").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/livros/{id}").hasRole(admin)
                        .requestMatchers(HttpMethod.DELETE, "/livros/{id}").hasRole(admin)
                        .requestMatchers("/livros/emprestar-livro/**", "/livros/devolver-livro/**").hasRole(admin)
                        .requestMatchers(HttpMethod.GET, "/aluno/email/{email}").hasRole(admin)

                        .requestMatchers(HttpMethod.GET, "/feedbacks/por-aluno/{idAluno}").hasRole(admin)
                        .requestMatchers(HttpMethod.DELETE, "/feedbacks/{id}").hasRole(admin)
                        .requestMatchers(HttpMethod.PATCH, "/editar-feedback/{id}").hasRole(admin)
                        .requestMatchers("/feedbacks/ocultar-nome-no-feedback/**",
                                "/feedbacks/exibir-nome-no-feedback/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/feedbacks/{id}").hasRole(admin)

                        .requestMatchers(HttpMethod.PATCH, "/aluno/atualizar").hasRole(aluno)
                        .requestMatchers(HttpMethod.POST, "/feedbacks").hasRole(aluno)
                        .requestMatchers(HttpMethod.PATCH, "/feedbacks/meus-feedbacks/{id}").hasRole(aluno)                        .requestMatchers(HttpMethod.GET, "/aluno/meus-feedbacks").hasRole(aluno)

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