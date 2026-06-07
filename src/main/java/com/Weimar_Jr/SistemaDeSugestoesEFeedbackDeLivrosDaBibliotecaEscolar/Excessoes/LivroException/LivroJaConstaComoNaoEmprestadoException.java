package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException;

public class LivroJaConstaComoNaoEmprestadoException extends RuntimeException {
    public LivroJaConstaComoNaoEmprestadoException() {
        super("O livro já consta como devolvido.");
    }
}
