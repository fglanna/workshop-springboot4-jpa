# Estágio 1: Build (Compilação) usando o JDK 25 oficial
FROM eclipse-temurin:25-jdk-noble AS build
WORKDIR /app

# Instala o Maven dentro do container
RUN apt-get update && apt-get install -y maven

# Copia os arquivos e compila o projeto do curso
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Execução (Runtime) leve apenas com o JRE 25
FROM eclipse-temurin:25-jre-noble
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]