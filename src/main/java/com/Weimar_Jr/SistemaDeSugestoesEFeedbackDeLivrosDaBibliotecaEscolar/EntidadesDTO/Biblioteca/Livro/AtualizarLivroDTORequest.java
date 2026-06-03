package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarLivroDTORequest(
        @JsonProperty("titulo")
        String titulo,
        @JsonProperty("autor")
        String autor,
        @JsonProperty("anoPublicacao")
        Integer anoPublicacao,
        @JsonProperty("genero")
        String genero,
        @JsonProperty("disponivel")
        Boolean disponivel,
        @JsonProperty("descricao")
        String descricao


) {
}
