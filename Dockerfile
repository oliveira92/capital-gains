# Fase 1: Build
FROM openjdk:21-jdk AS build
WORKDIR /app

# Copia o código-fonte e as configurações do Gradle para a imagem
COPY . .

# Fase 2: Execução
FROM amazoncorretto:21-alpine

# Configura o diretório de trabalho
WORKDIR /app

# Copia o JAR criado na fase de build para a nova imagem
COPY --from=build /app/build/libs/*.jar app.jar

# Define o comando padrão para rodar a aplicação no modo CLI
ENTRYPOINT ["java", "-jar", "app.jar"]