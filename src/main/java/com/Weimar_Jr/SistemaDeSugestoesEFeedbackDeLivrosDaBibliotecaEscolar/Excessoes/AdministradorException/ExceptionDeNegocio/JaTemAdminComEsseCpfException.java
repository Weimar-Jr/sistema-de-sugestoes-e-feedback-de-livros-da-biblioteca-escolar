package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AdministradorException.ExceptionDeNegocio;

public class JaTemAdminComEsseCpfException extends RuntimeException {
    public JaTemAdminComEsseCpfException(String cpf) {
        super("Ja existe um administrador com esse CPF: " + cpf);
    }
}
