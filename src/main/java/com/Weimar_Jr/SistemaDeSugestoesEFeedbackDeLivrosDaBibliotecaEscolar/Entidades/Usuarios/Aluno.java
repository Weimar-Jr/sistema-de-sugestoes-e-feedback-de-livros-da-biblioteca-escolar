package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Feedback;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @NotBlank @Column(unique = true)
    private String registroDeAluno;
    @Getter @Setter @NotBlank
    private String nome;
    @Getter @Setter @NotBlank @Column(unique = true)
    private String email;
    @Getter @Setter @NotBlank
    private String senha;

    @OneToMany(mappedBy = "aluno")
    private List<Feedback> feedbacks;
    @OneToOne
    private Livro livroEmprestado;
}
