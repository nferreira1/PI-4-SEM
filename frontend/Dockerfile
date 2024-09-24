FROM node:18

WORKDIR /usr/src/app

COPY package*.json ./

ARG VITE_API_URL

ENV VITE_API_URL=${VITE_API_URL}

RUN npm install

COPY . .

RUN npm run build

EXPOSE 3000

CMD ["npm", "run", "preview", "--", "--host", "0.0.0.0"]