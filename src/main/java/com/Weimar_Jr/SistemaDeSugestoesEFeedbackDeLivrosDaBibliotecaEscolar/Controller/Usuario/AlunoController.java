package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Controller.Usuario;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.FeedbackDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AlunoDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AtualizarAlunoDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.CriarUsuarioAlunoDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Biblioteca.FeedbackService;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/aluno")
public class AlunoController {

    final AlunoService alunoService;
    final FeedbackService feedbackService;

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTOResponse> obterAlunoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.obterAlunoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTOResponse>> obterTodosAlunos() {
        return ResponseEntity.ok(alunoService.obterTodosAlunos());
    }

    @PostMapping
    public ResponseEntity<AlunoDTOResponse> criarAluno(@RequestBody  @Valid CriarUsuarioAlunoDTORequest alunoDTO) {
        AlunoDTOResponse alunoDTOResponse = alunoService.cadastrarAluno(alunoDTO);
        return ResponseEntity.status(201).body(alunoDTOResponse);
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<Void> atualizarAluno(@RequestBody AtualizarAlunoDTORequest alunoDTO) {
        alunoService.atualizarAluno(alunoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aluno/{email}")
    public ResponseEntity<AlunoDTOResponse> acharAlunoPeloEmail(@PathVariable String email) {
        return ResponseEntity.ok(alunoService.acharAlunoPeloEmail(email));
    }

    @GetMapping("/meus-feedbacks")
    public ResponseEntity<List<FeedbackDTOResponse>> meusFeedbacks() {
        return ResponseEntity.ok(feedbackService.meusFeedbacksAluno());

    }
}
