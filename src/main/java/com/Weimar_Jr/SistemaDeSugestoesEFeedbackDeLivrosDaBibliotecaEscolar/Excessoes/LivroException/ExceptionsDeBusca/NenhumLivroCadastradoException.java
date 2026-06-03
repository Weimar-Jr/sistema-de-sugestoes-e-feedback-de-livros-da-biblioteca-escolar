package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.ExceptionsDeBusca;

public class NenhumLivroCadastradoException  extends RuntimeException {
    public NenhumLivroCadastradoException() {
        super("Nenhum livro cadastrado na biblioteca.");
    }
}
