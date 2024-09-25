FROM node:18

WORKDIR /usr/src/app

COPY package*.json ./

ENV VITE_API_URL="http://191.232.170.198/"

RUN npm install

COPY . .

RUN npm run build

EXPOSE 3000

CMD ["npm", "run", "preview", "--", "--host", "0.0.0.0"]