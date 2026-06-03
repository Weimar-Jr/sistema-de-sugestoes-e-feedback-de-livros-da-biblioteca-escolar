package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Administrador;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AtualizarAdministradorDTORequest(
        @JsonProperty("nome_administrador")
        String nome,
        @JsonProperty("email_administrador")
        String email,
        @JsonProperty("senha_administrador")
        String senha

) {
}
