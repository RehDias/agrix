# Agrix - Gestão de Fazendas
Este projeto foi desenvolvido como parte do aprendizado no curso de Java da Trybe, com foco na construção de APIs utilizando o ecossistema Spring Boot, com as boas práticas de arquitetura, persistência de dados, testes e segurança.

O Agrix é o primeiro produto da empresa fictícia AgroTech, a missão é usar tecnologia para otimizar o cultivo de plantações, reduzindo desperdícios e promovendo o uso mais consciente da terra.

## Descrição do Projeto
O sistema Agrix é uma aplicação web dividida em fases de desenvolvimento, com funcionalidades incrementais. Seu objetivo principal é permitir a gestão e monitoramento de fazendas, plantações e fertilizantes, além de garantir segurança e autenticação de usuários.

O projeto é composto pelas seguintes fases:

## Fase A - Funcionalidades Iniciais
Objetivo: Criar a estrutura básica da API para cadastro e consulta de fazendas e plantações.

Funcionalidades:

- POST /farms: Cadastro de uma nova fazenda

- GET /farms: Listagem de todas as fazendas

- GET /farms/{id}: Consulta de uma fazenda específica

- POST /farms/{farmId}/crops: Cadastro de plantações vinculadas a uma fazenda

- GET /farms/{farmId}/crops: Listagem de plantações de uma fazenda

- GET /crops: Listagem geral de plantações

- GET /crops/{id}: Consulta de uma plantação específica

- Dockerfile multi-stage para deploy com Docker

## Fase B - Ampliação e Novas Funcionalidades
Objetivo: Adicionar novas entidades, funcionalidades com datas e buscas customizadas.

Funcionalidades:

- Uso de campos de data para cadastro e consulta de plantações

- GET /crops/search: Busca de plantações pela data de colheita

- POST /fertilizers: Cadastro de fertilizantes

- GET /fertilizers: Listagem geral de fertilizantes

- GET /fertilizers/{id}: Consulta de fertilizante específico

- POST /crops/{cropId}/fertilizers/{fertilizerId}: Associar fertilizante à plantação

- GET /crops/{cropId}/fertilizers: Listagem de fertilizantes usados em uma plantação

## Fase C - Autenticação e Segurança
Objetivo: Proteger as rotas da aplicação usando autenticação com JWT e autorização por perfis.

Funcionalidades:

- POST /persons: Cadastro de pessoas no sistema

- Autenticação com JWT (/login)

- Controle de acesso com roles para as rotas:

  - GET /farms

  - GET /crops

  - GET /fertilizers

## Tecnologias Utilizadas
- Java 17

- Spring Boot

- Spring Data JPA

- Spring Security

- JWT (JSON Web Token)

- H2 / PostgreSQL

- JUnit e Mockito

- Docker

## Como rodar o projeto
Clone o repositório:

```bash
git clone https://github.com/seu-usuario/agrix.git
cd agrix
```

Compile e rode o projeto:

```bash
./mvnw spring-boot:run
```

(Opcional) Rode com Docker:

```bash
docker build -t agrix .
docker run -p 8080:8080 agrix
```

Acesse a API em: http://localhost:8080

## Autoria
Projeto desenvolvido como parte do curso de Java com Spring da Trybe.

