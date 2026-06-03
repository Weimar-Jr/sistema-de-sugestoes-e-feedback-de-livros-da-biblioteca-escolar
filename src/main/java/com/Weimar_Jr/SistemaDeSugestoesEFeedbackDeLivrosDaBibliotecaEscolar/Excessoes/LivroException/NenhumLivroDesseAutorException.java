package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException;

public class NenhumLivroDesseAutorException  extends RuntimeException {
    public NenhumLivroDesseAutorException(String autor) {
        super("Nenhum livro do autor '" + autor + "' encontrado na biblioteca.");
    }
}
