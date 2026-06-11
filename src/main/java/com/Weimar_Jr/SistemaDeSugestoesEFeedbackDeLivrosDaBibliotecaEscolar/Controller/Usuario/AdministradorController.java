package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Controller.Usuario;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Administrador.AdministradorDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Administrador.AtualizarAdministradorDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Administrador.CriarUsuarioAdministradorDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario.AdministradorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdministradorController {

    final AdministradorService administradorService;

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTOResponse> obterAdministradorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(administradorService.obterAdministradorPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AdministradorDTOResponse>> obterTodosAdministradores() {
        return ResponseEntity.ok(administradorService.obterTodosAdministradores());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<AdministradorDTOResponse> obterAdminPeloCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(administradorService.obterAdministradorPorCpf(cpf));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AdministradorDTOResponse> obterAdminPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(administradorService.obterAdministradorPorEmail(email));
    }

    @PostMapping
    public ResponseEntity<AdministradorDTOResponse> criarAdministrador(@RequestBody  @Valid CriarUsuarioAdministradorDTORequest administradorDTO) {
        AdministradorDTOResponse administradorDTOResponse = administradorService.criarAdministrador(administradorDTO);
        return ResponseEntity.status(201).body(administradorDTOResponse);
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<Void> atualizarAdministrador( @RequestBody  AtualizarAdministradorDTORequest administradorDTO) {
        administradorService.atualizarAdministrador(administradorDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAdministrador(@PathVariable Long id) {
        administradorService.deletarAdministrador(id);
        return ResponseEntity.noContent().build();
    }

}
