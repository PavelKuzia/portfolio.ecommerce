ARG NODE_VERSION=22.14.0-alpine
ARG NGINX_VERSION=alpine3.21

FROM node:${NODE_VERSION} AS build

WORKDIR /app

COPY ./angular.frontend/package.json ./angular.frontend/package-lock.json ./

RUN npm ci

COPY . .

# RUN npm install -g @angular/cli@19.0.7
RUN npm run build --configuration production


FROM nginx:${NGINX_VERSION}
EXPOSE 80
COPY ./angular.frontend/nginx/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/angular-ecommerce/browser /usr/share/nginx/html