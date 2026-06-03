package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException;

public class NenhumLivroDesseGeneroException  extends RuntimeException {
    public NenhumLivroDesseGeneroException(String genero) {
        super("Nenhum livro do gênero '" + genero + "' encontrado na biblioteca.");
    }
}
