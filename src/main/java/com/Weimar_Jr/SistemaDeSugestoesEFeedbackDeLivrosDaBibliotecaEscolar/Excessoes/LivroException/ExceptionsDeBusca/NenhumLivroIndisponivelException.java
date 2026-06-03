package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.ExceptionsDeBusca;

public class NenhumLivroIndisponivelException extends RuntimeException {
    public NenhumLivroIndisponivelException() {
        super("Nenhum livro indisponível para empréstimo no momento.");
    }
}
