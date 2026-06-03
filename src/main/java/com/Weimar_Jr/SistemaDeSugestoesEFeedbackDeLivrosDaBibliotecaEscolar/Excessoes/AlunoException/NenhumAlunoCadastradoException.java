package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException;

public class NenhumAlunoCadastradoException  extends RuntimeException {
    public NenhumAlunoCadastradoException() {
        super("Nenhum aluno cadastrado na base de dados.");
    }
}
