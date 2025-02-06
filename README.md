# Logística

## 📌 Visão Geral
Este projeto tem como objetivo oferecer uma solução simplificada para transformação de um arquivo legado de pedidos para entidades do banco padronizadas e busca de pedidos do usuário utilizando boas práticas de desenvolvimento.

---

## 🚀 Como Rodar o Projeto

### 🔹 Via Docker
1. Certifique-se de que possui a versão correta do **Maven** para o **Java 21**.
2. Execute o comando:
   ```sh
   mvn clean package
   ```
3. Suba o projeto com Docker Compose:
   ```sh
   docker-compose up --build
   ```
   > **Observação:** É necessário ter o **Docker Compose** instalado.

### 🔹 Via IDE
1. No arquivo `application.properties`, altere:
   ```properties
   spring.profiles.active=prod
   ```
   para:
   ```properties
   spring.profiles.active=qa
   ```
2. Crie um banco de dados localmente no **PostgreSQL** com o nome `logistica`.
3. Execute a classe **`LogisticaApplication`** para iniciar a aplicação.

---

## 🏗️ Decisões de Projeto

- O projeto foi desenvolvido priorizando **simplicidade**, evitando o uso de mecanismos externos complexos. No entanto, algumas melhorias futuras incluem:
    - **Uso do Kafka** para processar inserções no banco de forma assíncrona, evitando travamentos na API.
    - **Obs.:** Foi efetuado um teste com **Spring batch**, porém não apresentou ganhos significativos em performance, pois a massa de dados não era muito grande. Em um cenário onde o número de registro fosse maior valeria a implementação com spring batch para evitar a sobrecarga de dados no banco e dividir os processamentos em batchs.
- O banco de dados escolhido foi o **PostgreSQL**, por ser relacional e adequado ao modelo de entidades bem definidas e estáveis.

- Em cenários de alta demanda, a separação entre **leitura** e **escrita** em servidores distintos poderia ser implementada (**CQRS**), além do uso de **AWS Lambda** para hospedar APIs individualmente.

- Foi adotada uma **arquitetura em camadas**, mais simplificada, porém sem comprometer boas práticas do projeto como os princípios **SOLID** e alguns design patterns como o **Factory**.

- O **Flyway** foi utilizado para gestão de migrações do banco de dados, garantindo maior controle e rastreabilidade das alterações estruturais.

- Foi configurado um **pipeline no GitHub Actions**, que:
    - Executa o build da aplicação.
    - Roda os testes automatizados.
    - Gera relatórios de cobertura de testes via jacoco e envia para o **Codecov**.

- A documentação da API foi implementada utilizando **Swagger**, acessível em:
    - **[Swagger UI](http://localhost:8080/swagger-ui/index.html)**

---