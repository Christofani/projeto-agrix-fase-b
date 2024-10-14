# Gerenciamento de Fazendas - Fase B

## Funcionalidades
- **Migração de Código:** O código da Fase A foi migrado para o novo projeto, mantendo todas as funcionalidades anteriores.
- **Testes de Unidade:** Foram escritos testes com cobertura mínima de 80% das linhas da classe `PersonService`.
- **Ajuste de Rotas com Datas:**
  - **POST /farms/{farmId}/crops:** Rota ajustada para aceitar campos com datas ao adicionar plantações.
  - **GET /farms/{farmId}/crops:** Rota ajustada para retornar plantações com base em campos de datas.
  - **GET /crops:** Rota ajustada para incluir campos de datas na listagem de plantações.
  - **GET /crops/{id}:** Rota ajustada para retornar informações detalhadas de uma plantação com base em campos de datas.
- **Nova Rota de Busca:**
  - **GET /crops/search:** Implementada para buscar plantações a partir da data de colheita.
- **Gerenciamento de Fertilizantes:**
  - **POST /fertilizers:** Criação de um novo fertilizante.
  - **GET /fertilizers:** Listagem de todos os fertilizantes cadastrados.
  - **GET /fertilizers/{id}:** Obtém informações detalhadas de um fertilizante.
  - **POST /crops/{cropId}/fertilizers/{fertilizerId}:** Associar uma plantação com um fertilizante.
  - **GET /crops/{cropId}/fertilizers:** Lista os fertilizantes associados a uma plantação.

## Objetivo
O projeto Gerenciamento de Fazendas - Fase B foi desenvolvido para expandir as funcionalidades do sistema de gerenciamento de fazendas e suas plantações, adicionando suporte para gerenciamento de fertilizantes e consultas baseadas em datas. O objetivo é aprofundar o conhecimento em desenvolvimento de APIs RESTful usando Spring Boot, enquanto se aprimora a qualidade do código com a implementação de testes.

## Stacks Utilizadas
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## Endpoints da API
1. **POST /farms:** Cria uma nova fazenda.
2. **GET /farms:** Lista todas as fazendas cadastradas.
3. **GET /farms/{id}:** Obtém informações detalhadas de uma fazenda pelo ID.
4. **POST /farms/{farmId}/crops:** Adiciona uma nova plantação a uma fazenda específica (com suporte a datas).
5. **GET /farms/{farmId}/crops:** Lista todas as plantações de uma fazenda (com suporte a datas).
6. **GET /crops:** Lista todas as plantações cadastradas (com suporte a datas).
7. **GET /crops/{id}:** Obtém informações detalhadas de uma plantação pelo ID (com suporte a datas).
8. **GET /crops/search:** Busca plantações a partir da data de colheita.
9. **POST /fertilizers:** Cria um novo fertilizante.
10. **GET /fertilizers:** Lista todos os fertilizantes cadastrados.
11. **GET /fertilizers/{id}:** Obtém informações detalhadas de um fertilizante.
12. **POST /crops/{cropId}/fertilizers/{fertilizerId}:** Associa uma plantação a um fertilizante.
13. **GET /crops/{cropId}/fertilizers:** Lista os fertilizantes associados a uma plantação.

## Como Utilizar
1. Clone este repositório para o seu ambiente local.
2. Certifique-se de ter o Java e o Maven instalados em seu sistema.
3. Navegue até o diretório raiz do projeto.
4. Execute o comando `mvn clean install` no terminal para compilar o projeto e instalar todas as dependências necessárias.
5. Após a instalação das dependências, execute o comando `mvn spring-boot:run` para iniciar a aplicação.
6. Utilize a API conforme os endpoints listados acima.

## Dockerização
Para criar uma imagem Docker da aplicação, siga estes passos:
1. Certifique-se de ter o Docker instalado em seu sistema.
2. Navegue até o diretório raiz do projeto.
3. Execute o comando `docker build -t farm-management-app .` para construir a imagem Docker.
4. Execute o comando `docker run -p 8080:8080 farm-management-app` para iniciar um contêiner com a aplicação.

## Aprendizados e Desenvolvimento
Durante o desenvolvimento desta fase do projeto, foram consolidadas habilidades em:

- **Testes de Unidade:** Criação de testes para garantir a qualidade do código e a cobertura adequada das classes.
- **Gerenciamento de Dados:** Implementação de rotas para manipular dados relacionados a plantações e fertilizantes.
- **Desenvolvimento de APIs RESTful:** Melhoria nas práticas de design e implementação de APIs utilizando Spring Boot.

## Agradecimentos
Obrigado por conferir o projeto Gerenciamento de Fazendas - Fase B. Este projeto foi desenvolvido para aprimorar conhecimentos em desenvolvimento de APIs com Spring Boot, incluindo a implementação de testes e novas funcionalidades para a gestão de plantações e fertilizantes. Qualquer dúvida ou sugestão é bem-vinda!

**Autor:** [Rodrigo Cesar Christofani Junior](https://github.com/Christofani)
