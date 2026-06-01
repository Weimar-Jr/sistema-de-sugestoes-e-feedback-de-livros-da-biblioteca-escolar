package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Feedback;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate
@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(unique = true, name = "registro_de_aluno")
    private String registroDeAluno;
    @NotBlank
    private String nome;
    @NotBlank @Column(unique = true)
    private String email;
    @NotBlank
    private String senha;

    @OneToMany(mappedBy = "aluno")
    private List<Feedback> feedbacks = new ArrayList<>();
    @Column(name = "livro_emprestado_id")
    @OneToOne
    private Livro livroEmprestado;

    public void adiconarFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }
}
