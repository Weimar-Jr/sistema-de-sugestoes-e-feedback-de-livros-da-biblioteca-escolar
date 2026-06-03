package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Controller.Biblioteca;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AdicionarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AtualizarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.LivroDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Biblioteca.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/livros")
public class LivroController {

    final LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroDTOResponse> adicionarLivro(@RequestBody @Valid AdicionarLivroDTORequest livroDTO) {
        LivroDTOResponse livroDTOResponse = livroService.cadastrarLivro(livroDTO);
        return ResponseEntity.status(201).body(livroDTOResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTOResponse> obterLivroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.obterLivroPorId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarLivro(@PathVariable Long id, @RequestBody @Valid AtualizarLivroDTORequest livroDTO) {
        livroService.atualizarLivro(id, livroDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/emprestar-livro/{idLivro}/aluno/{idAluno}")
    public ResponseEntity<Void> emprestarLivro(@PathVariable Long idLivro, @PathVariable Long idAluno) {
        livroService.emprestarLivro(idLivro, idAluno);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/devolver-livro/{idLivro}")
    public ResponseEntity<Void> devolverLivro(@PathVariable Long idLivro) {
        livroService.devolverLivro(idLivro);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<LivroDTOResponse>> listarLivrosDisponiveis() {
        return ResponseEntity.ok(livroService.listarLivrosDisponiveis());
    }

    @GetMapping("/emprestados")
    public ResponseEntity<List<LivroDTOResponse>> listarLivrosEmprestados() {
        return ResponseEntity.ok(livroService.listarLivrosIndisponiveis());
    }

    @GetMapping
    public ResponseEntity<List<LivroDTOResponse>> listarTodosOsLivros() {
        return ResponseEntity.ok(livroService.listarLivros());
    }

    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<LivroDTOResponse>> listarLivrosPorAutor(@PathVariable String autor) {
        return ResponseEntity.ok(livroService.listarLivrosPorAutor(autor));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LivroDTOResponse>> listarLivrosPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(livroService.listarLivrosPorTitulo(titulo));
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<LivroDTOResponse>> listarLivrosPorGenero(@PathVariable String genero) {
        return ResponseEntity.ok(livroService.listarLivrosPorGenero(genero));
    }

}
