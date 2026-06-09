package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AtualizarFeedbackDTORequest(
        @JsonProperty("comentario")
        String comentario,
        @JsonProperty("avaliacao")
        Double avaliacao,
        @JsonProperty("nome_do_aluno_visivel")
        Boolean nomeDoAlunoVisivel
)
{
}
