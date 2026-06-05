package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AdministradorException.ExceptionDeNegocio;

public class JaTemAdminComEsseEmailException  extends RuntimeException{
    public JaTemAdminComEsseEmailException(String email) {
        super("Ja existe um administrador com o email: " + email);
    }
}
