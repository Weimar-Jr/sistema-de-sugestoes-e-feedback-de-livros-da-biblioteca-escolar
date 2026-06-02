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

    @PostMapping
    public ResponseEntity<AdministradorDTOResponse> criarAdministrador(@RequestBody  @Valid CriarUsuarioAdministradorDTORequest administradorDTO) {
        AdministradorDTOResponse administradorDTOResponse = administradorService.criarAdministrador(administradorDTO);
        return ResponseEntity.status(201).body(administradorDTOResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarAdministrador(@PathVariable Long id, @RequestBody @Valid AtualizarAdministradorDTORequest administradorDTO) {
        administradorService.atualizarAdministrador(id, administradorDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAdministrador(@PathVariable Long id) {
        administradorService.deletarAdministrador(id);
        return ResponseEntity.noContent().build();
    }

}
