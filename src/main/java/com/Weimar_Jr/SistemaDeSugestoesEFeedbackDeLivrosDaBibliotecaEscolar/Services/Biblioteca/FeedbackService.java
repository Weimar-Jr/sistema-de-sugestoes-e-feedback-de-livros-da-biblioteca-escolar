package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Biblioteca;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Feedback;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.AtualizarFeedbackDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.CriarFeedbackDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.FeedbackMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.AlunoRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.FeedbackRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.LivroRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedbackService {
    final LivroService livroService;
    final LivroRepository livroRepository;
    final AlunoService alunoService;
    final AlunoRepository alunoRepository;
    final FeedbackRepository feedbackRepository;
    final FeedbackMapper feedbackMapper;

    public void criarFeedback(CriarFeedbackDTORequest feedbackDTO) {
        Feedback feedback = feedbackMapper.toFeedback(feedbackDTO);
        Livro livro = livroService.acharLivroPorId(feedbackDTO.idLivro());
        Aluno aluno = alunoService.acharAlunoPeloId(feedbackDTO.idAluno());
        feedback.setLivro(livro);
        feedback.setAluno(aluno);
        aluno.adiconarFeedback(feedback);
        livro.adicionarFeedback(feedback);
        livroRepository.save(livro);
        alunoRepository.save(aluno);
        feedbackRepository.save(feedback);
        atualizarMediaAvaliacaoDoLivro(feedbackDTO.idLivro());
    }

    public void deletarFeedback(Long id) {
        Feedback feedback = acharFeedbackPorId(id);
        Long idLivro = feedback.getLivro().getId();
        feedbackRepository.deleteById(id);
        atualizarMediaAvaliacaoDoLivro(idLivro);
    }

    public void atualizarFeedback(Long id, AtualizarFeedbackDTORequest  feedbackDTO) {
        Feedback feedback = acharFeedbackPorId(id);
        feedbackMapper.toFeedbackAtualizar(feedbackDTO, feedback);
        feedbackRepository.save(feedback);
        atualizarMediaAvaliacaoDoLivro(feedback.getLivro().getId());
    }

    public Feedback acharFeedbackPorId(Long id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback não encontrado"));
    }

    public void ocultarNomeNoFeedback(Long id) {
        Feedback feedback = acharFeedbackPorId(id);
        feedback.setNomeDoAlunoVisivel(false);
        feedbackRepository.save(feedback);
    }
    public void mostrarNomeNoFeedback(Long id) {
        Feedback feedback = acharFeedbackPorId(id);
        feedback.setNomeDoAlunoVisivel(true);
        feedbackRepository.save(feedback);
    }

    public List<Feedback> listarFeedbacksPorLivro(Long idLivro) {
        return feedbackRepository.findFeedbacksByLivroIdAndVisivelTrue(idLivro);
    }
    public List<Feedback> listarFeedbacksPorAluno(Long idAluno) {
        return feedbackRepository.findFeedbacksByAlunoIdAndVisivelTrue(idAluno);
    }

    void atualizarMediaAvaliacaoDoLivro(Long idLivro) {
        Double mediaAvaliacao = feedbackRepository.mediaAvaliacaoByLivroId(idLivro);
        Livro livro = livroService.acharLivroPorId(idLivro);
        livro.setMediaAvaliacao(mediaAvaliacao);
        livroRepository.save(livro);
    }
}
