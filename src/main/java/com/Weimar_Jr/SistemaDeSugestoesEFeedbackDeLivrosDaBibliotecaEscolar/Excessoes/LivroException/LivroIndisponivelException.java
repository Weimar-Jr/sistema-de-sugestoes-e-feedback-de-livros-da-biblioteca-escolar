package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException;

public class LivroIndisponivelException  extends RuntimeException {
    public LivroIndisponivelException(Long id) {
        super("Livro com ID: " + id + " está indisponível para empréstimo.");
    }
}
