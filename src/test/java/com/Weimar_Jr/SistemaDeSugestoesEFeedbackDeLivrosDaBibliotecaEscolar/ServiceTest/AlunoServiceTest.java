package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.ServiceTest;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.AlunoMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AlunoDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AtualizarAlunoDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.CriarUsuarioAlunoDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.ExceptionDeNegocio.JaTemAlunoComEsseEmailException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.ExceptionDeNegocio.JaTemAlunoComEsseRegistroException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NaoTemAlunoComEsseIdException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NenhumAlunoCadastradoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NenhumAlunoComEsseEmailException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NenhumAlunoComEsseRegistroDeAlunoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.AlunoRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;
    @InjectMocks
    private AlunoService alunoService;
    @Mock
    private AlunoMapper alunoMapper;

    AlunoDTOResponse alunoDTOResponse;
    AlunoDTOResponse alunoDTOResponseAtualizado;
    CriarUsuarioAlunoDTORequest criarUsuarioAlunoDTORequest;
    AtualizarAlunoDTORequest atualizarAlunoDTORequest;
    Aluno aluno;

    @BeforeEach
     void setUp() {
        alunoDTOResponse = new AlunoDTOResponse(1L, "Weimar", "weimar@example.com", "123456789", null);
        alunoDTOResponseAtualizado = new AlunoDTOResponse(1L,"novo nome", "weimar2@example.com", "987654321", null);
        criarUsuarioAlunoDTORequest = new CriarUsuarioAlunoDTORequest("Weimar", "weimar@example.com", "123456789", "123456789");
        atualizarAlunoDTORequest = new AtualizarAlunoDTORequest( "novo nome", "weimar2@example.com", "987654321");
        aluno  = new Aluno();
        aluno.setEmail("weimar@example.com");
        aluno.setId(1L);
        aluno.setNome("Weimar");
        aluno.setRegistroDeAluno("123456789");
    }

    @Test
    void deveDarJaTemAlunoComEsseEmailExceptionTest()
    {
        when(alunoRepository.findByEmail("weimar@example.com")).thenReturn(Optional.of(aluno));
        when(alunoMapper.toAluno(criarUsuarioAlunoDTORequest)).thenReturn(aluno);

        assertThrows(JaTemAlunoComEsseEmailException.class, () -> alunoService.cadastrarAluno(criarUsuarioAlunoDTORequest));

        verify(alunoRepository, times(1)).findByEmail("weimar@example.com");
        verify(alunoMapper, times(1)).toAluno(criarUsuarioAlunoDTORequest);
        verifyNoMoreInteractions(alunoRepository);
        verifyNoMoreInteractions(alunoMapper);
        verify(alunoRepository, times(0)).save(aluno);
    }

    @Test
    void deveDarJaTemAlunoComEsseRegistroExceptionTest()
    {
        when(alunoRepository.findByRegistroDeAluno(aluno.getRegistroDeAluno()) ).thenReturn(Optional.of(aluno));
        when(alunoRepository.findByEmail(aluno.getEmail())).thenReturn(Optional.empty());
        when(alunoMapper.toAluno(criarUsuarioAlunoDTORequest)).thenReturn(aluno);

        assertThrows(JaTemAlunoComEsseRegistroException.class, () -> alunoService.cadastrarAluno(criarUsuarioAlunoDTORequest));

        verify(alunoRepository, times(1)).findByRegistroDeAluno(aluno.getRegistroDeAluno());
        verify(alunoMapper, times(1)).toAluno(criarUsuarioAlunoDTORequest);
        verify(alunoRepository, times(1)).findByEmail(aluno.getEmail());
        verifyNoMoreInteractions(alunoMapper);
        verifyNoMoreInteractions(alunoRepository);
    }

    @Test
    void deveCriarUsuarioAlunoTest()
    {
        when(alunoRepository.findByEmail(criarUsuarioAlunoDTORequest.email())) .thenReturn(Optional.empty());
        when(alunoRepository.findByRegistroDeAluno(criarUsuarioAlunoDTORequest.registroDeAluno())).thenReturn(Optional.empty());
        when(alunoRepository.save(any(Aluno.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(alunoMapper.toAluno(criarUsuarioAlunoDTORequest)).thenReturn(aluno);
        when(alunoMapper.toAlunoDTOResponse(aluno)).thenReturn(alunoDTOResponse);

        AlunoDTOResponse alunoRetornado = alunoService.cadastrarAluno(criarUsuarioAlunoDTORequest);
        ArgumentCaptor<Aluno> captor = ArgumentCaptor.forClass(Aluno.class);
        verify(alunoRepository, times(1)).save(captor.capture());
        Aluno alunoSalvo = captor.getValue();
        assertEquals(aluno, alunoSalvo);
        assertEquals(alunoDTOResponse, alunoRetornado);

        verify(alunoRepository, times(1)).save(aluno);
        verify(alunoRepository, times(1)).findByEmail(criarUsuarioAlunoDTORequest.email());
        verify(alunoRepository, times(1)).findByRegistroDeAluno(criarUsuarioAlunoDTORequest.registroDeAluno());
        verify(alunoMapper, times(1)).toAluno(criarUsuarioAlunoDTORequest);
        verify(alunoMapper, times(1)).toAlunoDTOResponse(aluno);
    }

    @Test
    void deveDarNaotemAlunoComEsseIdExceptionTest()
    {
        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.empty());

        assertThrows(NaoTemAlunoComEsseIdException.class, () -> alunoService.obterAlunoPorId(aluno.getId()));

        verify(alunoRepository, times(1)).findById(aluno.getId());
        verifyNoMoreInteractions(alunoRepository);

    }

    @Test
    void deveObterOAlunoPorIdTest()
    {
        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));
        when(alunoMapper.toAlunoDTOResponse(aluno)).thenReturn(alunoDTOResponse);

        AlunoDTOResponse alunoRetornado = alunoService.obterAlunoPorId(aluno.getId());
        assertEquals(alunoDTOResponse, alunoRetornado);

        verify(alunoRepository, times(1)).findById(aluno.getId());
        verify(alunoMapper, times(1)).toAlunoDTOResponse(aluno);
        verifyNoMoreInteractions(alunoRepository);
        verifyNoMoreInteractions(alunoMapper);
    }

    @Test
    void deveDarExceptionAtualizarAlunoTest()
    {
        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.empty());
        assertThrows(NaoTemAlunoComEsseIdException.class, () -> alunoService.atualizarAluno(aluno.getId(), atualizarAlunoDTORequest));
        verify(alunoRepository , times(1)).findById(aluno.getId());
        verifyNoInteractions(alunoMapper);
        verifyNoMoreInteractions(alunoRepository);
    }

    @Test
    void deveAtualizarOAlunoTest() {
        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));
        doAnswer(invocation -> {
            AtualizarAlunoDTORequest dto = invocation.getArgument(0);
            Aluno aluno1 = invocation.getArgument(1);
            aluno1.setNome(dto.nome());
            aluno1.setEmail(dto.email());
            aluno1.setSenha(dto.senha());
            return null;
        }).when(alunoMapper).toAlunoAtualizar(any(AtualizarAlunoDTORequest.class), any(Aluno.class));
        when(alunoRepository.save(any(Aluno.class))).thenAnswer(invocation ->  invocation.getArgument(0));

        alunoService.atualizarAluno(aluno.getId(), atualizarAlunoDTORequest);
        ArgumentCaptor<Aluno> captor = ArgumentCaptor.forClass(Aluno.class);
        verify(alunoRepository, times(1)).save(captor.capture());
        Aluno alunoCapturado = captor.getValue();
        assertEquals("novo nome" , alunoCapturado.getNome());
        assertEquals("weimar2@example.com", alunoCapturado.getEmail());
        assertEquals("987654321" , alunoCapturado.getSenha());

        verify(alunoMapper, times(1)).toAlunoAtualizar(atualizarAlunoDTORequest, aluno);
        verifyNoMoreInteractions(alunoRepository);
        verifyNoMoreInteractions(alunoMapper);
    }

    @Test
    void deveDarExceptionNoDeletarAlunoTest()
    {
        when(alunoRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NaoTemAlunoComEsseIdException.class, () -> alunoService.deletarAluno(2L));

        verify(alunoRepository, times(1)).findById(2L);
        verify(alunoRepository, times(0)).deleteById(2L);

    }

    @Test
    void deveDeletarAlunoTest()
    {
        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));

        alunoService.deletarAluno(aluno.getId());
        verify(alunoRepository, times(1)).findById(aluno.getId());
        verify(alunoRepository, times(1)).deleteById(aluno.getId());
        verifyNoMoreInteractions(alunoRepository);
    }

    @Test
    void deveDarExceptionNoObterTodosAlunosTest()
    {
        when(alunoRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NenhumAlunoCadastradoException.class, () -> alunoService.obterTodosAlunos());

        verify(alunoRepository, times(1)).findAll();
        verifyNoMoreInteractions(alunoRepository);
    }

    @Test
    void deveListarTodosAlunosTest()
    {
        List<Aluno> alunos = List.of(aluno);
        when(alunoRepository.findAll()).thenReturn(alunos);
        when(alunoMapper.toAlunoDTOResponse(aluno)).thenReturn(alunoDTOResponse);

        List<AlunoDTOResponse> alunosRetornados = alunoService.obterTodosAlunos();
        assertNotNull(alunosRetornados);
        assertEquals(1, alunosRetornados.size());
        assertEquals(alunoDTOResponse, alunosRetornados.get(0));

        verify(alunoRepository, times(1)).findAll();
        verify(alunoMapper, times(1)).toAlunoDTOResponse(aluno);
        verifyNoMoreInteractions(alunoRepository, alunoMapper);

    }

    @Test
    void deveDarExceptionNoAcharAlunoPeloEmail()
    {
        when(alunoRepository.findByEmail(aluno.getEmail())).thenReturn(Optional.empty());

        assertThrows(NenhumAlunoComEsseEmailException.class, () -> alunoService.acharAlunoPeloEmail(aluno.getEmail()));

        verify(alunoRepository, times(1)).findByEmail(aluno.getEmail());
        verifyNoMoreInteractions(alunoRepository);
    }

    @Test
    void deveAcharAlunoPeloEmail()
    {
        when(alunoRepository.findByEmail(aluno.getEmail())).thenReturn(Optional.of(aluno));
        when(alunoMapper.toAlunoDTOResponse(aluno)).thenReturn(alunoDTOResponse);

        AlunoDTOResponse alunoRetornado = alunoService.acharAlunoPeloEmail(aluno.getEmail());
        assertEquals(alunoDTOResponse, alunoRetornado);
        verify(alunoRepository, times(1)).findByEmail(aluno.getEmail());
        verify(alunoMapper, times(1)).toAlunoDTOResponse(aluno);
        verifyNoMoreInteractions(alunoRepository, alunoMapper);
    }


    @Test
    void deveDarExceptionAcharAlunoPeloRegistro()
    {
        when(alunoRepository.findByRegistroDeAluno(aluno.getRegistroDeAluno())).thenReturn(Optional.empty());
        assertThrows(NenhumAlunoComEsseRegistroDeAlunoException.class, () -> alunoService.acharAlunoPeloRegistroDeAluno(aluno.getRegistroDeAluno()));

        verify(alunoRepository).findByRegistroDeAluno(aluno.getRegistroDeAluno());
        verifyNoInteractions(alunoMapper);
        verifyNoMoreInteractions(alunoRepository);
    }

    @Test
    void deveAcharAlunoPeloRegistro()
    {
        when(alunoRepository.findByRegistroDeAluno(aluno.getRegistroDeAluno())).thenReturn(Optional.of(aluno));
        when(alunoMapper.toAlunoDTOResponse(aluno)).thenReturn(alunoDTOResponse);

        AlunoDTOResponse alunoRetornadoDTO = alunoService.acharAlunoPeloRegistroDeAluno(aluno.getRegistroDeAluno());
        assertEquals(alunoDTOResponse, alunoRetornadoDTO);

        verify(alunoRepository, times(1)).findByRegistroDeAluno(aluno.getRegistroDeAluno());
        verify(alunoMapper, times(1)).toAlunoDTOResponse(aluno);
        verifyNoMoreInteractions(alunoRepository, alunoMapper);
    }
}
