package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.SecuityConfigs.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
public class Administrador implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotBlank
        private String nome;
        @NotBlank
        private String email;
        @NotBlank
        private String senha;
        @NotBlank @Column(unique = true)
        private String cpf;

        @Enumerated(EnumType.STRING)
        Role role = Role.ROLE_ADMIN;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority(role.name()));
        }
        @Override
        public String getPassword() {
                return this.senha;
        }

        @Override
        public String getUsername() {
                return this.email;
        }
        @Override
        public boolean isAccountNonExpired()
        {
                return true;
        }
        @Override
        public boolean isAccountNonLocked()
        {
                return true;
        }
        @Override
        public boolean isCredentialsNonExpired()
        {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }


}
