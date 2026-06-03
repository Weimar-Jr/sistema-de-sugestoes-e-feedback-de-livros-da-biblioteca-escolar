package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.FeedbackException;

public class NenhumFeedbackDesseAlunoException  extends RuntimeException {
    public NenhumFeedbackDesseAlunoException(Long idAluno) {
        super("Nenhum feedback encontrado para o aluno com ID: " + idAluno);
    }
}
