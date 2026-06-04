package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.ExceptionDeNegocio;


public class JaTemAlunoComEsseEmailException extends RuntimeException {
    public JaTemAlunoComEsseEmailException(String email) {
        super("Já existe um aluno cadastrado com o email: " + email);
    }
}
