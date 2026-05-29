package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CriarUsuarioAlunoDTORequest(
        @NotBlank @JsonProperty("nome_aluno")
        String nome,
        @JsonProperty("email_aluno")
        String email,
        @JsonProperty("senha_aluno")
        String senha,
        @JsonProperty("registro_de_aluno")
        String registroDeAluno
) {
}
