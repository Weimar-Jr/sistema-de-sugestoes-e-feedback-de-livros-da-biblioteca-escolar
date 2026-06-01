package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AlunoDTOResponse;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.AtualizarAlunoDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.UsuariosDTO.Aluno.CriarUsuarioAlunoDTORequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {LivroMapper.class, FeedbackMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AlunoMapper {
    AlunoDTOResponse toAlunoDTOResponse(Aluno aluno);
    void toAlunoAtualizar(AtualizarAlunoDTORequest atualizarAlunoDTO, @MappingTarget Aluno aluno);
    Aluno toAluno(CriarUsuarioAlunoDTORequest criarAlunoDTO);
}
