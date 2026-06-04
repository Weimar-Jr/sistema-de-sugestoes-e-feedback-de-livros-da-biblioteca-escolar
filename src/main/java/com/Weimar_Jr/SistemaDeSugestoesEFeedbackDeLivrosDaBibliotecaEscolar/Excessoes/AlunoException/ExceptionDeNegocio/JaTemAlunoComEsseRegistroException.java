package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.ExceptionDeNegocio;

public class JaTemAlunoComEsseRegistroException extends RuntimeException{
    public JaTemAlunoComEsseRegistroException()
    {
        super("Já existe um aluno cadastrado com o registro de aluno");
    }
}
