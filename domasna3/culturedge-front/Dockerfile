FROM node:21-alpine as builder

WORKDIR /app

RUN npm install -g @angular/cli

COPY package.json .

RUN npm install

COPY . .

RUN npm run build

FROM nginx:alpine

COPY --from=builder /app/dist/* /usr/share/nginx/html/
COPY /nginx.conf /etc/nginx/conf.d/default.conf

CMD ["nginx", "-g", "daemon off;"]