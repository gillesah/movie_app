# Construire l'application
FROM node:latest
WORKDIR /app
COPY movie-front/package*.json ./
RUN npm install
COPY movie-front/ .
RUN npm run build

# Préparer Nginx pour servir l'application
# FROM nginx:stable-alpine as production-stage
# COPY --from=build-stage /app/dist /usr/share/nginx/html
# EXPOSE 80
# CMD ["nginx", "-g", "daemon off;"]
# Installer un serveur HTTP simple pour servir l'application
RUN npm install -g http-server

# Exposer le port sur lequel le serveur HTTP écoute
EXPOSE 80

# Servir l'application
CMD ["http-server", "dist", "-p", "80"]
