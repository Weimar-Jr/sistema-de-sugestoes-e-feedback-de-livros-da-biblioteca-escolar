package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.ServiceTest;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Feedback;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.FeedbackDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AdicionarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AtualizarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.LivroDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.LivroMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NaoTemAlunoComEsseIdException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.AlunoJaPossuiUmLivroEmprestadoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.ExceptionsDeBusca.*;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.JaTemLivroSemelhanteCadastradoExeption;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.LivroIndisponivelException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.LivroJaConstaComoNaoEmprestadoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.AlunoRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.LivroRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Biblioteca.LivroService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {
    @Mock
    private LivroRepository livroRepository;
    @Mock
    private LivroMapper livroMapper;
    @Mock
    AlunoRepository alunoRepository;
    @InjectMocks
    private LivroService livroService;

    Livro livro = new Livro();
    Livro livro2 = new Livro();
    LivroDTOResponse livroDTOResponse;
    LivroDTOResponse livroDTOResponse2;
    AdicionarLivroDTORequest adicionarLivroDTORequest;
    AtualizarLivroDTORequest atualizarLivroDTORequest;
    Feedback feedback = new Feedback();
    Feedback feedback2 = new Feedback();
    FeedbackDTOResponse feedbackDTOResponse;
    FeedbackDTOResponse feedbackDTOResponse2;
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

        livro2.setId(2L);
        livro2.setTitulo("Teste");
        livro2.setAutor("Weimar2");
        livro2.setAnoPublicacao(2025);
        livro2.setEditora("Editora Teste2");
        livro2.setGenero("exatas");
        livro2.setDisponivel(false);
        livro2.setDescricao("objeto de teste");

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
        aluno.getFeedbacks().add(feedback);
        aluno.getFeedbacks().add(feedback2);
        feedback2.setAluno(aluno);
        livro2.setAlunoEmprestado(aluno);

        feedbackDTOResponse = new FeedbackDTOResponse(feedback.getId(), feedback.getComentario(), feedback.getAvaliacao(), feedback.getNomeDoAlunoVisivel(), aluno.getId(), aluno.getNome(), livro.getId(), livro.getTitulo());
        feedbackDTOResponse2 = new FeedbackDTOResponse(feedback2.getId(), feedback2.getComentario(), feedback2.getAvaliacao(), feedback2.getNomeDoAlunoVisivel(), aluno.getId(), aluno.getNome(), livro.getId(), livro.getTitulo());

        adicionarLivroDTORequest = new AdicionarLivroDTORequest("Teste", "Weimar"," Editora Teste",2026,"Ficção",true,"objeto de teste");
        atualizarLivroDTORequest = new AtualizarLivroDTORequest("novo titulo", "novo autor", "nova editora",2027,"nova descrição","novo genero",false);
        livroDTOResponse = new LivroDTOResponse(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getEditora(), livro.getAnoPublicacao(), livro.getGenero(), livro.getDisponivel(), livro.getDescricao(), livro.getMediaAvaliacao(), List.of(feedbackDTOResponse, feedbackDTOResponse2));
        livroDTOResponse2 = new LivroDTOResponse(livro2.getId(), livro2.getTitulo(), livro2.getAutor(), livro2.getEditora(), livro2.getAnoPublicacao(), livro2.getGenero(), livro2.getDisponivel(), livro2.getDescricao(), livro2.getMediaAvaliacao(), Collections.emptyList());
    }

    @Test
    void deveDarExceptionDeJaTemLivroSemelhanteCadastradoTest()
    {
        when(livroRepository.findByAutorTituloEEditora(adicionarLivroDTORequest.autor(), adicionarLivroDTORequest.titulo(), adicionarLivroDTORequest.editora())).thenReturn(Optional.of(livro));
        assertThrows( JaTemLivroSemelhanteCadastradoExeption.class, () -> livroService.cadastrarLivro(adicionarLivroDTORequest));
        verify(livroRepository, times(1)).findByAutorTituloEEditora(adicionarLivroDTORequest.autor(), adicionarLivroDTORequest.titulo(), adicionarLivroDTORequest.editora());
        verifyNoMoreInteractions(livroRepository);
        verifyNoInteractions(livroMapper);
    }

    @Test
    void deveCadastrarLivroTest()
    {
        when(livroRepository.findByAutorTituloEEditora(adicionarLivroDTORequest.autor(), adicionarLivroDTORequest.titulo(), adicionarLivroDTORequest.editora())).thenReturn(Optional.empty());
        when(livroMapper.toLivro(adicionarLivroDTORequest)).thenReturn(livro);
        when(livroMapper.toLivroDTOResponse(livro)).thenReturn(livroDTOResponse);
        when(livroRepository.save(livro)).thenAnswer(invocation -> invocation.getArgument(0));

        LivroDTOResponse livroDTORetonado = livroService.cadastrarLivro(adicionarLivroDTORequest);
        ArgumentCaptor<Livro> captor = ArgumentCaptor.forClass(Livro.class);
        verify(livroRepository, times(1)).save(captor.capture());
        Livro livroSalvo = captor.getValue();
        assertEquals(livroDTOResponse, livroDTORetonado);
        assertEquals(livro, livroSalvo);
        verify(livroRepository , times(1)).findByAutorTituloEEditora(adicionarLivroDTORequest.autor(), adicionarLivroDTORequest.titulo(), adicionarLivroDTORequest.editora());
        verify(livroMapper, times(1)).toLivro(adicionarLivroDTORequest);
        verify(livroMapper, times(1)).toLivroDTOResponse(livro);
        verifyNoMoreInteractions(livroRepository, livroMapper);

    }

    @Test
    void deveDarExceptionDeLivroNaoEncontradoPorIdTest()
    {
        when(livroRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(LivroNaoEncontradoException.class, () -> livroService.obterLivroPorId(1L));
        verify(livroRepository , times(1)).findById(1L);
        verifyNoMoreInteractions(livroRepository);

    }

    @Test
    void deveObterOLivroPorIdTest()
    {
        when(livroRepository.findById(livro.getId())).thenReturn(Optional.of(livro));
        when(livroMapper.toLivroDTOResponse(livro)).thenReturn(livroDTOResponse);
        LivroDTOResponse livroDTORetonado = livroService.obterLivroPorId(livro.getId());
        assertEquals(livroDTOResponse, livroDTORetonado);
        verify(livroRepository, times(1)).findById(livro.getId());
        verify(livroMapper, times(1)).toLivroDTOResponse(livro);
        verifyNoMoreInteractions(livroRepository, livroMapper);
    }

    @Test
    void deveDarExceptionDeLivroNaoAchadoNoServiceDeAtualizarTest()
    {
        when(livroRepository.findById(livro.getId())).thenReturn(Optional.empty());
        assertThrows(LivroNaoEncontradoException.class, () -> livroService.atualizarLivro(livro.getId(), atualizarLivroDTORequest));
        verify(livroRepository, times(1)).findById(livro.getId());
        verifyNoMoreInteractions(livroRepository);
        verifyNoInteractions(livroMapper);

    }

    @Test
    void deveAtualizarLivroTest()
    {
        when(livroRepository.findById(livro.getId())).thenReturn(Optional.of(livro));
        doAnswer(invocation -> {
            AtualizarLivroDTORequest livroDTO = invocation.getArgument(0);
            Livro livroRetornado = invocation.getArgument(1);
            livroRetornado.setTitulo(livroDTO.titulo());
            livroRetornado.setAutor(livroDTO.autor());
            livroRetornado.setDisponivel(livroDTO.disponivel());
            livroRetornado.setAnoPublicacao(livroDTO.anoPublicacao());
            livroRetornado.setEditora(livroDTO.editora());
            livroRetornado.setGenero(livroDTO.genero());
            livroRetornado.setDescricao(livroDTO.descricao());
            return null;
        }).when(livroMapper).toLivroAtualizar(atualizarLivroDTORequest, livro);

        when(livroRepository.save(livro)).thenAnswer(invocation -> invocation.getArgument(0));
        livroService.atualizarLivro(livro.getId(), atualizarLivroDTORequest);
        ArgumentCaptor<Livro> captor = ArgumentCaptor.forClass(Livro.class);
        verify(livroRepository, times(1)).save(captor.capture());
        Livro livroSalvo = captor.getValue();
        assertEquals(atualizarLivroDTORequest.titulo(), livroSalvo.getTitulo());
        assertEquals(atualizarLivroDTORequest.autor(), livroSalvo.getAutor());
        assertEquals(atualizarLivroDTORequest.editora(), livroSalvo.getEditora());
        assertEquals(atualizarLivroDTORequest.anoPublicacao(), livroSalvo.getAnoPublicacao());
        assertEquals(atualizarLivroDTORequest.descricao(), livroSalvo.getDescricao());
        assertEquals(atualizarLivroDTORequest.disponivel(), livroSalvo.getDisponivel());
        assertEquals(atualizarLivroDTORequest.genero(), livroSalvo.getGenero());

        verify(livroMapper, times(1)).toLivroAtualizar(atualizarLivroDTORequest, livro);
        verifyNoMoreInteractions(livroRepository, livroMapper);


    }

    @Test
    void deveDarExceptionDeLivroNaoAchadoEmDeletarLivro() {
        when(livroRepository.findById(livro.getId())).thenReturn(Optional.empty());
        assertThrows(LivroNaoEncontradoException.class, () -> livroService.deletarLivro(livro.getId()));
        verify(livroRepository, times(1)).findById(livro.getId());
        verifyNoMoreInteractions(livroRepository);
    }

    @Test
    void deveDeletarLivroTest()
    {
        when(livroRepository.findById(livro.getId())).thenReturn(Optional.of(livro));

        livroService.deletarLivro(livro.getId());
        verify(livroRepository, times(1)).findById(livro.getId());
        verify(livroRepository, times(1)).deleteById(livro.getId());
        verifyNoMoreInteractions(livroRepository);
    }

    @Test
    void deveDarExceptionDeLivroNaoEncontradoNoEmprestarLivroTest()
    {
        when(livroRepository.findById(livro.getId())).thenReturn(Optional.empty());
        assertThrows(LivroNaoEncontradoException.class, () -> livroService.emprestarLivro(livro.getId(), aluno.getId()));
        verify(livroRepository, times(1)).findById(livro.getId());
        verifyNoMoreInteractions(livroRepository);

    }

    @Test
    void deveDarExceptionDeNaoTemAlunoComEsseIdEmprestarLivroTest()
    {
        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.empty());
        when(livroRepository.findById(livro.getId())).thenReturn(Optional.of(livro));
        assertThrows(NaoTemAlunoComEsseIdException.class, () -> livroService.emprestarLivro(livro.getId(), aluno.getId()));
        verify(livroRepository, times(1)).findById(livro.getId());
        verify(alunoRepository, times(1)).findById(aluno.getId());
        verifyNoMoreInteractions(livroRepository, alunoRepository );
    }

    @Test
    void deveDarExceptionDeLivroNaoDisponivelEmprestarLivroTest()
    {
        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));
        when(livroRepository.findById(livro2.getId())).thenReturn(Optional.of(livro2));
        assertThrows(LivroIndisponivelException.class, () -> livroService.emprestarLivro(livro2.getId(), aluno.getId()));
        verify(livroRepository, times(1)).findById(livro2.getId());
        verify(alunoRepository, times(1)).findById(aluno.getId());
        verifyNoMoreInteractions(livroRepository, alunoRepository );
    }

    @Test
    void deveDarExceptionDeAlunoJaTemLivroEmprestadoTest()
    {
        aluno.setLivroEmprestado(livro2);
        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));
        when(livroRepository.findById(livro.getId())).thenReturn(Optional.of(livro));

        assertThrows(AlunoJaPossuiUmLivroEmprestadoException.class, () -> livroService.emprestarLivro(livro.getId(), aluno.getId()));
        verify(alunoRepository, times(1)).findById(aluno.getId());
        verify(livroRepository, times(1)).findById(livro.getId());
        verifyNoMoreInteractions(alunoRepository, livroRepository);
    }

    @Test
    void deveEmprestarLivroTest()
    {
        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));
        when(livroRepository.findById(livro.getId())).thenReturn(Optional.of(livro));
        when(alunoRepository.save(aluno)).thenAnswer(invocation -> invocation.getArgument(0));
        when(livroRepository.save(livro)).thenAnswer(invocation ->  invocation.getArgument(0));

        livroService.emprestarLivro(livro.getId(), aluno.getId());
        ArgumentCaptor<Aluno> alunoCaptor = ArgumentCaptor.forClass(Aluno.class);
        ArgumentCaptor<Livro> livroCaptor = ArgumentCaptor.forClass(Livro.class);
        verify(alunoRepository, times(1)).save(alunoCaptor.capture());
        verify(livroRepository, times(1)).save(livroCaptor.capture());
        Livro livroCapturado = livroCaptor.getValue();
        Aluno alunoCapturado = alunoCaptor.getValue();

        assertEquals(alunoCapturado.getLivroEmprestado(), livro);
        assertEquals(livroCapturado.getAlunoEmprestado(), aluno);
        verifyNoMoreInteractions(alunoRepository, livroRepository);

    }

    @Test
    void deveDarExceptionDeLivroNaoAchadoNoDevolverLivroTest()
    {
        when(livroRepository.findById(livro.getId())).thenReturn(Optional.empty());
        assertThrows(LivroNaoEncontradoException.class, () -> livroService.devolverLivro(livro.getId()));
        verify(livroRepository, times(1)).findById(livro.getId());
        verifyNoMoreInteractions(alunoRepository);

    }

    @Test
    void deveDarExceptionDeLivroJaConstaComoDisponivelNoDevolverLivroTest()
    {
        when(livroRepository.findById(livro.getId())).thenReturn(Optional.of(livro));
        assertThrows(LivroJaConstaComoNaoEmprestadoException.class, () -> livroService.devolverLivro(livro.getId()));
        verify(livroRepository, times(1)).findById(livro.getId());
        verifyNoMoreInteractions(livroRepository);
    }

    @Test
    void deveDevolverLivroTest()
    {
        aluno.setLivroEmprestado(livro2);
        when(livroRepository.findById(livro2.getId())).thenReturn(Optional.of(livro2));
        when(livroRepository.save(livro2)).thenAnswer(invocation -> invocation.getArgument(0));
        when(alunoRepository.save(aluno)).thenAnswer(invocation -> invocation.getArgument(0));

        livroService.devolverLivro(livro2.getId());
        ArgumentCaptor<Livro> livroCaptor = ArgumentCaptor.forClass(Livro.class);
        ArgumentCaptor<Aluno> alunoCaptor = ArgumentCaptor.forClass(Aluno.class);
        verify(alunoRepository, times(1)).save(alunoCaptor.capture());
        verify(livroRepository, times(1)).save(livroCaptor.capture());
        assertNull(livroCaptor.getValue().getAlunoEmprestado());
        assertNull(alunoCaptor.getValue().getLivroEmprestado());
        verify(livroRepository, times(1)).findById(livro2.getId());
        verifyNoMoreInteractions(alunoRepository, livroRepository);


    }

    @Test
    void deveDarExceptionDeNenhumLivroCadastradoNoListarLivrosTest()
    {
        when(livroRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NenhumLivroCadastradoException.class, () -> livroService.listarLivros());
        verify(livroRepository, times(1)).findAll();
        verifyNoMoreInteractions(livroRepository);
    }

    @Test
    void deveListarLivrosTest()
    {
        when(livroRepository.findAll()).thenReturn(List.of(livro,livro2));
        when(livroMapper.toLivroDTOResponse(livro)).thenReturn(livroDTOResponse);
        when(livroMapper.toLivroDTOResponse(livro2)).thenReturn(livroDTOResponse2);
        List<LivroDTOResponse> livros = livroService.listarLivros();
        assertEquals(2, livros.size());
        assertEquals(livroDTOResponse, livros.get(0));
        assertEquals(livroDTOResponse2, livros.get(1));
        verify(livroRepository, times(1)).findAll();
        verify(livroMapper, times(2)).toLivroDTOResponse(any(Livro.class));
        verifyNoMoreInteractions(livroRepository, livroMapper);

    }

    @Test
    void deveDarExceptionDeNenhumLivroDisponivelNoListarLivrosDisponiveisTest()
    {
        when(livroRepository.findByDisponivel(true)).thenReturn(Collections.emptyList());
        assertThrows(NenhumLivroDisponivelException.class, () -> livroService.listarLivrosDisponiveis());
        verify(livroRepository, times(1)).findByDisponivel(true);
        verifyNoMoreInteractions(livroRepository);
    }

    @Test
    void deveListarLivrosDisponiveisTest()
    {
        when(livroRepository.findByDisponivel(true)).thenReturn(List.of(livro));
        when(livroMapper.toLivroDTOResponse(livro)).thenReturn(livroDTOResponse);
        List<LivroDTOResponse> livros = livroService.listarLivrosDisponiveis();
        assertEquals(1, livros.size());
        assertEquals(livroDTOResponse, livros.getFirst());
        verify(livroRepository, times(1)).findByDisponivel(true);
        verify(livroMapper, times(1)).toLivroDTOResponse(livro);
        verifyNoMoreInteractions(livroRepository, livroMapper);

    }


    @Test
    void deveDarExceptionDeNenhumLivroIndisponivelTest()
    {
        when(livroRepository.findByDisponivel(false)).thenReturn(Collections.emptyList());
        assertThrows(NenhumLivroIndisponivelException.class, () -> livroService.listarLivrosIndisponiveis());
        verify(livroRepository, times(1)).findByDisponivel(false);
        verifyNoMoreInteractions(livroRepository);
    }

    @Test
    void deveListarLivrosIndisponiveisTest()
    {
        when(livroRepository.findByDisponivel(false)).thenReturn(List.of(livro2));
        when(livroMapper.toLivroDTOResponse(livro2)).thenReturn(livroDTOResponse2);
        List<LivroDTOResponse> livros = livroService.listarLivrosIndisponiveis();
        assertEquals(1, livros.size());
        assertEquals(livroDTOResponse2, livros.getFirst());
        verify(livroRepository, times(1)).findByDisponivel(false);
        verify(livroMapper, times(1)).toLivroDTOResponse(livro2);
        verifyNoMoreInteractions(livroRepository);
    }

    @Test
    void deveDarExceptionNenhumLivroAchadoPeloTituloTest()
    {
        when(livroRepository.findByTitulo("titulo")).thenReturn(Collections.emptyList());
        assertThrows(NenhumLivroAchadoPelotituloFaladoException.class, () -> livroService.listarLivrosPorTitulo("titulo"));
        verify(livroRepository, times(1)).findByTitulo("titulo");
        verifyNoMoreInteractions(livroRepository);
    }

    @Test
    void deveListarLivrosPorTituloTest()
    {
       when(livroRepository.findByTitulo("teste")).thenReturn(List.of(livro, livro2));
       when(livroMapper.toLivroDTOResponse(livro)).thenReturn(livroDTOResponse);
       when(livroMapper.toLivroDTOResponse(livro2)).thenReturn(livroDTOResponse2);

       List<LivroDTOResponse> livros = livroService.listarLivrosPorTitulo("teste");
       assertEquals(2, livros.size());
       assertEquals(livroDTOResponse, livros.getFirst());
       assertEquals(livroDTOResponse2, livros.get(1));
       verify(livroRepository, times(1)).findByTitulo("teste");
       verify(livroMapper, times(2)).toLivroDTOResponse(any(Livro.class));
       verifyNoMoreInteractions(livroRepository, livroMapper);
    }

    @Test
    void deveDarExceptionQuandoListarLivrosPorAutorTest()
    {
        when(livroRepository.findByAutor("weimar")).thenReturn(Collections.emptyList());
        assertThrows(NenhumLivroDesseAutorException.class, () -> livroService.listarLivrosPorAutor("weimar"));
        verify(livroRepository, times(1)).findByAutor("weimar");
        verifyNoMoreInteractions(livroRepository);
    }

    @Test
    void deveListarLivrosDoAutorTest()
    {
        when(livroRepository.findByAutor("weimar")).thenReturn(List.of(livro));
        when(livroMapper.toLivroDTOResponse(livro)).thenReturn(livroDTOResponse);
        List<LivroDTOResponse> livros = livroService.listarLivrosPorAutor("weimar");
        assertEquals(1, livros.size());
        assertEquals(livroDTOResponse, livros.getFirst());
        verify(livroRepository, times(1)).findByAutor("weimar");
        verify(livroMapper, times(1)).toLivroDTOResponse(livro);
        verifyNoMoreInteractions(livroRepository, livroMapper);

    }

    @Test
    void deveDarExceptionQuandoListarPorGeneroTest()
    {
        when(livroRepository.findByGenero("test")).thenReturn(Collections.emptyList());
        assertThrows(NenhumLivroDesseGeneroException.class, () -> livroService.listarLivrosPorGenero("test"));
        verify(livroRepository, times(1)).findByGenero("test");
        verifyNoMoreInteractions(livroRepository);
    }

    @Test
    void deveListarLivrosPeloGeneroTest()
    {
        when(livroRepository.findByGenero("exatas")).thenReturn(List.of(livro2));
        when(livroMapper.toLivroDTOResponse(livro2)).thenReturn(livroDTOResponse2);
        List<LivroDTOResponse> livros = livroService.listarLivrosPorGenero("exatas");

        assertEquals(1, livros.size());
        assertEquals(livroDTOResponse2, livros.getFirst());
        verify(livroRepository, times(1)).findByGenero("exatas");
        verify(livroMapper, times(1)).toLivroDTOResponse(livro2);
        verifyNoMoreInteractions(livroRepository, livroMapper);
    }

}
