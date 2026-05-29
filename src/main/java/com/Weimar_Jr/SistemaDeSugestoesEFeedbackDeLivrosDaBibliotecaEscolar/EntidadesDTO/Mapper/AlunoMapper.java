package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AlunoDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AtualizarAlunoDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.CriarUsuarioAlunoDTORequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {LivroMapper.class, FeedbackMapper.class})
public interface AlunoMapper {


    @Mapping( target = "id", ignore = true)
    @Mapping( target = "livroEmprestado", ignore = true)
    @Mapping( target = "feedbacks", ignore = true)
    AlunoDTOResponse toAlunoDTOResponse(Aluno aluno);
    @Mapping( target = "registroDeAluno", ignore = true)
    @Mapping( target = "id", ignore = true)
    @Mapping( target = "feedbacks", ignore = true)
    @Mapping( target = "livroEmprestado", ignore = true)
    void toAlunoAtualizar(AtualizarAlunoDTORequest atualizarAlunoDTO, @MappingTarget Aluno aluno);
    Aluno toAluno(CriarUsuarioAlunoDTORequest criarAlunoDTO);
}
