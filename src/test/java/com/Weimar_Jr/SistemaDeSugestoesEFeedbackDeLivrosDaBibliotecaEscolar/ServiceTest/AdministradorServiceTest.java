package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.ServiceTest;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Administrador;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.AdministradorMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Administrador.AdministradorDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Administrador.AtualizarAdministradorDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Administrador.CriarUsuarioAdministradorDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AdministradorException.ExceptionDeNegocio.JaTemAdminComEsseCpfException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AdministradorException.ExceptionDeNegocio.JaTemAdminComEsseEmailException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AdministradorException.NenhumAdminComEsseCpfException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AdministradorException.NenhumAdminComEsseIDException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AdministradorException.NenhumAdministradorCadastradoExeption;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.AdministradorRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario.AdministradorService;
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
class AdministradorServiceTest {
    @Mock
    AdministradorRepository administradorRepository;
    @Mock
    AdministradorMapper administradorMapper;
    @InjectMocks
    AdministradorService administradorService;

    Administrador admin = new Administrador();
    AdministradorDTOResponse administradorDTOResponse;
    CriarUsuarioAdministradorDTORequest criarUsuarioAdministradorDTORequest;
    AtualizarAdministradorDTORequest atualizarAdministradorDTORequest;

    @BeforeEach
    void setUp()
    {
        admin.setId(1L);
        admin.setNome("weimar");
        admin.setEmail("weimar@email.com");
        admin.setSenha("senha123");
        admin.setCpf("12345678910");

        criarUsuarioAdministradorDTORequest = new CriarUsuarioAdministradorDTORequest("weimar", "weimar@email.com", "senha123", "12345678910");
        atualizarAdministradorDTORequest = new AtualizarAdministradorDTORequest("novo nome", "novoemail@email.com", "novasenha");
        administradorDTOResponse = new AdministradorDTOResponse(1L, "weimar", "weimar@email.com", "12345678910");
    }

    @Test
    void deveDarExceptionDeCpfCriarUsuarioAdministradorTest() {
        when(administradorRepository.findByCpf(admin.getCpf())).thenReturn(Optional.of(admin));
        assertThrows(JaTemAdminComEsseCpfException.class, () -> administradorService.criarAdministrador(criarUsuarioAdministradorDTORequest));
        verify(administradorRepository, times(1)).findByCpf(admin.getCpf());
        verifyNoMoreInteractions(administradorRepository);

    }

    @Test
    void deveDarExceptionDeEmailCriarUsuarioAdministradorTest()
    {
        when(administradorRepository.findByCpf(admin.getCpf())).thenReturn(Optional.empty());
        when(administradorRepository.findByEmail(admin.getEmail())).thenReturn(Optional.of(admin));
        assertThrows(JaTemAdminComEsseEmailException.class, () -> administradorService.criarAdministrador(criarUsuarioAdministradorDTORequest));
        verify(administradorRepository,times(1)).findByCpf(admin.getCpf());
        verify(administradorRepository,times(1)).findByEmail(admin.getEmail());
        verifyNoMoreInteractions(administradorRepository);
    }

    @Test
    void deveCriarAdministradorTest() {
        when(administradorRepository.findByCpf(criarUsuarioAdministradorDTORequest.cpf())).thenReturn(Optional.empty());
        when(administradorRepository.findByEmail(criarUsuarioAdministradorDTORequest.email())).thenReturn(Optional.empty());
        when(administradorRepository.save(admin)).thenAnswer((invocation -> invocation.getArgument(0)));
        when(administradorMapper.toAdministrador(criarUsuarioAdministradorDTORequest)).thenReturn(admin);
        when(administradorMapper.toAdministradorDTOResponse(admin)).thenReturn(administradorDTOResponse);

        AdministradorDTOResponse adminDTORetornado = administradorService.criarAdministrador(criarUsuarioAdministradorDTORequest);
        ArgumentCaptor<Administrador> captor = ArgumentCaptor.forClass(Administrador.class);
        verify(administradorRepository, times(1)).save(captor.capture());
        Administrador administradorCapturado = captor.getValue();

        assertEquals(administradorDTOResponse, adminDTORetornado);
        assertEquals(admin, administradorCapturado);

        verify(administradorRepository, times(1)).findByCpf(criarUsuarioAdministradorDTORequest.cpf());
        verify(administradorRepository, times(1)).findByEmail(criarUsuarioAdministradorDTORequest.email());
        verify(administradorMapper, times(1)).toAdministrador(criarUsuarioAdministradorDTORequest);
        verify(administradorMapper, times(1)).toAdministradorDTOResponse(admin);
        verifyNoMoreInteractions(administradorRepository, administradorMapper);
    }

    @Test
    void deveDarExceptionDeNenhumAdminComEsseIDTest() {
        when(administradorRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NenhumAdminComEsseIDException.class, () -> administradorService.obterAdministradorPorId(1L));
        verify(administradorRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(administradorRepository);
    }

    @Test
    void deveObterOAdministradorPorIdTest() {
        when(administradorRepository.findById(admin.getId())).thenReturn(Optional.of(admin));
        when(administradorMapper.toAdministradorDTOResponse(admin)).thenReturn(administradorDTOResponse);
        AdministradorDTOResponse adminDTORetornado = administradorService.obterAdministradorPorId(admin.getId());
        assertEquals(administradorDTOResponse, adminDTORetornado);
        verify(administradorRepository, times(1)).findById(admin.getId());
        verify(administradorMapper, times(1)).toAdministradorDTOResponse(admin);
        verifyNoMoreInteractions(administradorRepository, administradorMapper);
    }

    @Test
    void deveDarExceptionDeNenhumAdminCadastradoTest()
    {
        when(administradorRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NenhumAdministradorCadastradoExeption.class, () -> administradorService.obterTodosAdministradores());
        verify(administradorRepository, times(1)).findAll();
        verifyNoMoreInteractions(administradorRepository);
    }

    @Test
    void deveObterTodosOsAdministradoresTest()
    {
        when(administradorRepository.findAll()).thenReturn(List.of(admin));
        when(administradorMapper.toAdministradorDTOResponse(admin)).thenReturn(administradorDTOResponse);
        List<AdministradorDTOResponse> admins = administradorService.obterTodosAdministradores();
        assertEquals(1, admins.size());
        assertEquals(administradorDTOResponse, admins.get(0));
        verify(administradorRepository, times(1)).findAll();
        verify(administradorMapper, times(1)).toAdministradorDTOResponse(admin);
        verifyNoMoreInteractions(administradorRepository, administradorMapper);
    }

    @Test
    void deveDarExceptionNenhumAdminComEsseCpfTest()
    {
        when(administradorRepository.findByCpf(admin.getCpf())).thenReturn(Optional.empty());
        assertThrows(NenhumAdminComEsseCpfException.class, () -> administradorService.obterAdministradorPorCpf(admin.getCpf()));
        verify(administradorRepository, times(1)).findByCpf(admin.getCpf());
        verifyNoMoreInteractions(administradorRepository);
    }

    @Test
    void deveObterOAdministradorPeloCpfTest()
    {
        when(administradorRepository.findByCpf(admin.getCpf())).thenReturn(Optional.of(admin));
        when(administradorMapper.toAdministradorDTOResponse(admin)).thenReturn(administradorDTOResponse);
        AdministradorDTOResponse adminDTO = administradorService.obterAdministradorPorCpf(admin.getCpf());
        assertEquals(administradorDTOResponse, adminDTO);

    }

    @Test
    void deveDarExceptionNoDeletarAlunoTest()
    {
        when(administradorRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NenhumAdminComEsseIDException.class, () -> administradorService.deletarAdministrador(2L));
        verify(administradorRepository, times(1)).findById(2L);
        verify(administradorRepository, times(0)).delete(admin);
        verifyNoMoreInteractions(administradorRepository);
    }

    @Test
    void deveDeletarAlunoTest()
    {
        when(administradorRepository.findById(admin.getId())).thenReturn(Optional.of(admin));
        administradorService.deletarAdministrador(admin.getId());
        verify(administradorRepository, times(1)).findById(admin.getId());
        verify(administradorRepository, times(1)).delete(admin);
        verifyNoMoreInteractions(administradorRepository);
    }

    @Test
    void deveDarExceptionNoAtualizarAdministradorTest()
    {
        when(administradorRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NenhumAdminComEsseIDException.class, () -> administradorService.atualizarAdministrador(2L, atualizarAdministradorDTORequest));
        verify(administradorRepository, times(1)).findById(2L);
        verifyNoMoreInteractions(administradorRepository);
        verifyNoInteractions(administradorMapper);
    }

    @Test
    void deveAtualizarAdministradorTest()
    {
        when(administradorRepository.findById(admin.getId())).thenReturn(Optional.of(admin));
        doAnswer(invocation -> {
            AtualizarAdministradorDTORequest dto = invocation.getArgument(0);
            Administrador administrador = invocation.getArgument(1);
            administrador.setNome(dto.nome());
            administrador.setEmail(dto.email());
            administrador.setSenha(dto.senha());
            return null;

        }).when(administradorMapper).toAdministradorAtualizar(any(AtualizarAdministradorDTORequest.class), any(Administrador.class));

        administradorService.atualizarAdministrador(admin.getId(), atualizarAdministradorDTORequest);
        ArgumentCaptor<Administrador> captor = ArgumentCaptor.forClass(Administrador.class);
        verify(administradorRepository, times(1)).save(captor.capture());
        Administrador administradorSalvo = captor.getValue();
        assertEquals("novo nome", administradorSalvo.getNome());
        assertEquals("novoemail@email.com", administradorSalvo.getEmail());
        assertEquals("novasenha", administradorSalvo.getSenha());
        verify(administradorRepository, times(1)).findById(admin.getId());
        verifyNoMoreInteractions(administradorRepository);
        verify(administradorMapper, times(1)).toAdministradorAtualizar(atualizarAdministradorDTORequest,admin);
    }

}
