# Etapa de build
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

RUN apk add --no-cache dos2unix

COPY . /app

RUN dos2unix ./mvnw && chmod +x ./mvnw

# Executa o build do projeto e gera o JAR
RUN ./mvnw clean package -DskipTests

# Etapa final (runtime)
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia o JAR gerado na etapa de build para a imagem final
COPY --from=build /app/target/*.jar /app/backend.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/backend.jar", "-Dspring.profiles.active=prod"]
