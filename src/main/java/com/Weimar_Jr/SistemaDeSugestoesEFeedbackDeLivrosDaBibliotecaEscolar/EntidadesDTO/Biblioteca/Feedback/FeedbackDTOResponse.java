package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.LivroDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AlunoDTOResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public record FeedbackDTOResponse(
        @JsonProperty("id_feedback")
        Long id,
        @JsonProperty("comentario")
        String comentario,
        @JsonProperty("avaliacao")
        int avaliacao,
        @JsonProperty("visivel")
        Boolean visivel,
        @JsonProperty("aluno_id")
        Long idAluno,
        @JsonProperty("nome_aluno")
        String nomeAluno,
        @JsonProperty("livro_id")
        Long idLivro,
        @JsonProperty("titulo_livro")
        String nomeLivro
) {
}
