# Logística

## 1 - Como rodar o projeto:
  
### - Via Docker:  
- Rode o comando ``mvn clean package``. Obs: garanta que está com a versão do maven correta para o java 21.
- Rode o comando ``docker-compose up --build`` para subir o projeto. Obs: necessário ter o docker-compose instalado.

### - Via IDE:
- Altere no arquivo ``application.properties`` o atributo `spring.profiles.active=prod` para `spring.profiles.active=qa`
- Crie um database localmente com o nome `logistica` no postgresql.
- Rode a classe LogisticaApplication.
## 2 - Decisões de projeto:
 >  Foi decidido utilizar apenas lógica na aplicação sem utilizar mecanismos externos para focar na simplicidade do projeto, porém algumas melhorias poderiam serem implementadas como utilizar kafka para processar assíncronamente as inserções no banco evitar que a API travada para responder.
 
 > Outro mecanismo que poderiam ser utilizado seria o Spring Batch, mas visto que a quantidade de registros não é tão grande, não houve melhorias significativas na sua utilização, dados alguns testes que foram feitos.
 
 > Em um caso mais robusto onde uma das APIs fossem utilizadas mais vezes e tivesse uma alta taxa de requisições por minuto, valeria a separação da busca e do salvamento em servidores distintos(podendo até utilizar lambdas da aws para hospedar cada uma das APIs) para não sobrecarregar o servidor(Conceito de CQRS).
 
 > Devido ao escopo reduzido do projeto foi optado por utilizar uma arquitetura baseada em camadas para simplificar, porém ainda foram seguidos diversos padrões para garantir a manutenabilidade e evolução do código como por exemplo o SOLID.
 
 > Foi utilizado o flyway para executar as migrations de banco de dados e criar as tabelas devido a sua versatilidade de trabalhar com SQL.

 > Foi Criado um pipeline para o github actions para rodar o build, tests e gerar um coverage. O Coverage é gerado no codecov para poder consultar visualmente a cobertura de testes da aplicação.