package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.AlunoMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AlunoDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AtualizarAlunoDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.CriarUsuarioAlunoDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.ExceptionDeNegocio.JaTemAlunoComEsseEmailException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.ExceptionDeNegocio.JaTemAlunoComEsseRegistroException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NenhumAlunoComEsseEmailException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NenhumAlunoComEsseRegistroDeAlunoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NaoTemAlunoComEsseIdException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Excessoes.AlunoException.NenhumAlunoCadastradoException;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlunoService {

    final AlunoRepository alunoRepository;
    final AlunoMapper alunoMapper;

    public AlunoDTOResponse obterAlunoPorId(Long id) {
        return alunoMapper.toAlunoDTOResponse(acharAlunoPeloId(id));
    }

    public AlunoDTOResponse cadastrarAluno(CriarUsuarioAlunoDTORequest alunoDTO) {
        Aluno aluno = alunoMapper.toAluno(alunoDTO);
        if(verificarSeJaTemAlunoComEsseEmail(aluno.getEmail()))
        {
              throw new  JaTemAlunoComEsseEmailException(aluno.getEmail());
        }
        if(verificarSeJaTemAlunoComEsseRegistro(aluno.getRegistroDeAluno()))
        {
            throw new JaTemAlunoComEsseRegistroException();
        }
        alunoRepository.save(aluno);
        return alunoMapper.toAlunoDTOResponse(aluno);
    }
    public void atualizarAluno(Long id, AtualizarAlunoDTORequest alunoDTO) {

        Aluno aluno = acharAlunoPeloId(id);
        alunoMapper.toAlunoAtualizar(alunoDTO, aluno);
        alunoRepository.save(aluno);
    }

    public void deletarAluno(Long id) {
        acharAlunoPeloId(id);
        alunoRepository.deleteById(id);
    }

    private Boolean verificarSeJaTemAlunoComEsseEmail(String email) {
        return alunoRepository.findByEmail(email).isPresent();

    }

     public Aluno acharAlunoPeloId(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new NaoTemAlunoComEsseIdException(id));
    }

    public List<AlunoDTOResponse> obterTodosAlunos() {
        List<AlunoDTOResponse> alunosDTO = alunoRepository.findAll().stream().map(alunoMapper::toAlunoDTOResponse).toList();
        if (alunosDTO.isEmpty()) {
            throw new NenhumAlunoCadastradoException();
        }
        return alunosDTO;
    }

    public AlunoDTOResponse acharAlunoPeloRegistroDeAluno(String registroDeAluno) {

        return alunoMapper.toAlunoDTOResponse(alunoRepository.findByRegistroDeAluno(registroDeAluno).orElseThrow(() -> new NenhumAlunoComEsseRegistroDeAlunoException(registroDeAluno)));
    }

    private Boolean verificarSeJaTemAlunoComEsseRegistro(String registroAluno)
    {
        return alunoRepository.findByRegistroDeAluno(registroAluno).isPresent();
    }

    public AlunoDTOResponse acharAlunoPeloEmail(String email) {
        return alunoMapper.toAlunoDTOResponse(alunoRepository.findByEmail(email).orElseThrow(() -> new NenhumAlunoComEsseEmailException(email)));
    }
}
