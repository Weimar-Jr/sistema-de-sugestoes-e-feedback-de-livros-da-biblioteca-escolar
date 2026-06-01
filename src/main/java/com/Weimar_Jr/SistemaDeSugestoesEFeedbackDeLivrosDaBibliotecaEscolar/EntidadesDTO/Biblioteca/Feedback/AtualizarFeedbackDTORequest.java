package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public record AtualizarFeedbackDTORequest(
        @NotBlank @JsonProperty("comentario")
        String comentario,
        @NotNull @JsonProperty("avaliacao")
        int avaliacao,
        @NotNull @JsonProperty("visivel")
        Boolean visivel
)
{
}
