FROM node:18

WORKDIR /usr/src/app

COPY package.json package-lock.json ./

RUN sed -i '/"prepare":/d' package.json

RUN npm ci --force

COPY . .

RUN npm run build

EXPOSE 3000

CMD ["npm", "run", "start"]
