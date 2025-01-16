# Projeto CRUD de Produtos

Este projeto é uma aplicação que consiste em um CRUD (Create, Read, Update, Delete) para gerenciar produtos. A aplicação é dividida em duas partes:

- **Backend**: Desenvolvido com **Spring Boot** para gerenciar a lógica de negócios e persistência de dados.
- **Frontend**: Desenvolvido com **React** para fornecer uma interface interativa.

## Requisitos

Antes de iniciar, certifique-se de ter os seguintes pré-requisitos instalados em sua máquina:

- **Java 17 ou superior**: Para executar o backend com Spring Boot.
- **Node.js** e **npm** (ou **Yarn**): Para rodar o frontend em React.
- **Postman** (opcional): Para testar a API do backend.

## Backend - Spring Boot

### 1. Configuração do Banco de Dados

Este projeto utiliza o **H2 Database** em memória para armazenamento dos produtos. Não é necessário configurar um banco de dados externo.

### 2. Como Executar o Backend

1. Clone este repositório para sua máquina local:

    ```bash
    git clone https://github.com/seu-usuario/projeto-crud-produtos.git
    cd projeto-crud-produtos
    ```

2. Navegue até o diretório do backend (Na pasta `src`).

3. Compile o projeto com **Maven**:
   
   Maven:
    ```bash
    ./mvnw clean install
    ```

4. Execute a aplicação Spring Boot:
    ```bash
    ./mvnw spring-boot:run
    ```

   Ou, se você estiver usando o IntelliJ IDEA ou Spring Tool Suite (STS), basta executar a classe principal `ProdutoApplication.java` como uma aplicação Spring Boot.

5. O backend estará rodando em `http://localhost:8080`.

### 3. Testando a API no Postman

Agora, você pode testar a API usando o Postman com os seguintes endpoints:

- **Criar Produto (POST)**: `http://localhost:8080/produtos`
    - Corpo da requisição (JSON):

        ```json
        {
          "nome": "Produto Exemplo",
          "preco": 100.0,
          "quantidade": 50,
          "descricao": "Descrição do Produto"
        }
        ```

- **Listar Produtos (GET)**: `http://localhost:8080/produtos`

- **Atualizar Produto (PUT)**: `http://localhost:8080/produtos/{id}`

- **Deletar Produto (DELETE)**: `http://localhost:8080/produtos/{id}`

### 4. Documentação com Swagger

Este projeto utiliza o Swagger UI para gerar e exibir a documentação da API automaticamente.

- **Acessar o Swagger UI**: 
   - Abra no navegador: `http://localhost:8080/swagger-ui.html`

- **OpenAPI JSON: Para acessar diretamente a especificação em formato JSON**: `http://localhost:8080/v3/api-docs`

A partir do Swagger UI, você pode explorar os endpoints, testar requisições e visualizar as definições da API.

## Frontend - React

### 1. Como Executar o Frontend

1. Navegue até o diretório do frontend (`frontend`).

2. Instale as dependências do projeto:
   
    ```bash
    npm install
    ```

3. Execute o servidor de desenvolvimento:
   
    ```bash
    npm start
    ```

4. O frontend estará disponível em `http://localhost:3000`.

### 2. Funcionalidades do Frontend

O frontend é uma interface simples que permite:

- Criar novos produtos.
- Listar produtos existentes.
- Atualizar produtos.
- Deletar produtos.

## Como Contribuir

1. Faça um fork deste repositório.
2. Crie uma nova branch para suas alterações.
3. Realize suas modificações e crie um pull request.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
