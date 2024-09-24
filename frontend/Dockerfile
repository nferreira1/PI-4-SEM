FROM node:18

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

EXPOSE 3000

# Comando para rodar a aplicação
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0"]