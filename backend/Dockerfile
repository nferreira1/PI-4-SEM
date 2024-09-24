FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

RUN apk add --no-cache dos2unix

COPY . /app

RUN dos2unix ./mvnw && chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

COPY *.jar /app/backend.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/backend.jar", "-Dspring.profiles.active=prod"]
