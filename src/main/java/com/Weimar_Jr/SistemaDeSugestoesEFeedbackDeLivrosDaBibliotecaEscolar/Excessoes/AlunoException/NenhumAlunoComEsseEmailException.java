package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException;

public class NenhumAlunoComEsseEmailException  extends RuntimeException{
    public NenhumAlunoComEsseEmailException(String alunoEmail)
    {
        super("Não tem aluno com esse Email: " + alunoEmail);
    }

}
