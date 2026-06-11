package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public record AtualizarFeedbackDTORequest(
        @JsonProperty("comentario")
        String comentario,
        @JsonProperty("avaliacao")
        @DecimalMax(value = "5.0", message = "Avaliação deve ser no maximo 5")
        @DecimalMin(value = "0.0", message = "Avaliação deve ser no minimo 0")
        Double avaliacao,
        @JsonProperty("nome_do_aluno_visivel")
        Boolean nomeDoAlunoVisivel
){ }
