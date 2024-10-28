# TechCommerce

O **TechCommerce** é um projeto da disciplina Projeto Integrador IV (PI) do curso de Tecnologia em Análise e Desenvolvimento de Sistemas do primeiro semestre, no Centro Universitário Senac - Santo Amaro visa, na medida do possível, promover a integração de conhecimentos das diversas disciplinas oferecidas em cada período correspondente a disciplina de PI. Dentro deste contexto, o projeto de PI IV tem por objetivo primário aprimorar nosso conhecimento sobre Web Services e Testes de Software, adquirido nas disciplinas de Desenvolvimento Web e Testes de Software, considerando também outras disciplinas cursadas no 4º período do curso.

Por fim, é relevante ressaltar que no decorrer do projeto, houve uma forte ênfase na aplicação dos elementos de desenvolvimento abordados nas disciplinas de Desenvolvimento de Sistemas Web, Arquitetura de Software e Testes de Software durante o 4º período, assim como na utilização da linguagem Java e Spring Framework.

## 📕 Sumário
  - [1. 🚀 Começando](#1--começando)
  - [2. 📋 Pré-requisitos](#2--pré-requisitos)
  - [3. 🔧 Instalação](#3--instalação)
     - [3.1 IntelliJ IDEA](#31-intellij-idea)
     - [3.2 Visual Studio Code](#32-visual-studio-code)
     - [3.3 Máquina Virtual](#33-máquina-virtual)
  - [4. 🛠️ Tecnologias](#4-%EF%B8%8F-ferramentas-e-tecnologias)
  - [5. 📌 Versão](#5--versão)
  - [6. ✒️ Autores](#6-%EF%B8%8F-autores)
  - [7. 🎁 Expressões de gratidão](#7--expressões-de-gratidão)
 

## 1. 🚀 Começando
Estas instruções permitirão que você faça uma cópia do projeto em sua própria máquina ou em uma VM para utilizá-lo e explorá-lo!


## 2. 📋 Pré-requisitos
- [Java SE Development Kit 17.0.7](https://www.oracle.com/br/java/technologies/downloads/#java17);
- [NodeJS 18.20.3](https://nodejs.org/en/download/package-manager)
- IDE de desenvolvimento Java, recomendamos o [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/) mas também é possivel usar o [Visual Studio Code](https://code.visualstudio.com/).


## 3. 🔧 Instalação
Uma série de exemplos passo-a-passo que informam o que você deve executar para ter um ambiente de desenvolvimento em execução.

### 3.1 IntelliJ IDEA:
- Instale a IDE IntelliJ IDEA, você pode baixa-lá no site da [JetBrains](https://www.jetbrains.com/pt-br/idea/).
- Faça a importação do projeto:
  - Clique em Open
  - Escolha o diretório onde foi feito download ou o clone do arquivo
  - Clique em OK
- Versão do JDK:
  - Clique em Settings
  - Project Structure
  - Em SDK, escolha a versão 17.0.7
  - Clique em OK
 
Agora você já pode rodar o programa no IntelliJ IDEA 😊.

### 3.2 Visual Studio Code:
- Instale o editor de texto Visual Studio Code, você pode baixa-lá no site da [Visual Studio Code](https://code.visualstudio.com/).
- Faça a importação do projeto:
  - Clique em File
  - Open Folder
  - Escolha o diretório onde foi feito download ou o clone do arquivo
  - Clique em OK
 
Agora você já pode rodar o programa no Visual Studio Code 😊.


### 3.3 Máquina Virtual:
- Configure o GitHub com as seguintes chaves secretas:
  - VM_IP (IP da Máquina Virtual);
  - SSH_USERNAME (Nome de usuáro para acessar a Máquina Virtual);
  - SSH_PASSWORD (Senha para acessar a Máquina Virtual);
  - SPRING_DATASOURCE_USERNAME (Nome de usuário em que fará a conexão com o banco de dados);
  - SPRING_DATASOURCE_URL (String de conexão do banco de dados);
  - SPRING_DATASOURCE_PASSWORD (Senha de usuário em que fará a conexão com o banco de dados);
  - API_URL (URL da API para efetuar as requisições no Front-End);
  - DIRECTORY_SERVER_IMAGES (Diretório da Máquina Virtual onde ficará salvo as imagens);
- Crie uma Máquina Virtual na [Azure](https://portal.azure.com/) ou na [AWS](https://aws.amazon.com/);
- Faça uma conexão SSH informando o nome de usuário e o IP da Máquina Virtual:
  ```bash
  ssh SSH_USERNAME@VP_IP
- Atualize todas as dependências do sistema:
  ```bash
  sudo apt update
  sudo apt upgrade
- Crie uma pasta no diretório raiz do servidor. O nome dessa pasta deve ser o mesmo da pasta pai definida na variável de ambiente `DIRECTORY_SERVER_IMAGES`. Isso garante consistência no caminho de armazenamento das imagens.
  ```bash
  sudo mkdir /techcommerce
- Crie uma sub pasta no diretório para fazer o upload das imagens do docker após o build, ela deve ter o nome de `docker-images`.
  ```bash
  sudo mkdir /techcommerce/docker-images
- Ajuste as permissões dessa pasta para permitir que qualquer usuário do sistema possa ler, escrever e executar arquivos dentro dela.
  ```bash
  sudo chmod 777 /techcommerce
- Instale o [Docker](https://www.docker.com/), você pode seguir o passo a passo pela documentação no [site oficial](https://docs.docker.com/engine/install/ubuntu/);
  - Crie uma rede para ter a comunicação entre o Nginx e o Front-End:
    ```bash
     docker network create nginx-frontend
  - Crie um arquivo de configuração para que o Nginx faça o proxy reverso, para a exibição do caminho das imagens:
    ```bash
     docker network create nginx-frontend
  - Crie o arquivo de configuração do Nginx:
    ```bash
     sudo mkdir -p /techcommerce/nginx-config
     sudo nano /techcommerce/nginx-config/default.conf
  	```
    - Escreva os seguintes dados:
      ```bash
      server {
        listen 80;
          server_name _;
      
          # Redireciona requisições para o servidor caseiro via Tailscale
          location /techcommerce/images/ {
              proxy_pass http://{VM_IP}{DIRECTORY_SERVER_IMAGES};  # IP da máquina virtual + o diretório pai onde ficará salvo as imagens
              proxy_set_header Host $host;
              proxy_set_header X-Real-IP $remote_addr;
              proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
              proxy_set_header X-Forwarded-Proto $scheme;
      
              # Habilitar a listagem de diretórios
              autoindex on;
              autoindex_exact_size off;
              autoindex_localtime on;
          }
      
          # Redireciona todas as outras requisições para o contêiner da aplicação
          location / {
              proxy_pass http://frontend-container:4173;  # Nome do contêiner da aplicação e a porta mapeada
              proxy_set_header Host $host;
              proxy_set_header X-Real-IP $remote_addr;
              proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
              proxy_set_header X-Forwarded-Proto $scheme;
          }
      }
  - Instale o [Nginx](https://nginx.org/en/) através do Docker:
    ```bash
     docker run -d --name nginx \
     -p 80:80 \
     --network nginx-frontend \
     -v /techcommerce/nginx-config/default.conf:/etc/nginx/conf.d/default.conf \
     nginx
- Basta apenas lançar uma nova release que ele irá automaticamente fazer a publicação na Máquina Virtual.

Agora você já pode rodar o programa na sua Máquina Virtual 😊.


## 4. 🛠️ Ferramentas e Tecnologias:
- [Java](https://www.java.com/pt-BR/)
- [Spring Framework](https://spring.io/projects/spring-framework)
- [NodeJS](https://nodejs.org/en/)
- [NextJS](https://nextjs.org/docs) 
- [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/)
- [Visual Studio Code](https://code.visualstudio.com/)
- [Git](https://git-scm.com/)
- [GitHub](https://github.com/)


## 5. 📌 Versão
Nós usamos o [Git](https://git-scm.com/) para controle de versão.


## 6. ✒️ Autores
* **Carlos Eduardo Martins dos Santos** - *Desenvolvimento Back-End e Testes de Sistema* - [Estagiário de Desenvolvimento](http://linkedin.com/in/carloseedu)
* **Murilo Vieira** - *Documentação* - [Programador I](https://www.linkedin.com/in/murilo-augusto-vieira-957aab202/)
* **Nathan Ferreira** - *Desenvolvimento Back-End, Desenvolvimento Front-End e Testes Unitários* - [Auxiliar de Gestão de Informática](https://www.linkedin.com/in/nathan-ferreira-97a355231/)
* **Otávio Augusto Reis Almeida** - *Desenvolvimento Back-End* - [Desenvolvedor C#](https://www.linkedin.com/in/otavio-augusto-a0a71b225/)
* **Thiago Nobre** - *Análise de Requisitos e Documentação* - [Atendente de SAC](https://www.linkedin.com/in/tiagonobre-/)
* **Vinicius Sales** - *Documentação* - [Estagiário em Planejamento B2C/VP Négocios](https://www.linkedin.com/in/vinisl/)

Você também pode ver a lista de todos os [colaboradores](https://github.com/nferreira1/PI-3-SEM/graphs/contributors) que participaram deste projeto.


## 7. 🎁 Expressões de gratidão
* Conte a outras pessoas sobre este projeto 📢;
* Convide alguém da equipe para uma cerveja 🍺;
* Um agradecimento publicamente 🫂;


---
Feito com ❤️ por [Nathan Ferreira](https://github.com/nferreira1).
