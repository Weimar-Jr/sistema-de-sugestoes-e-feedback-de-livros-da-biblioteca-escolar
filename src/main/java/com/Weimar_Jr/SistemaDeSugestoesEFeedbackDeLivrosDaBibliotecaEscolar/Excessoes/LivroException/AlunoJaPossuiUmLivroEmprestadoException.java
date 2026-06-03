package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException;

public class AlunoJaPossuiUmLivroEmprestadoException extends RuntimeException {
    public AlunoJaPossuiUmLivroEmprestadoException() {
        super("O aluno já tem um livro emprestado. Ele deve devolver o livro atual antes de pegar outro emprestado.");
    }
}
