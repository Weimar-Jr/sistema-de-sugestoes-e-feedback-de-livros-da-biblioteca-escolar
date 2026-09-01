# 📚 Sistema de Sugestões e Feedback de Livros - API RESTful

> API RESTful para gestão de acervo, empréstimos, sugestões e avaliações de livros em bibliotecas escolares, com controle de acesso baseado em funções (RBAC).

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=flat&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-blue?style=flat&logo=springsecurity)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-green?style=flat&logo=swagger)

---

## 🚀 Sobre o Projeto

O objetivo principal desta aplicação é automatizar o fluxo de gestão de uma biblioteca escolar, permitindo que **alunos** realizem empréstimos, avaliem obras lidas e enviem sugestões de novos livros, enquanto **administradores** gerenciam o acervo e aprovam requisições.

### ✨ Principais Funcionalidades
- **Gestão de Acervo:** Cadastro, atualização e filtragem de livros e autores.
- **Empréstimos & Devoluções:** Controle de regras de negócio para empréstimos de livros aos alunos.
- **Sistema de Avaliação (Feedback):** Alunos podem atribuir notas e comentários sobre os livros.
- **Autenticação & Segurança:** Autenticação via JWT com permissões distintas por perfil (`ROLE_ADMIN`, `ROLE_ALUNO`).

---

## 🛠️ Tecnologias e Ferramentas

- **Linguagem:** Java 17
- **Framework Principal:** Spring Boot 3
- **Persistência de Dados:** Spring Data JPA / Hibernate
- **Banco de Dados:** H2 Database (Desenvolvimento) / PostgreSQL
- **Segurança:** Spring Security + JSON Web Tokens (JWT)
- **Mapeamento & DTOs:** MapStruct & Java Records
- **Documentação:** OpenAPI 3 / Swagger UI
- **Gerenciador de Dependências:** Maven

---

## 🏛️ Arquitetura e Boas Práticas

O projeto segue a arquitetura em camadas e boas práticas do ecossistema Spring:
- **DTO Pattern com Java Records:** Imutabilidade e segurança no transporte de dados.
- **Mapeamento Eficiente:** Uso do **MapStruct** para conversão performática entre DTOs e Entidades.
- **Tratamento Global de Exceções:** Captura centralizada de erros com `@ControllerAdvice` retornando respostas HTTP semânticas (400, 403, 404, 409).
- **Validação de Dados:** Uso de Jakarta Bean Validation (`@Valid`, `@NotNull`, `@NotBlank`).

---
