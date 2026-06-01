package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Biblioteca;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AdicionarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AtualizarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.LivroMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.AlunoRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.LivroRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LivroService {
    LivroRepository livroRepository;
    LivroMapper livroMapper;
    AlunoService alunoService;
    AlunoRepository alunoRepository;

    public void cadastrarLivro(AdicionarLivroDTORequest livroDTO) {
        Livro livro = livroMapper.toLivro(livroDTO);
        livroRepository.save(livro);
    }

    public void atualizarLivro(Long id, AtualizarLivroDTORequest livroDTO) {
        Livro livro = acharLivroPorId(id);
        livroMapper.toLivroAtualizar(livroDTO, livro);
        livroRepository.save(livro);
    }

    public Livro acharLivroPorId(Long id) {
        return livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public void deletarLivro(Long id) {
        livroRepository.deleteById(id);
    }

    public void emprestarLivro(Long idLivro, Long idAluno) {
        Livro livro = acharLivroPorId(idLivro);
        Aluno aluno = alunoService.acharAlunoPeloId(idAluno);
        if (livro.getDisponivel()) {
            if(aluno.getLivroEmprestado() != null) {
                throw new RuntimeException("Aluno já possui um livro emprestado");
            }
            livro.setDisponivel(false);
            livro.setAlunoEmprestado(aluno);
            livroRepository.save(livro);
        } else {
            throw new RuntimeException("Livro indisponível para empréstimo");
        }
    }
    public void devolverLivro(Long idLivro) {
        Livro livro = acharLivroPorId(idLivro);
        Aluno aluno = livro.getAlunoEmprestado();
        if (!livro.getDisponivel()) {
            livro.setDisponivel(true);
            aluno.setLivroEmprestado(null);
            livro.setAlunoEmprestado(null);
            livroRepository.save(livro);
            alunoRepository.save(aluno);
        } else {
            throw new RuntimeException("Livro já está disponível na biblioteca");
        }
    }
}
