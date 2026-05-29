package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Administrador;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AdministradorDTOResponse(
        @JsonProperty("id_administrador")
        Long id,
        @JsonProperty("nome_administrador")
        String nome,
        @JsonProperty("email_administrador")
        String email,
        @JsonProperty("cpf_administrador")
        String cpf
) {
}
