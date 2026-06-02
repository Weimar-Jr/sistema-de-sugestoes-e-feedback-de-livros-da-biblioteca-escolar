package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Controller.Biblioteca;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Feedback;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.AtualizarFeedbackDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.CriarFeedbackDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.FeedbackDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.FeedbackMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Biblioteca.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    final FeedbackService feedbackService;
    final FeedbackMapper feedbackMapper;

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTOResponse> acharFeedbackPorId(@PathVariable Long id) {
        Feedback feedback = feedbackService.acharFeedbackPorId(id);
        FeedbackDTOResponse feedbackDTOResponse = feedbackMapper.toFeedbackDTOResponse(feedback);
        return ResponseEntity.status(201).body(feedbackDTOResponse);
    }

    @GetMapping("/por-livro/{idLivro}")
    public ResponseEntity<List<FeedbackDTOResponse>> acharFeedbacksPorLivro(@PathVariable Long idLivro) {
        return ResponseEntity.ok(feedbackService.listarFeedbacksPorLivro(idLivro));
    }

    @GetMapping("/por-aluno/{idAluno}")
    public ResponseEntity<List<FeedbackDTOResponse>> acharFeedbacksPorAluno(@PathVariable Long idAluno) {
        return ResponseEntity.ok(feedbackService.listarFeedbacksPorAluno(idAluno));
    }

    @PostMapping
    public ResponseEntity<FeedbackDTOResponse> criarFeedback(@RequestBody @Valid CriarFeedbackDTORequest feedbackDTO) {
        FeedbackDTOResponse feedbackDTOResponse = feedbackService.criarFeedback(feedbackDTO);
        return ResponseEntity.status(201).body(feedbackDTOResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarFeedback(@PathVariable Long id, @RequestBody  @Valid AtualizarFeedbackDTORequest feedbackDTO) {
        feedbackService.atualizarFeedback(id, feedbackDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFeedback(@PathVariable Long id) {
        feedbackService.deletarFeedback(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ocultar-nome-no-feedback/{id}")
    public ResponseEntity<Void> ocultarNomeNoFeedback(@PathVariable Long id) {
        feedbackService.ocultarNomeNoFeedback(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/exibir-nome-no-feedback/{id}")
    public ResponseEntity<Void> exibirNomeNoFeedback(@PathVariable Long id) {
        feedbackService.mostrarNomeNoFeedback(id);
        return ResponseEntity.noContent().build();
    }

}
