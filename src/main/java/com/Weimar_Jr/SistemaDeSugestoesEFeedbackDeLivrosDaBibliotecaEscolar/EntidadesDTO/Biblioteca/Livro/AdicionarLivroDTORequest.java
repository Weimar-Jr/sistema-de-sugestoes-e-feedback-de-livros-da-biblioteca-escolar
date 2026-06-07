package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdicionarLivroDTORequest(
        @NotBlank @JsonProperty("titulo")
        String titulo,
        @NotBlank  @JsonProperty("autor")
        String autor,
        @NotBlank @JsonProperty("editora")
        String editora,
        @NotNull @JsonProperty("anoPublicacao")
        int anoPublicacao,
        @NotBlank @JsonProperty("genero")
        String genero,
        @NotNull @JsonProperty("disponivel")
        Boolean disponivel,
        @NotBlank @JsonProperty("descricao")
        String descricao) {
}
