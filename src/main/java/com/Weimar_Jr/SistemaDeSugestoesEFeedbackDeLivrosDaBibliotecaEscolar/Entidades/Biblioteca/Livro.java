package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String autor;
    @NotBlank
    private String editora;
    @NotNull
    @Column(name = "ano_publicacao")
    private int anoPublicacao;
    @NotBlank
    private String genero;
    @NotNull
    private Boolean disponivel;
    @NotBlank
    private String descricao;
    @Column(columnDefinition = "DOUBLE PRECISION DEFAULT 0.0", name = "media_avaliacao")
    private Double mediaAvaliacao;

    @Column(name = "livro_emprestado_id")
    @OneToOne(mappedBy = "livroEmprestado", cascade = CascadeType.ALL)
    private Aluno alunoEmprestado;
    @OneToMany(mappedBy = "livro")
    private List<Feedback> feedbacks = new ArrayList<>();

    public void adicionarFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }
}
