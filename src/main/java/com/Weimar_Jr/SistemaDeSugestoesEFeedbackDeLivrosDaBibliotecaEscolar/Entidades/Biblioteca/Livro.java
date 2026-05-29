package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String autor;
    @NotNull
    private int anoPublicacao;
    @NotBlank
    private String genero;
    @NotNull
    private Boolean disponivel;
    @NotBlank
    private String descricao;
    @NotNull
    @Column(columnDefinition = "DOUBLE PRECISION DEFAULT 0.0")
    private Double mediaAvaliacao;
    @OneToOne(mappedBy = "livroEmprestado")
    private Aluno alunoEmprestado;
    @OneToMany(mappedBy = "livro")
    private List<Feedback> feedbacks;
}
