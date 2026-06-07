package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarLivroDTORequest(
        @JsonProperty("titulo")
        String titulo,
        @JsonProperty("autor")
        String autor,
        @JsonProperty("editora")
        String editora,
        @JsonProperty("anoPublicacao")
        Integer anoPublicacao,
        @JsonProperty("descricao")
        String descricao,
        @JsonProperty("genero")
        String genero,
        @JsonProperty("disponivel")
        Boolean disponivel



) {
}
