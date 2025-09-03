# 🏥 API Voll-Med

Projeto desenvolvido durante meus estudos na **Alura**, simulando o sistema de uma clínica médica fictícia.  
A aplicação implementa um **CRUD para médicos e pacientes**, utilizando **Java 21, Spring Boot 3, PostgreSQL e Docker**.

---

## 🔧 Tecnologias

- **Java 21**  
- **Spring Boot 3** (Web, Data JPA, Flyway)  
- **PostgreSQL**  
- **Docker & Docker Compose**  
- **Maven**

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

### Banco de dados
- Migrações automáticas usando **Flyway**  

---

## ▶️ Como rodar o projeto

```bash
# 1. Clonar o repositório
git clone https://github.com/arthurolivv/Voll-Med.git
cd Voll-Med

# 2. Subir o banco de dados com Docker
docker-compose up -d

# 3. Rodar a aplicação
./mvnw spring-boot:run
