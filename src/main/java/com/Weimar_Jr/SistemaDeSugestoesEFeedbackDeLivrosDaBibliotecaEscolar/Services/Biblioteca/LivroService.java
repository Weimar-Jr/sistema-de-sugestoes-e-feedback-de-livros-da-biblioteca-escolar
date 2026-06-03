package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Biblioteca;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AdicionarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AtualizarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.LivroDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.LivroMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.*;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.AlunoRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.LivroRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario.AlunoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LivroService {
    LivroRepository livroRepository;
    LivroMapper livroMapper;
    AlunoService alunoService;
    AlunoRepository alunoRepository;

    public LivroDTOResponse cadastrarLivro(AdicionarLivroDTORequest livroDTO) {
        Livro livro = livroMapper.toLivro(livroDTO);
        livroRepository.save(livro);
        return livroMapper.toLivroDTOResponse(livro);
    }

    public LivroDTOResponse obterLivroPorId(Long id) {
        Livro livro = acharLivroPorId(id);
        return livroMapper.toLivroDTOResponse(livro);
    }

    public LivroDTOResponse atualizarLivro(Long id, AtualizarLivroDTORequest livroDTO) {
        Livro livro = acharLivroPorId(id);
        livroMapper.toLivroAtualizar(livroDTO, livro);
        livroRepository.save(livro);
        return livroMapper.toLivroDTOResponse(livro);
    }

    public Livro acharLivroPorId(Long id) {
        return livroRepository.findById(id).orElseThrow(() -> new LivroNaoEncontradoException(id));
    }

    public void deletarLivro(Long id) {
        acharLivroPorId(id);
        livroRepository.deleteById(id);
    }

    @Transactional
    public LivroDTOResponse emprestarLivro(Long idLivro, Long idAluno) {
        Livro livro = acharLivroPorId(idLivro);
        Aluno aluno = alunoService.acharAlunoPeloId(idAluno);
        if (livro.getDisponivel()) {
            if(aluno.getLivroEmprestado() != null) {
                throw new AlunoJaPossuiUmLivroEmprestadoException();
            }
            livro.setDisponivel(false);
            livro.setAlunoEmprestado(aluno);
            aluno.setLivroEmprestado(livro);
            alunoRepository.save(aluno);
            livroRepository.save(livro);
            return livroMapper.toLivroDTOResponse(livro);
        } else {
            throw new LivroIndisponivelException(idLivro);
        }
    }
    @Transactional
        public LivroDTOResponse devolverLivro(Long idLivro) {
        Livro livro = acharLivroPorId(idLivro);
        Aluno aluno = livro.getAlunoEmprestado();
        if (!livro.getDisponivel()) {
            livro.setDisponivel(true);
            aluno.setLivroEmprestado(null);
            livro.setAlunoEmprestado(null);
            livroRepository.save(livro);
            alunoRepository.save(aluno);
            return livroMapper.toLivroDTOResponse(livro);
        } else {
            throw new LivroJaConstaComoDevolvidoException();
        }
    }

    public List<LivroDTOResponse> listarLivros() {
        List<LivroDTOResponse> livros = livroRepository.findAll().stream().map(livro -> livroMapper.toLivroDTOResponse(livro)).collect(java.util.stream.Collectors.toList());
        if(livros.isEmpty()) {
            throw new NenhumLivroCadastradoException();
        }
        return livros;
    }

    public List<LivroDTOResponse> listarLivrosDisponiveis() {
        List<LivroDTOResponse> livros = livroRepository.findByDisponivel(true).stream().map(livro -> livroMapper.toLivroDTOResponse(livro)).collect(java.util.stream.Collectors.toList());
        if(livros.isEmpty()) {
            throw new NenhumLivroDisponivelException();
        }
        return livros;
    }

    public List<LivroDTOResponse> listarLivrosIndisponiveis() {
        List<LivroDTOResponse> livros = livroRepository.findByDisponivel(false).stream().map(livro -> livroMapper.toLivroDTOResponse(livro)).collect(java.util.stream.Collectors.toList());
        if(livros.isEmpty()) {
            throw new NenhumLivroIndisponivelException();
        }
        return livros;
    }

    public List<LivroDTOResponse> listarLivrosPorGenero(String genero) {
        List<LivroDTOResponse> livros = livroRepository.findByGenero(genero).stream().map(livro -> livroMapper.toLivroDTOResponse(livro)).collect(java.util.stream.Collectors.toList());
        if(livros.isEmpty()) {
            throw new NenhumLivroDesseGeneroException(genero);
        }
        return livros;
    }

    public List<LivroDTOResponse> listarLivrosPorAutor(String autor) {
        List<LivroDTOResponse> livros = livroRepository.findByAutor(autor).stream().map(livro -> livroMapper.toLivroDTOResponse(livro)).collect(java.util.stream.Collectors.toList());
        if(livros.isEmpty()) {
            throw new NenhumLivroDesseAutorException(autor);
        }
        return livros;
    }

    public List<LivroDTOResponse> listarLivrosPorTitulo(String titulo) {
        List<LivroDTOResponse> livros = livroRepository.findByTitulo(titulo).stream().map(livro -> livroMapper.toLivroDTOResponse(livro)).collect(java.util.stream.Collectors.toList());
        if(livros.isEmpty()) {
            throw new NenhumLivroAchadoPelotituloFaladoException(titulo);
        }
        return livros;
    }
}
