package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException;

public class LivroJaConstaComoDevolvidoException  extends RuntimeException {
    public LivroJaConstaComoDevolvidoException() {
        super("O livro já consta como devolvido.");
    }
}
