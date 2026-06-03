package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT AVG(f.avaliacao) FROM Feedback f WHERE f.livro.id = :idLivro")
    Double mediaAvaliacaoByLivroId(@Param("idLivro") Long idLivro);
    List<Feedback> findFeedbacksByLivroId(Long idLivro);
    List<Feedback> findFeedbacksByAlunoId(Long idAluno);
}
