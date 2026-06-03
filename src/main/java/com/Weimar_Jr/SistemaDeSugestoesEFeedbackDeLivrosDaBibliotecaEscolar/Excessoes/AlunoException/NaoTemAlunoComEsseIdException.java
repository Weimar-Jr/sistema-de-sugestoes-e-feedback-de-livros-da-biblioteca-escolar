package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException;

public class NaoTemAlunoComEsseIdException  extends RuntimeException {
    public NaoTemAlunoComEsseIdException(Long id) {
        super("Não existe um aluno com o ID: " + id);
    }
}
