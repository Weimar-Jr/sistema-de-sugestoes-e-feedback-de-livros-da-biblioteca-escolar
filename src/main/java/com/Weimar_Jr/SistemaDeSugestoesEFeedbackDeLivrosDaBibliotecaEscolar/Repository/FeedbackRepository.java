package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT AVG(f.avaliacao) FROM Feedback f WHERE f.livro.id = :idLivro")
    Double mediaAvaliacaoByLivroId(@Param("idLivro") Long idLivro);
    List<Feedback> findFeedbacksByLivroId(Long idLivro);
    List<Feedback> findFeedbacksByAlunoId(Long idAluno);
    @Query("SELECT f FROM Feedback f JOIN FETCH f.aluno JOIN FETCH f.livro WHERE f.id = :id")
    Optional<Feedback> findByIdWithAssociations(@Param("id") Long id);
}
