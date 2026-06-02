package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Services.Usuario;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper.AlunoMapper;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AlunoDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AtualizarAlunoDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.CriarUsuarioAlunoDTORequest;
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
        alunoRepository.save(aluno);
        return alunoMapper.toAlunoDTOResponse(aluno);
    }
    public void atualizarAluno(Long id, AtualizarAlunoDTORequest alunoDTO) {

        Aluno aluno = acharAlunoPeloId(id);
        alunoMapper.toAlunoAtualizar(alunoDTO, aluno);
        alunoRepository.save(aluno);
    }

    public void deletarAluno(Long id) {
        alunoRepository.deleteById(id);
    }

    public Boolean verificarSeContaDoAlunoExiste(String email) {
        return alunoRepository.findByEmail(email).isPresent();
    }

     public Aluno acharAlunoPeloId(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public List<AlunoDTOResponse> obterTodosAlunos() {
        return alunoRepository.findAll().stream().map(alunoMapper::toAlunoDTOResponse).toList();
    }
}
