# 🏥 API Voll-Med

Cursos da Alura utilizados:
1. Spring Boot 3: Desenvolva uma API Rest em Java
2. Spring Boot 3: aplique boas práticas e proteja uma API Rest
3. Spring Boot 3: documente, teste e prepare uma API para o deploy

Projeto desenvolvido durante meus estudos na **Alura**, simulando o sistema de uma clínica médica fictícia.  
A aplicação implementa um **CRUD para médicos e pacientes**, utilizando **Java 21, Spring Boot 3, PostgreSQL e Docker**, além de práticas de **testes automatizados** e **configuração de segurança com Bearer Token**.

---

## 🔧 Tecnologias

- **Java 21**  
- **Spring Boot 3** (Web, Data JPA, Flyway, Spring Security)  
- **PostgreSQL**  
- **Docker & Docker Compose**  
- **Maven**  
- **JUnit 5 & Spring Boot Test** (testes automatizados)

---

## ✨ Funcionalidades

### Médicos
- Cadastrar novos médicos  
- Listar todos os médicos  
- Atualizar dados de médicos  
- Desativar ou excluir médicos do banco  

### Pacientes
- Cadastrar novos pacientes  
- Listar todos os pacientes  
- Atualizar dados de pacientes  
- Excluir pacientes

### Consultas
- Cadastrar novas consultas, selecionando médico específico ou aleatóriamente quando disponível para o horário escolhido com a especialidade desejada  
- Excluir consultas

### Banco de Dados
- Migrações automáticas usando **Flyway**  

### Segurança
- **Autenticação e Autorização** via **Bearer Token (JWT)**  
- Rotas protegidas exigem envio de um token válido no cabeçalho `Authorization: Bearer <token>`  

### Testes Automatizados
- **Testes de integração e unitários** com **JUnit 5** e **Spring Boot Test**  
- Cobertura de cenários principais, garantindo a estabilidade do CRUD e da autenticação  

---

## ▶️ Como rodar o projeto em desenvolvimento

```bash
# 1. Clonar o repositório
git clone https://github.com/arthurolivv/Voll-Med.git
cd Voll-Med

# 2. Subir o banco de dados com Docker
docker-compose up -d

# 3. Rodar a aplicação (perfil de desenvolvimento)
./mvnw spring-boot:run

# 4. Acessar API (Modo desenvolvedor)
Em seu navegador acesse: http://localhost:8080/swagger-ui/index.html
```
🔑 Configuração de Autenticação (Bearer Token)

Cadastro de usuário:
Crie um usuário ou utilize as credenciais definidas no banco para autenticação.

Obtenção do token:
Faça uma requisição POST para /login enviando email e password do usuario no corpo.
A resposta conterá um campo token.

Acesso às rotas protegidas
Inclua o token no cabeçalho
