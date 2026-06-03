package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException;

public class NenhumLivroDisponivelException  extends RuntimeException {
    public NenhumLivroDisponivelException() {
        super("Nenhum livro disponível para empréstimo no momento.");
    }
}
