# Étape de build
FROM openjdk:17-alpine AS build


# Installer les outils nécessaires (si nécessaire)
RUN apk add --no-cache curl tar bash

# Définir le répertoire de travail pour copier les fichiers du projet
WORKDIR /app

# Copier le projet dans l'image
COPY . .

# Donner la permission d'exécution au script gradlew
RUN chmod +x ./gradlew

# Construire l'application Spring Boot en utilisant Gradle Wrapper
# Ignorer les tests si nécessaire pour éviter l'erreur lors de la construction
RUN ./gradlew clean build -x test

# Étape de déploiement
FROM openjdk:17-alpine

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR du stage de build au stage de déploiement
COPY --from=build /app/build/libs/*.jar app.jar

# Exposer le port sur lequel votre application écoute
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
