package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AtualizarAlunoDTORequest(
        @NotBlank @JsonProperty("nome_aluno")
        String nome,
        @NotBlank @JsonProperty("email_aluno")
        String email,
        @NotBlank @JsonProperty("senha_aluno")
        String senha
) {
}
