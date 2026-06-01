package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    private String comentario;
    @NotNull
    private int avaliacao;
    @NotNull
    private Boolean nickVisivel;

    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;
    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

}
