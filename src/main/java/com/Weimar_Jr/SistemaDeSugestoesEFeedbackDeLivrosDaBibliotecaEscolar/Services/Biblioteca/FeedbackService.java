package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Biblioteca;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Feedback;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.AtualizarFeedbackDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.CriarFeedbackDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.FeedbackDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.FeedbackMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.FeedbackException.NenhumFeedbackComEsseIdException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.FeedbackException.NenhumFeedbackDoLivroFaladoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.FeedbackRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.LivroRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario.AlunoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedbackService {
    final LivroService livroService;
    final LivroRepository livroRepository;
    final AlunoService alunoService;
    final FeedbackRepository feedbackRepository;
    final FeedbackMapper feedbackMapper;

    @Transactional
    public FeedbackDTOResponse criarFeedback(CriarFeedbackDTORequest feedbackDTO) {
        Feedback feedback = feedbackMapper.toFeedback(feedbackDTO);
        Livro livro = livroService.acharLivroPorId(feedbackDTO.idLivro());
        Aluno aluno = alunoService.getAlunoLogado();
        feedback.setLivro(livro);
        feedback.setAluno(aluno);
        feedbackRepository.save(feedback);
        atualizarMediaAvaliacaoDoLivro(feedbackDTO.idLivro());
        return feedbackMapper.toFeedbackDTOResponse(feedback);
    }

    @Transactional
    public void deletarFeedback(Long id) {
        Feedback feedback = acharFeedbackPorId(id);
        Long idLivro = feedback.getLivro().getId();
        feedbackRepository.deleteById(id);
        atualizarMediaAvaliacaoDoLivro(idLivro);
    }

    @Transactional
    public void atualizarFeedback(Long id, AtualizarFeedbackDTORequest  feedbackDTO) {
        Feedback feedback = feedbackRepository.findByIdWithAssociations(id)
                .orElseThrow(() -> new NenhumFeedbackComEsseIdException(id));
        Aluno aluno = alunoService.getAlunoLogado();
        if(aluno.getId() == feedback.getAluno().getId())
        {
            feedbackMapper.toFeedbackAtualizar(feedbackDTO, feedback);
            feedbackRepository.save(feedback);
            atualizarMediaAvaliacaoDoLivro(feedback.getLivro().getId());
        }
        else{
            throw new RuntimeException("Feedback mencionado não pertence ao aluno logado.");
        }
    }

    @Transactional
    public void atualizarFeedbackAdmin(Long id, AtualizarFeedbackDTORequest dto) {
        Feedback feedback = feedbackRepository.findByIdWithAssociations(id)
                .orElseThrow(() -> new NenhumFeedbackComEsseIdException(id));
        feedbackMapper.toFeedbackAtualizar(dto, feedback);
        feedbackRepository.save(feedback);
        atualizarMediaAvaliacaoDoLivro(feedback.getLivro().getId());
    }

    public Feedback acharFeedbackPorId(Long id) {

        return feedbackRepository.findById(id).orElseThrow(() -> new NenhumFeedbackComEsseIdException(id));
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

    public List<FeedbackDTOResponse> listarFeedbacksPorLivro(Long idLivro) {
        return feedbackRepository.findFeedbacksByLivroId(idLivro).stream().map(feedbackMapper::toFeedbackDTOResponse).toList();
    }
    public List<FeedbackDTOResponse> listarFeedbacksPorAluno(Long idAluno) {

        return feedbackRepository.findFeedbacksByAlunoId(idAluno).stream().map(feedbackMapper::toFeedbackDTOResponse).toList();

    }


    public void atualizarMediaAvaliacaoDoLivro(Long idLivro) {
        Double mediaAvaliacao = feedbackRepository.mediaAvaliacaoByLivroId(idLivro);
        Livro livro = livroService.acharLivroPorId(idLivro);
        livro.setMediaAvaliacao(mediaAvaliacao);
        livroRepository.save(livro);
    }

    public List<FeedbackDTOResponse> meusFeedbacksAluno()
    {
         Long IdAluno = alunoService.getAlunoLogado().getId();
                 return listarFeedbacksPorAluno(IdAluno);
    }

    public FeedbackDTOResponse acharFeedbackDTOPorId(Long id)
    {
        Feedback feedback = feedbackRepository.findByIdWithAssociations(id).orElseThrow(() -> new NenhumFeedbackComEsseIdException(id));
       return  feedbackMapper.toFeedbackDTOResponse(feedback);
    }
}
