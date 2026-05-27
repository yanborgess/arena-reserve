#  ArenaReserve - Sistema de Agendamento de Quadras

API REST em Spring Boot para gerenciamento e aluguel de quadras esportivas, estruturada com padrões de projeto comerciais.

---

##  Tecnologias

* **Java 21** / **Spring Boot 4.x**
* **Spring Data JPA** & **PostgreSQL**
* **Validation** & **Lombok**
* **Swagger/OpenAPI 2.8.5**
* **Git** (Branches `main` e `develop`)

---

##  Padrões de Projeto (Design Patterns)

1. **Strategy:** Isola o cálculo de preços (Tarifa normal vs Tarifa de Fim de Semana com +20%).
2. **Factory Method:** Escolhe a estratégia de preço correta com base na data.
3. **Template Method:** Define o fluxo fixo de validações e conflitos de horários.
4. **Builder:** Instancia a entidade `Agendamento` de forma limpa e fluida.
5. **DTO (Data Transfer Object):** Protege o banco usando *Java Records* nas requisições.
6. **Repository:** Abstrai a persistência e as consultas SQL.

---

##  Como Executar Localmente

### 1. Configurar Credenciais
Na pasta `src/main/resources/`, duplique o arquivo `application.properties.example`, renomeie a cópia para `application.properties` e insira o usuário e a senha do seu PostgreSQL local.

### 2. Criar o Banco de Dados
No seu gerenciador de banco (pgAdmin / DBeaver), crie um banco de dados vazio com o nome: `arena_db`.

### 3. Iniciar a Aplicação
Execute no terminal da raiz do projeto:
```bash
mvn clean package
mvn spring-boot:run
```

### 4. Acessar o Swagger (Documentação)
Abra no navegador:
http://localhost:8080/swagger-ui/index.html

Nota: Todas as datas utilizam o formato brasileiro (dd/MM/yyyy HH:mm).