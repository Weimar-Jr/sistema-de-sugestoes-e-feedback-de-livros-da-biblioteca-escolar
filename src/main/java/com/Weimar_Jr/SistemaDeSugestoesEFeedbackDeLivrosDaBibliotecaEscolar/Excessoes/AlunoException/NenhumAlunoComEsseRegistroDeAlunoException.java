package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException;

public class NenhumAlunoComEsseRegistroDeAlunoException extends RuntimeException {
    public NenhumAlunoComEsseRegistroDeAlunoException(String registroDeAluno) {
        super("Nenhum aluno cadastrado com esse registro: " + registroDeAluno);
    }
}
