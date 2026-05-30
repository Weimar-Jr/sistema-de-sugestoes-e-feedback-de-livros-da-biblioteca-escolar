package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Feedback;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.AtualizarFeedbackDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.CriarFeedbackDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Feedback.FeedbackDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {AlunoMapper.class, LivroMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FeedbackMapper {
    FeedbackDTOResponse toFeedbackDTOResponse(Feedback feedback);
    Feedback toFeedback(CriarFeedbackDTORequest criarFeedbackDTO);
    void toFeedbackAtualizar(AtualizarFeedbackDTORequest atualizarFeedbackDTO, @MappingTarget Feedback feedback);

}
