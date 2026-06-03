package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AdministradorException;

public class NenhumAdministradorCadastradoExeption  extends RuntimeException {
    public NenhumAdministradorCadastradoExeption() {
        super("Nenhum administrador cadastrado na biblioteca.");
    }
}
