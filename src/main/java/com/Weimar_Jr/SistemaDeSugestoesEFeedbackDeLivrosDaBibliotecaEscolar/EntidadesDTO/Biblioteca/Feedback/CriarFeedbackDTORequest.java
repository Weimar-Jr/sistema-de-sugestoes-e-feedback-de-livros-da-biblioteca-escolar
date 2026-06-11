package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarFeedbackDTORequest(
        @NotBlank @JsonProperty("comentario")
        String comentario,
        @NotNull @JsonProperty("avaliacao")
        @DecimalMax(value = "5.0", message = "Avaliação deve ser no maximo 5")
        @DecimalMin(value = "0.0", message = "Avaliaçãod eve ser no minimo 0")
        Double avaliacao,
        @NotNull @JsonProperty("nome_do_aluno_visivel")
        Boolean nomeDoAlunoVisivel,
        @NotNull @JsonProperty("livro_id")
        Long idLivro

) {
}
