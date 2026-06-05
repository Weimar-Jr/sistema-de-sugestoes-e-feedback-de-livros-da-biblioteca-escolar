package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AdministradorException;

public class NenhumAdminComEsseCpfException  extends RuntimeException {
    public NenhumAdminComEsseCpfException(String cpf)
    {
        super("Não existe nenhum administrador com esse cpf: " + cpf);
    }
}
