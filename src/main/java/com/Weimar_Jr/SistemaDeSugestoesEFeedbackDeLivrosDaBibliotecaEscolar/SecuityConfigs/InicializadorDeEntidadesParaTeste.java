package com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.SecuityConfigs;

import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Biblioteca.Livro;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Administrador;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Entidades.Usuarios.Aluno;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.AdministradorRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.AlunoRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.FeedbackRepository;
import com.Weimar_Jr.SistemaDeSugestoesEFeedbackDeLivrosDaBibliotecaEscolar.Repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InicializadorDeEntidadesParaTeste implements CommandLineRunner {

    private final AdministradorRepository administradorRepository;
    private final AlunoRepository alunoRepository;
    private final LivroRepository livroRepository;
    private final FeedbackRepository feedbackRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (administradorRepository.count() == 0) {
            Administrador admin = new Administrador();
            admin.setNome("Administrador Master");
            admin.setEmail("admin@teste.com");
            admin.setCpf("000.000.000-00");
            admin.setSenha(passwordEncoder.encode("admin123"));
            administradorRepository.save(admin);
            System.out.println("✅ Administrador padrão criado: admin@teste.com / admin123");
        }
        if(alunoRepository.count() == 0) {
            Aluno aluno = new Aluno();
            aluno.setNome("Aluno Master");
            aluno.setEmail("aluno@teste.com");
            aluno.setRegistroDeAluno("1234567890");
            aluno.setSenha(passwordEncoder.encode("aluno123"));
            alunoRepository.save(aluno);
            System.out.println("✅ Aluno padrão criado: aluno@teste.com / aluno123");
        }

        if(livroRepository.count() == 0)
        {
            Livro livro1 = new Livro();
            livro1.setTitulo("Dom Casmurro");
            livro1.setAutor("Machado de Assis");
            livro1.setEditora("Editora Globo");
            livro1.setAnoPublicacao(1899);
            livro1.setGenero("Romance");
            livro1.setDisponivel(true);
            livro1.setDescricao("Uma obra-prima da literatura brasileira, narrada por Bentinho.");
            livroRepository.save(livro1);

            Livro livro2 = new Livro();
            livro2.setTitulo("O Pequeno Príncipe");
            livro2.setAutor("Antoine de Saint-Exupéry");
            livro2.setEditora("Agir");
            livro2.setAnoPublicacao(1943);
            livro2.setGenero("Ficção");
            livro2.setDisponivel(true);
            livro2.setDescricao("Clássico mundial sobre amizade e amor.");
            livroRepository.save(livro2);

            System.out.println("✅ Dois livros de exemplo criados.");
        }

    }
}
