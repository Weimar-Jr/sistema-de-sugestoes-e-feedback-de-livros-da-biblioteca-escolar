package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Controller.Biblioteca;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AdicionarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AtualizarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Biblioteca.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/livros")
public class LivroController {

    final LivroService livroService;


    public void adicionarLivro(@RequestBody AdicionarLivroDTORequest livroDTO) {
        livroService.cadastrarLivro(livroDTO);
    }

    @RequestMapping("/{id}")
    public void obterLivroPorId(@PathVariable Long id) {
        livroService.obterLivroPorId(id);
    }

    @RequestMapping("/editar-livro/{id}")
    public void atualizarLivro(@PathVariable Long id, @RequestBody AtualizarLivroDTORequest livroDTO) {
        livroService.atualizarLivro(id, livroDTO);
    }

    @RequestMapping("/deletar-livro/{id}")
    public void deletarLivro(@PathVariable Long id) {
        livroService.deletarLivro(id);
    }
    @RequestMapping("/emprestar-livro/{idLivro}/aluno/{idAluno}")
    public void emprestarLivro(@PathVariable Long idLivro, @PathVariable Long idAluno) {
        livroService.emprestarLivro(idLivro, idAluno);
    }

    @RequestMapping("/devolver-livro/{idLivro}")
    public void devolverLivro(@PathVariable Long idLivro) {
        livroService.devolverLivro(idLivro);
    }

    @RequestMapping("/disponiveis")
    public void listarLivrosDisponiveis() {
        livroService.listarLivrosDisponiveis();
    }

    @RequestMapping("/emprestados")
    public void listarLivrosEmprestados() {
        livroService.listarLivrosIndisponiveis();
    }

    @RequestMapping("/todos")
    public void listarTodosOsLivros() {
        livroService.listarLivros();
    }

    @RequestMapping("/autor/{autor}")
    public void listarLivrosPorAutor(@PathVariable String autor) {
        livroService.listarLivrosPorAutor(autor);
    }

    @RequestMapping("/titulo/{titulo}")
    public void listarLivrosPorTitulo(@PathVariable String titulo) {
        livroService.listarLivrosPorTitulo(titulo);
    }

    @RequestMapping("/genero/{genero}")
    public void listarLivrosPorGenero(@PathVariable String genero) {
        livroService.listarLivrosPorGenero(genero);
    }

}
