package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.ServiceTest;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Feedback;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.AtualizarFeedbackDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.CriarFeedbackDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.FeedbackDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.LivroDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.FeedbackMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NaoTemAlunoComEsseIdException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.FeedbackException.NenhumFeedbackComEsseIdException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.FeedbackException.NenhumFeedbackDesseAlunoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.FeedbackException.NenhumFeedbackDoLivroFaladoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.ExceptionsDeBusca.LivroNaoEncontradoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.AlunoRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.FeedbackRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.LivroRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Biblioteca.FeedbackService;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Biblioteca.LivroService;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {
    
    @Mock
    LivroService livroService;
    @Mock
    LivroRepository livroRepository;
    @Mock
    AlunoService alunoService;
    @Mock
    AlunoRepository AlunoRepository;
    @Mock
    FeedbackRepository feedbackRepository;
    @Mock
    FeedbackMapper feedbackMapper;
    @InjectMocks
    FeedbackService feedbackService;

    Livro livro = new Livro();
    LivroDTOResponse livroDTOResponse;
    Feedback feedback = new Feedback();
    Feedback feedback2 = new Feedback();
    FeedbackDTOResponse feedbackDTOResponse;
    FeedbackDTOResponse feedbackDTOResponse2;
    CriarFeedbackDTORequest criarFeedbackDTORequest;
    AtualizarFeedbackDTORequest atualizarFeedbackDTORequest;
    Aluno aluno = new Aluno();

    @BeforeEach
    void setUp() {
        livro.setId(1L);
        livro.setTitulo("Teste");
        livro.setAutor("Weimar");
        livro.setAnoPublicacao(2026);
        livro.setEditora("Editora Teste");
        livro.setGenero("Ficção");
        livro.setDisponivel(true);
        livro.setDescricao("objeto de teste");

        feedback.setId(1L);
        feedback.setNomeDoAlunoVisivel(true);
        feedback.setAvaliacao(5.0);
        feedback.setComentario("Legal");
        feedback.setLivro(livro);

        feedback2.setId(2L);
        feedback2.setNomeDoAlunoVisivel(false);
        feedback2.setAvaliacao(5.0);
        feedback2.setComentario("Bom");
        feedback2.setLivro(livro);


        aluno.setEmail("weimar@example.com");
        aluno.setId(1L);
        aluno.setNome("Weimar");
        aluno.setRegistroDeAluno("123456789");

        livro.adicionarFeedback(feedback);
        livro.adicionarFeedback(feedback2);
        feedback.setAluno(aluno);
        feedback.setLivro(livro);
        aluno.getFeedbacks().add(feedback);
        aluno.getFeedbacks().add(feedback2);
        feedback2.setAluno(aluno);
        feedback2.setLivro(livro);

        feedbackDTOResponse = new FeedbackDTOResponse(feedback.getId(), feedback.getComentario(), feedback.getAvaliacao(), feedback.getNomeDoAlunoVisivel(), aluno.getId(), aluno.getNome(), livro.getId(), livro.getTitulo());
        feedbackDTOResponse2 = new FeedbackDTOResponse(feedback2.getId(), feedback2.getComentario(), feedback2.getAvaliacao(), feedback2.getNomeDoAlunoVisivel(), aluno.getId(), aluno.getNome(), livro.getId(), livro.getTitulo());
        livroDTOResponse = new LivroDTOResponse(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getEditora(), livro.getAnoPublicacao(), livro.getGenero(), livro.getDisponivel(), livro.getDescricao(), livro.getMediaAvaliacao(), List.of(feedbackDTOResponse, feedbackDTOResponse2));
        criarFeedbackDTORequest = new CriarFeedbackDTORequest(feedbackDTOResponse.comentario(), feedbackDTOResponse.avaliacao(), feedbackDTOResponse.nomeDoAlunoVisivel(), livro.getId(), aluno.getId());
        atualizarFeedbackDTORequest = new AtualizarFeedbackDTORequest(" bom", 4.5, false);
    }

    @Test
    void deveDarExceptionEmCriarFeedbackAlunoNaoAchadoTest()
    {
        when(livroService.acharLivroPorId(livro.getId())).thenReturn(livro);
        when(alunoService.acharAlunoPeloId(aluno.getId())).thenThrow(new NaoTemAlunoComEsseIdException(aluno.getId()));
        assertThrows(NaoTemAlunoComEsseIdException.class, () -> feedbackService.criarFeedback(criarFeedbackDTORequest));
        verifyNoInteractions(feedbackRepository);
        verify(livroService).acharLivroPorId(criarFeedbackDTORequest.idLivro());
        verify(alunoService).acharAlunoPeloId(criarFeedbackDTORequest.idAluno());
    }

    @Test
    void deveDarExceptionEmCriarFeedbackLivroNaoAchadoTest()
    {
        when(livroService.acharLivroPorId(livro.getId())).thenThrow(new LivroNaoEncontradoException(livro.getId()));
        assertThrows(LivroNaoEncontradoException.class, () -> feedbackService.criarFeedback(criarFeedbackDTORequest));
        verifyNoInteractions(feedbackRepository);
    }

    @Test
    void deveCriarFeedbackTest()
    {
        when(alunoService.acharAlunoPeloId(aluno.getId())).thenReturn(aluno);
        when(livroService.acharLivroPorId(livro.getId())).thenReturn(livro);
        when(feedbackRepository.save(any(Feedback.class))).thenAnswer(invocation ->  invocation.getArgument(0));
        when(feedbackMapper.toFeedback(criarFeedbackDTORequest)).thenReturn(feedback);
        when(feedbackMapper.toFeedbackDTOResponse(feedback)).thenReturn(feedbackDTOResponse);

        FeedbackDTOResponse feedbackDTOResponseRetornado = feedbackService.criarFeedback(criarFeedbackDTORequest);
        ArgumentCaptor<Feedback> captor = ArgumentCaptor.forClass(Feedback.class);
        verify(feedbackRepository).save(captor.capture());
        Feedback feedbackSalvo = captor.getValue();

        assertEquals(feedbackDTOResponse, feedbackDTOResponseRetornado);
        assertEquals(feedback, feedbackSalvo);
        verify(alunoService, times(1)).acharAlunoPeloId(aluno.getId());
        verify(livroService, times(2)).acharLivroPorId(livro.getId());
        verify(feedbackMapper, times(1)).toFeedbackDTOResponse(feedback);
        verify(feedbackMapper, times(1)).toFeedback(criarFeedbackDTORequest);
    }

    @Test
    void deveDarExceptionEmAtualizarFeedbackQuandoNaoTemFeedbackComOIdPassadoTest() {
        when(feedbackRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NenhumFeedbackComEsseIdException.class, () -> feedbackService.atualizarFeedback(feedback.getId(), atualizarFeedbackDTORequest));
        verify(feedbackRepository).findById(anyLong());
        verifyNoMoreInteractions(feedbackRepository);
        verifyNoInteractions(feedbackMapper);
    }

    @Test
    void deveAtualizarFeedbackTest() {
        when(feedbackRepository.findById(feedback.getId())).thenReturn(Optional.of(feedback));
        when(feedbackRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        doAnswer(invocation ->  {
                AtualizarFeedbackDTORequest dto = invocation.getArgument(0);
                Feedback feedbackAtualizado = invocation.getArgument(1);
                feedbackAtualizado.setComentario(dto.comentario());;
                feedbackAtualizado.setAvaliacao(dto.avaliacao());
                feedbackAtualizado.setNomeDoAlunoVisivel(dto.nomeDoAlunoVisivel());
                return null;

        }).when(feedbackMapper).toFeedbackAtualizar(atualizarFeedbackDTORequest, feedback);
        when(feedbackRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(livroService.acharLivroPorId(anyLong())).thenReturn(livro);



        feedbackService.atualizarFeedback(feedback.getId(), atualizarFeedbackDTORequest);
        ArgumentCaptor<Feedback> captor = ArgumentCaptor.forClass(Feedback.class);
        verify(feedbackRepository).save(captor.capture());
        Feedback feedbackSalvo = captor.getValue();
        assertEquals(atualizarFeedbackDTORequest.comentario(), feedbackSalvo.getComentario());
        assertEquals(atualizarFeedbackDTORequest.avaliacao(), feedbackSalvo.getAvaliacao());
        assertEquals(atualizarFeedbackDTORequest.nomeDoAlunoVisivel(), feedbackSalvo.getNomeDoAlunoVisivel());

        verify(feedbackMapper).toFeedbackAtualizar(atualizarFeedbackDTORequest, feedback);
        verify(feedbackRepository, times(1)).findById(anyLong());
        verify(feedbackRepository, times(1)).mediaAvaliacaoByLivroId(livro.getId());
        verify(livroRepository, times(1)).save(livro);
        verifyNoMoreInteractions(feedbackMapper, feedbackRepository, livroRepository);

    }


    @Test
    void deveDarExceptionEmDeletarFeedbackQuandoNaoAcharPeloIdTest()
    {
        when(feedbackRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NenhumFeedbackComEsseIdException.class, () -> feedbackService.deletarFeedback(1L));
        verify(feedbackRepository).findById(anyLong());
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void deveDeletarFeedBackTest()
    {
        when(feedbackRepository.findById(feedback.getId())).thenReturn(Optional.of(feedback));
        when(livroService.acharLivroPorId(livro.getId())).thenReturn(livro);
        feedbackService.deletarFeedback(feedback.getId());
        verify(feedbackRepository, times(1)).findById(feedback.getId());
        verify(feedbackRepository, times(1)).deleteById(feedback.getId());
        verify(feedbackRepository, times(1)).mediaAvaliacaoByLivroId(livro.getId());
        verifyNoMoreInteractions(feedbackRepository);

    }

    @Test
    void deveDarExceptionAcharFeedbackPorIdTest()
    {
        when(feedbackRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NenhumFeedbackComEsseIdException.class, () -> feedbackService.acharFeedbackPorId(feedback.getId()));
        verify(feedbackRepository, times(1)).findById(feedback.getId());
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void deveAcharFeedbackPorIdTest()
    {
        when(feedbackRepository.findById(feedback.getId())).thenReturn(Optional.of(feedback));
        Feedback feedbackRetornado = feedbackService.acharFeedbackPorId(feedback.getId());
        assertEquals(feedback, feedbackRetornado);
        verify(feedbackRepository, times(1)).findById(feedback.getId());
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void deveOcultarNomeDoAlunoNoFeedbackTest()
    {
        when(feedbackRepository.findById(feedback.getId())).thenReturn(Optional.of(feedback));
        when(feedbackRepository.save(any(Feedback.class))).thenAnswer(invocation -> invocation.getArgument(0));

        feedbackService.ocultarNomeNoFeedback(feedback.getId());
        ArgumentCaptor<Feedback> captor = ArgumentCaptor.forClass(Feedback.class);
        verify(feedbackRepository, times(1)).save(captor.capture());
        Feedback feedbackSalvo = captor.getValue();
        assertEquals(false, feedbackSalvo.getNomeDoAlunoVisivel());
        verify(feedbackRepository, times(1)).findById(feedback.getId());
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void deveMostrarNomeDoAlunoNoFeedbackTest()
    {
        when(feedbackRepository.findById(feedback2.getId())).thenReturn(Optional.of(feedback2));
        when(feedbackRepository.save(any(Feedback.class))).thenAnswer(invocation -> invocation.getArgument(0));

        feedbackService.mostrarNomeNoFeedback(feedback2.getId());
        ArgumentCaptor<Feedback> captor = ArgumentCaptor.forClass(Feedback.class);
        verify(feedbackRepository, times(1)).save(captor.capture());
        Feedback feedbackSalvo = captor.getValue();
        assertEquals(true, feedbackSalvo.getNomeDoAlunoVisivel());
        verify(feedbackRepository, times(1)).findById(feedback2.getId());
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void deveDarExceptionListarAlunosPorLivroTest()
    {
        when(feedbackRepository.findFeedbacksByLivroId(anyLong())).thenReturn(Collections.emptyList());
        assertThrows(NenhumFeedbackDoLivroFaladoException.class, () -> feedbackService.listarFeedbacksPorLivro(livro.getId()));
        verify(feedbackRepository, times(1)).findFeedbacksByLivroId(livro.getId());
        verifyNoMoreInteractions(feedbackRepository);
    }

    @Test
    void deveListarFeedbacksDoLivroEspecificadoTest()
    {
        when(feedbackRepository.findFeedbacksByLivroId(livro.getId())).thenReturn(List.of(feedback, feedback2));
        when(feedbackMapper.toFeedbackDTOResponse(feedback)).thenReturn(feedbackDTOResponse);
        when(feedbackMapper.toFeedbackDTOResponse(feedback2)).thenReturn(feedbackDTOResponse2);

        List<FeedbackDTOResponse> feedbacks = feedbackService.listarFeedbacksPorLivro(livro.getId());
        assertEquals(2, feedbacks.size());
        assertEquals(feedbackDTOResponse, feedbacks.get(0));
        assertEquals(feedbackDTOResponse2, feedbacks.get(1));
        verify(feedbackRepository, times(1)).findFeedbacksByLivroId(livro.getId());
        verify(feedbackMapper, times(2)).toFeedbackDTOResponse(any(Feedback.class));
        verifyNoMoreInteractions(feedbackRepository, feedbackMapper);
    }

    @Test
    void deveDarExceptionQuandoListarFeedbacksPorAlunoEspecificadoTest()
    {
        when(feedbackRepository.findFeedbacksByAlunoId(aluno.getId())).thenReturn(Collections.emptyList());
        assertThrows(NenhumFeedbackDesseAlunoException.class, () -> feedbackService.listarFeedbacksPorAluno(aluno.getId()));
        verify(feedbackRepository, times(1)).findFeedbacksByAlunoId(aluno.getId());
        verifyNoInteractions(feedbackMapper);
    }

    @Test
    void deveListarTodosOsFeedbacksDoAlunoTest()
    {
        when(feedbackRepository.findFeedbacksByAlunoId(aluno.getId())).thenReturn(List.of(feedback, feedback2));
        when(feedbackMapper.toFeedbackDTOResponse(feedback)).thenReturn(feedbackDTOResponse);
        when(feedbackMapper.toFeedbackDTOResponse(feedback2)).thenReturn(feedbackDTOResponse2);

        List<FeedbackDTOResponse> feedbacks = feedbackService.listarFeedbacksPorAluno(aluno.getId());
        assertEquals(2, feedbacks.size());
        assertEquals(feedbackDTOResponse, feedbacks.get(0));
        assertEquals(feedbackDTOResponse2, feedbacks.get(1));
        verify(feedbackRepository, times(1)).findFeedbacksByAlunoId(aluno.getId());
        verify(feedbackMapper, times(2)).toFeedbackDTOResponse(any(Feedback.class));
        verifyNoMoreInteractions(feedbackRepository, feedbackMapper);
    }

    @Test
    void deveAtualizarAMediaDeAvaliacaoLivroTest()
    {
        when(feedbackRepository.mediaAvaliacaoByLivroId(livro.getId())).thenReturn(4.5);
        when(livroService.acharLivroPorId(livro.getId())).thenReturn(livro);
        when(livroRepository.save(any(Livro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        feedbackService.atualizarMediaAvaliacaoDoLivro(livro.getId());
        ArgumentCaptor<Livro> captor = ArgumentCaptor.forClass(Livro.class);
        verify(livroRepository, times(1)).save(captor.capture());
        Livro livroComMediaAtualizada = captor.getValue();
        assertEquals(4.5, livroComMediaAtualizada.getMediaAvaliacao());
        verify(livroService, times(1)).acharLivroPorId(livro.getId());
        verifyNoMoreInteractions(feedbackRepository, livroRepository);
    }


}
