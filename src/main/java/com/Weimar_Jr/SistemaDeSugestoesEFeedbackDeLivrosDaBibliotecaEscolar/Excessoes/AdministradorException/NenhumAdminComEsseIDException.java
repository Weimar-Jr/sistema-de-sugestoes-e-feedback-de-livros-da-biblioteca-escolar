package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AdministradorException;

public class NenhumAdminComEsseIDException  extends RuntimeException {
    public NenhumAdminComEsseIDException(Long id) {
        super("Nenhum administrador encontrado com ID: " + id);
    }
}
