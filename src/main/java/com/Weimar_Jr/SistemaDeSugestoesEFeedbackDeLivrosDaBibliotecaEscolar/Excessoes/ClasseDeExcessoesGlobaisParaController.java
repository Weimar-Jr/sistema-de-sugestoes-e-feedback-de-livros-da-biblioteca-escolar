package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AdministradorException.NenhumAdminComEsseIDException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AdministradorException.NenhumAdministradorCadastradoExeption;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.ExceptionDeNegocio.JaTemAlunoComEsseEmailException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.ExceptionDeNegocio.JaTemAlunoComEsseRegistroException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NenhumAlunoComEsseEmailException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NenhumAlunoComEsseRegistroDeAlunoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NaoTemAlunoComEsseIdException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NenhumAlunoCadastradoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.FeedbackException.NenhumFeedbackDesseAlunoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.FeedbackException.NenhumFeedbackDoLivroFaladoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.AlunoJaPossuiUmLivroEmprestadoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.ExceptionsDeBusca.*;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.LivroIndisponivelException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.LivroException.LivroJaConstaComoDevolvidoException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClasseDeExcessoesGlobaisParaController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body("Dados de entrada inválidos: " + e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler({NenhumAdminComEsseIDException.class,
            NenhumAdministradorCadastradoExeption.class,
            NenhumAlunoCadastradoException.class,
            NaoTemAlunoComEsseIdException.class,
            LivroNaoEncontradoException.class,
            NenhumLivroCadastradoException.class,
            NenhumLivroAchadoPelotituloFaladoException.class,
            NenhumLivroDesseAutorException.class,
            NenhumLivroDesseGeneroException.class,
            NenhumLivroDisponivelException.class,
            NenhumLivroIndisponivelException.class,
            NenhumLivroIndisponivelException.class,
            NenhumFeedbackDesseAlunoException.class,
            NenhumFeedbackDoLivroFaladoException.class,
            NenhumAlunoComEsseEmailException.class,
            NenhumAlunoComEsseRegistroDeAlunoException.class
    })
    public ResponseEntity<String> handleNotFoundExceptions(RuntimeException e) {
        return ResponseEntity.status(404).body(e.getMessage());

    }

    @ExceptionHandler({AlunoJaPossuiUmLivroEmprestadoException.class, LivroIndisponivelException.class, LivroJaConstaComoDevolvidoException.class, JaTemAlunoComEsseEmailException.class, NenhumAlunoComEsseRegistroDeAlunoException.class, JaTemAlunoComEsseEmailException.class, JaTemAlunoComEsseRegistroException.class})
    public ResponseEntity<String> handleConflictExceptions(RuntimeException e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(400).body("Requisição inválida: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(500).body("Ocorreu um erro inesperado: " + e.getMessage());
    }

}