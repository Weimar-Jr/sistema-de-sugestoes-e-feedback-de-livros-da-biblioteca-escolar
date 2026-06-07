package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException;

public class JaTemLivroSemelhanteCadastradoExeption extends  RuntimeException {
    public JaTemLivroSemelhanteCadastradoExeption()
    {
        super("Ja tem livro semelhante cadastrado.");
    }
}
