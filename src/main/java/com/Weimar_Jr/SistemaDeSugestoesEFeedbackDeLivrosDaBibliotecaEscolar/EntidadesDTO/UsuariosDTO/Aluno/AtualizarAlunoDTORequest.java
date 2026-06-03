package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno;

import com.fasterxml.jackson.annotation.JsonProperty;


public record AtualizarAlunoDTORequest(
        @JsonProperty("nome_aluno")
        String nome,
        @JsonProperty("email_aluno")
        String email,
        @JsonProperty("senha_aluno")
        String senha
) {
}
