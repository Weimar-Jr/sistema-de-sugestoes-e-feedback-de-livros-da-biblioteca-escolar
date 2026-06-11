package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Biblioteca;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AdicionarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AtualizarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.LivroDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.LivroMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.*;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.ExceptionsDeBusca.*;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.AlunoRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.LivroRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario.AlunoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;
    private final AlunoService alunoService;
    private final AlunoRepository alunoRepository;

    public LivroDTOResponse cadastrarLivro(AdicionarLivroDTORequest livroDTO) {
        verificarSeJaTemLivro(livroDTO.autor(), livroDTO.titulo(), livroDTO.editora());
        Livro livro = livroMapper.toLivro(livroDTO);
        livroRepository.save(livro);
        return livroMapper.toLivroDTOResponse(livro);

    }

    public LivroDTOResponse obterLivroPorId(Long id) {
        Livro livro = acharLivroPorId(id);
        return livroMapper.toLivroDTOResponse(livro);
    }

    public void atualizarLivro(Long id, AtualizarLivroDTORequest livroDTO) {
        Livro livro = acharLivroPorId(id);
        livroMapper.toLivroAtualizar(livroDTO, livro);
        livroRepository.save(livro);
    }

    public Livro acharLivroPorId(Long id) {
        return livroRepository.findById(id).orElseThrow(() -> new LivroNaoEncontradoException(id));
    }

    public void deletarLivro(Long id) {
        acharLivroPorId(id);
        livroRepository.deleteById(id);
    }

    @Transactional
    public void emprestarLivro(Long idLivro, Long idAluno) {
        Livro livro = acharLivroPorId(idLivro);
        Aluno aluno = alunoService.acharAlunoPeloId(idAluno);

        if (livro.getDisponivel().equals(true)) {
            if (aluno.getLivroEmprestado() != null) {
                throw new AlunoJaPossuiUmLivroEmprestadoException();
            }
            livro.setDisponivel(false);
            livro.setAlunoEmprestado(aluno);
            aluno.setLivroEmprestado(livro);
            alunoRepository.save(aluno);
            livroRepository.save(livro);
        } else {
            throw new LivroIndisponivelException(idLivro);
        }
    }
    @Transactional
        public void devolverLivro(Long idLivro) {
        Livro livro = acharLivroPorId(idLivro);
        Aluno aluno = livro.getAlunoEmprestado();
        if (!livro.getDisponivel().equals(true)) {
            livro.setDisponivel(true);
            aluno.setLivroEmprestado(null);
            livro.setAlunoEmprestado(null);
            livroRepository.save(livro);
            alunoRepository.save(aluno);
        } else {
            throw new LivroJaConstaComoNaoEmprestadoException();
        }
    }

    public List<LivroDTOResponse> listarLivros() {
        return livroRepository.findAll().stream().map(livroMapper::toLivroDTOResponse).toList();
    }

    public List<LivroDTOResponse> listarLivrosDisponiveis() {
        return livroRepository.findByDisponivel(true).stream().map(livroMapper::toLivroDTOResponse).toList();
    }

    public List<LivroDTOResponse> listarLivrosIndisponiveis() {
        return livroRepository.findByDisponivel(false).stream().map(livroMapper::toLivroDTOResponse).toList();
    }

    public List<LivroDTOResponse> listarLivrosPorGenero(String genero) {
        return livroRepository.findByGenero(genero).stream().map(livroMapper::toLivroDTOResponse).toList();
    }

    public List<LivroDTOResponse> listarLivrosPorAutor(String autor) {
        return livroRepository.findByAutor(autor).stream().map(livroMapper::toLivroDTOResponse).toList();
    }

    public List<LivroDTOResponse> listarLivrosPorTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo).stream().map(livroMapper::toLivroDTOResponse).toList();
    }
    private void verificarSeJaTemLivro(String autor, String titulo, String editora)
    {
        Optional<Livro> livro = livroRepository.findByAutorTituloEEditora(autor,titulo , editora);
        if(livro.isPresent())
        {
             throw new JaTemLivroSemelhanteCadastradoExeption();
        }
    }

}
