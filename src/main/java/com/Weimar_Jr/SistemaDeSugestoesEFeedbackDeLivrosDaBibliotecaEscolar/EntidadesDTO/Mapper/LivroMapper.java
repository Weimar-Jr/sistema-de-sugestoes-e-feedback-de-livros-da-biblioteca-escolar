package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Mapper;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AdicionarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.AtualizarLivroDTORequest;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.EntidadesDTO.Biblioteca.Livro.LivroDTOResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {AlunoMapper.class, FeedbackMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LivroMapper {
    LivroDTOResponse toLivroDTOResponse(Livro livro);
    Livro toLivro(AdicionarLivroDTORequest criarLivroDTO);
    void toLivroAtualizar(AtualizarLivroDTORequest atualizarLivroDTO, @MappingTarget Livro livro);

}
