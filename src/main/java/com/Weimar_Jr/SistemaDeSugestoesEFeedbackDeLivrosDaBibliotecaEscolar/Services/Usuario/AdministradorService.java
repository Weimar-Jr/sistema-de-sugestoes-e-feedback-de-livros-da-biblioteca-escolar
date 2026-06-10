package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario;

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
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdministradorService {
    private PasswordEncoder passwordEncoder;
    final AdministradorRepository administradorRepository;
    final AdministradorMapper administradorMapper;

    public AdministradorDTOResponse obterAdministradorPorId(Long id) {
        return administradorMapper.toAdministradorDTOResponse(acharAdministradorPorId(id));
    }

    public AdministradorDTOResponse criarAdministrador(CriarUsuarioAdministradorDTORequest administradorDTORequest) {
        if(jaTemAdminComEsseCpf(administradorDTORequest.cpf()))
        {
            throw new JaTemAdminComEsseCpfException(administradorDTORequest.cpf());
        }
        if(jaTemAdminComEsseEmail(administradorDTORequest.email()))
        {
            throw new JaTemAdminComEsseEmailException(administradorDTORequest.email());
        }
        Administrador administrador = administradorMapper.toAdministrador(administradorDTORequest);
        administrador.setSenha(passwordEncoder.encode(administradorDTORequest.senha()));
        administradorRepository.save(administrador);
        return administradorMapper.toAdministradorDTOResponse(administrador);
    }

    private Administrador acharAdministradorPorId(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new NenhumAdminComEsseIDException(id));
    }

    public void atualizarAdministrador(Long id, AtualizarAdministradorDTORequest administradorDTORequest) {
        Administrador administradorExistente = acharAdministradorPorId(id);
        administradorMapper.toAdministradorAtualizar(administradorDTORequest, administradorExistente);
        administradorRepository.save(administradorExistente);
    }

    public void deletarAdministrador(Long id) {
        Administrador administradorExistente = acharAdministradorPorId(id);
        administradorRepository.delete(administradorExistente);
    }
    public List<AdministradorDTOResponse> obterTodosAdministradores() {
        List<AdministradorDTOResponse> administradores = administradorRepository.findAll().stream()
                .map(administradorMapper::toAdministradorDTOResponse)
                .toList();
        if (administradores.isEmpty()) {
            throw new NenhumAdministradorCadastradoExeption();
        }
        return administradores;
    }
    public AdministradorDTOResponse obterAdministradorPorCpf(String cpf) {
        Administrador administrador = administradorRepository.findByCpf(cpf).orElseThrow(() -> new NenhumAdminComEsseCpfException(cpf));
        return administradorMapper.toAdministradorDTOResponse(administrador);
    }

    private Boolean jaTemAdminComEsseCpf(String cpf)
    {
        return administradorRepository.findByCpf(cpf).isPresent();
    }

    private  Boolean jaTemAdminComEsseEmail(String email)
    {
        return administradorRepository.findByEmail(email).isPresent();
    }

}
