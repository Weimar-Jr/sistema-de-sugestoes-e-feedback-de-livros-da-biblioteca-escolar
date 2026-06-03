package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.FeedbackException;

public class NenhumFeedbackDoLivroFaladoException  extends RuntimeException {
    public NenhumFeedbackDoLivroFaladoException(Long idLivro) {
        super("Nenhum feedback encontrado para o livro com ID: " + idLivro);
    }
}
