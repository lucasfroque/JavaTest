# Teste de seleção para vaga de Java
Nesse projeto foi desenvolvido uma API para fazer consultas de frete.

## Informações 
- [Tecnologias utilizadas](#-tecnologias-utilizadas)
- [Rodando localmente](#rodando-localmente)
- [Documentação da API](#documentação-da-api)

Documentação da API usando swagger: https://sigabem-apitest.herokuapp.com/swagger-ui/index.html#/

Endpoint: https://sigabem-apitest.herokuapp.com/frete

A API está em um servidor gratis, talvez demore um pouco para carregar da primeira vez.

Caso não consiga usar por esse servidor recomendo rodar localmente.

## 🛠 Tecnologias utilizadas

- Java 17
- Maven
- Springboot
- Spring Data JPA
- H2 Database
- MySQL
- Swagger

## Rodando localmente

### Clone o projeto

```bash
  git clone https://github.com/lucasfroque/JavaTest
```
### Instale as dependências

```bash
  mvn clean install
```

### Crie um banco de dados
Crie um banco de dados com o nome "sigabem". 

Caso deseje usar outro nome altere em application.properties

### Inicie o servidor

```bash
  mvn spring-boot:run
```

## Documentação da API

#### Consulta o frete e salva no banco de dados

```http
  POST api/v1/users
```

| Parâmetro   | Tipo       |
| :---------- | :--------- | 
| `nomeDestinatario`      | `string` | 
| `cepOrigem`      | `string` | 
| `cepDestino`      | `string` |
| `peso`      | `double` | 


#### Lista todos os frete consultados

```http
  GET /frete
```