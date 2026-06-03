package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException;

public class NenhumLivroAchadoPelotituloFaladoException extends RuntimeException {
    public NenhumLivroAchadoPelotituloFaladoException(String titulo) {
        super("Nenhum livro com o título '" + titulo + "' encontrado na biblioteca.");
    }
}
