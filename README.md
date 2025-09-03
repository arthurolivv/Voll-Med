# 🏥 API Voll-Med - Spring Boot 3

Esse projeto foi desenvolvido durante meus estudos na **Alura**.  
A ideia é simular o sistema de um hospital fictício, criando um **CRUD para médicos e pacientes**, com **Java 21, Spring Boot 3, PostgreSQL e Docker**.  

---

## 🔧 Tecnologias

- Java 21  
- Spring Boot 3 (Web, Data JPA, Flyway)  
- PostgreSQL  
- Docker & Docker Compose  
- Maven  

---

## ✨ O que o sistema faz?

- **Médicos**
  - Cadastrar, listar, atualizar e excluir (desativar ou apagar do banco)  

- **Pacientes**
  - Cadastrar, listar, atualizar e excluir  

- **Banco de dados**
  - Migrações automáticas com Flyway  

---

## ▶️ Como rodar o projeto

### 1. Clone o repositório
# Clonar o repositório git clone https://github.com/arthurolivv/Voll-Med.git cd Voll-Med #
# cd Voll-Med #


### 2. Suba o banco de dados com Docker
# docker-compose up -d

### 3. Rode a aplicação
./mvnw spring-boot:run

A API vai estar disponível em:
👉 http://localhost:8080




