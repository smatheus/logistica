# Etapa 1: Utiliza uma imagem base do Java
FROM openjdk:21-jdk-slim as build

# Diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo JAR gerado pelo Spring Boot para o contêiner
COPY target/*.jar app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Expõe a porta 8080 (ou qualquer que seja a porta configurada no seu Spring Boot)
EXPOSE 8080