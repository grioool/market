FROM node:16.10-alpine AS build
WORKDIR frontend/src/app
COPY package.json package-lock.json ./
EXPOSE 4200
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.17.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /src/app/dist/frontend /usr/share/nginx/html

