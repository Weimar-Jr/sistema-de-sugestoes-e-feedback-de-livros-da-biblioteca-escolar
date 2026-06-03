package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.FeedbackException;

public class NenhumFeedbackComEsseIdException  extends RuntimeException {
    public NenhumFeedbackComEsseIdException(Long id) {
        super("Nenhum feedback encontrado com ID: " + id);
    }
}
