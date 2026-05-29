package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AdicionarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AtualizarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.LivroDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AlunoMapper.class, FeedbackMapper.class})
public interface LivroMapper {
    LivroDTOResponse toLivroDTOResponse(Livro livro);
     @Mapping( target = "id", ignore = true)
     @Mapping( target = "aluno", ignore = true)
     @Mapping( target = "feedbacks", ignore = true)
     Livro toLivro(AdicionarLivroDTORequest criarLivroDTO);
    @Mapping( target = "id", ignore = true)
    @Mapping( target = "aluno", ignore = true)
    @Mapping( target = "feedbacks", ignore = true)
     void toLivroAtualizar(AtualizarLivroDTORequest atualizarLivroDTO, @MappingTarget Livro livro);
}
