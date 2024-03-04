# Utiliser l'image officielle Ubuntu 20.04
FROM ubuntu:20.04

# Mise à jour des packages et installation de Java 17 et Gradle
RUN apt-get update && apt-get install -y openjdk-17-jdk gradle

COPY . /work

# Définir le répertoire de travail dans le conteneur
WORKDIR /work

# Copier le fichier build.gradle.kts et les fichiers source Kotlin dans le conteneur
COPY build.gradle.kts .
COPY src src

# Exécuter la construction du projet avec Gradle
RUN ./gradlew build
#RUN mv /work/build/libs/*.jar /work/app.jar

# Exposer le port utilisé par l'application Kotlin
EXPOSE 8080

# Commande d'exécution de l'application Spring Boot
CMD ["java", "-jar", "build/libs/movieapp-0.0.1-SNAPSHOT.jar"]
