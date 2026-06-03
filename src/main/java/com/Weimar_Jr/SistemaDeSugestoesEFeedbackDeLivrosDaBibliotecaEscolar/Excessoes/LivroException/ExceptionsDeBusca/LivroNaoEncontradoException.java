package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException;

public class LivroNaoEncontradoException extends RuntimeException {
    public LivroNaoEncontradoException(Long id) {
        super("Livro não encontrado com ID: " + id);
    }
}
