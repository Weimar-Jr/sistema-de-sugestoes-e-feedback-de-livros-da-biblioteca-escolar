package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Administrador;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CriarUsuarioAdministradorDTORequest(
        @NotBlank @JsonProperty("nome")
        String nome,
        @NotBlank @JsonProperty("email")
        String email,
        @NotBlank @JsonProperty("senha")
        String senha,
        @NotBlank @JsonProperty("cpf")
        String cpf
) {
}
