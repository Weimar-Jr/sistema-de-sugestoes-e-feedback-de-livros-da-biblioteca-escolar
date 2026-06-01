package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Administrador;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.AtualizarFeedbackDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.AdministradorMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Administrador.AtualizarAdministradorDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Administrador.CriarUsuarioAdministradorDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.AdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdministradorService {
    final AdministradorRepository administradorRepository;
    final AdministradorMapper administradorMapper;

    public void criarAdministrador(CriarUsuarioAdministradorDTORequest administradorDTORequest) {
        Administrador administrador = administradorMapper.toAdministrador(administradorDTORequest);
        administradorRepository.save(administrador);
    }

    public Administrador obterAdministradorPorId(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado com ID: " + id));
    }

    public void atualizarAdministrador(Long id, AtualizarAdministradorDTORequest administradorDTORequest) {
        Administrador administradorExistente = obterAdministradorPorId(id);
        administradorMapper.toAdministradorAtualizar(administradorDTORequest, administradorExistente);
        administradorRepository.save(administradorExistente);
    }

        public void deletarAdministrador(Long id) {
            Administrador administradorExistente = obterAdministradorPorId(id);
            administradorRepository.delete(administradorExistente);
        }

}
