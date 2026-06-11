package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.titulo LIKE %:titulo%")
    List<Livro> findByTitulo(String titulo);
    List<Livro> findByGenero(String genero);
    List<Livro> findByDisponivel(Boolean disponivel);
    @Query("SELECT l FROM Livro l WHERE l.autor LIKE %:autor%")
    List<Livro> findByAutor(String autor);
    @Query("SELECT l FROM Livro l WHERE l.autor = :autor AND l.titulo = :titulo AND l.editora = :editora")
    Optional<Livro> findByAutorTituloEEditora(String autor, String titulo, String editora);

}
