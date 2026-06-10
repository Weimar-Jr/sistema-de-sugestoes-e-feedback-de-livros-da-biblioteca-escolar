package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.LivroDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AlunoDTOResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarFeedbackDTORequest(
        @NotBlank @JsonProperty("comentario")
        String comentario,
        @NotNull @JsonProperty("avaliacao")
        Double avaliacao,
        @NotNull @JsonProperty("nome_do_aluno_visivel")
        Boolean nomeDoAlunoVisivel,
        @NotNull @JsonProperty("livro_id")
        Long idLivro

) {
}
