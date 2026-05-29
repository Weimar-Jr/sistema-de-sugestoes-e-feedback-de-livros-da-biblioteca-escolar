package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Administrador;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarAdministradorDTOResquest(
        @NotBlank @JsonProperty("nome_administrador")
        String nome,
        @NotBlank @JsonProperty("email_administrador")
        String email,
        @NotBlank @JsonProperty("senha_administrador")
        String senha

) {
}
