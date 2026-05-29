package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.FeedbackDTOResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AlunoDTOResponse(
        @JsonProperty("id_aluno")
        Long id,
        @JsonProperty("nome_aluno")
        String nome,
        @JsonProperty("email_aluno")
        String email,
        @JsonProperty("registro_de_aluno")
        String registroDeAluno,
        @JsonProperty("feedbacks")
        List<FeedbackDTOResponse> feedbacks
) {
}
