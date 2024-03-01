# Utiliser une image de base pour le JDK
FROM openjdk:11-jdk-slim as build

# Copier les sources dans l'image
COPY . /app

WORKDIR /app

# Compiler le projet
RUN ./gradlew build

# Exécuter l'application
FROM openjdk:11-jre-slim
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]