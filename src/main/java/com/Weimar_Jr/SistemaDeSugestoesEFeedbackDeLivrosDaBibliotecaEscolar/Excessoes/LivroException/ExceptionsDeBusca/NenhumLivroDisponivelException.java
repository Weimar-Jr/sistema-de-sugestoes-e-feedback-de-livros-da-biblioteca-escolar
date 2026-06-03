package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.ExceptionsDeBusca;

public class NenhumLivroDisponivelException  extends RuntimeException {
    public NenhumLivroDisponivelException() {
        super("Nenhum livro disponível para empréstimo no momento.");
    }
}
