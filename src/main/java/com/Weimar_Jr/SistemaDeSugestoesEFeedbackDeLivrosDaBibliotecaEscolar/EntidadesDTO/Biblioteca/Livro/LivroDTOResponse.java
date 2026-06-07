package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.FeedbackDTOResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record LivroDTOResponse(
        @JsonProperty("id_livro")
        Long id,
        @JsonProperty("titulo")
        String titulo,
        @JsonProperty("autor")
        String autor,
        @JsonProperty("editora")
        String editora,
        @JsonProperty("anoPublicacao")
        int anoPublicacao,
        @JsonProperty("genero")
        String genero,
        @JsonProperty("disponivel")
        Boolean disponivel,
        @JsonProperty("descricao")
        String descricao,
        @JsonProperty("mediaAvaliacao")
        Double mediaAvaliacao,
        @JsonProperty("feedbacks")
        List<FeedbackDTOResponse> feedbacks
) {
}
