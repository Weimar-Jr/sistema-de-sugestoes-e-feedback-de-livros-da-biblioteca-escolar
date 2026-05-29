package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Administrador {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotBlank
        private String nome;
        @NotBlank
        private String email;
        @NotBlank
        private String senha;
        @NotBlank @Column(unique = true)
        private String cpf;

}
