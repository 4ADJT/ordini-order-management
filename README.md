# Modo de execução da aplicação Spring Boot Ordini Order Management

## Requisitos
- Java 21 (JDK)
- Docker
- Plugin Docker Compose

### Recomendações

- IDE Intellij IDEA
- Cliente SQL (DBeaver, DataGrip, etc)

## Build

A execução do build é feita através do comando `./gradlew build` na raiz do projeto.

> [!IMPORTANT]
>
> Requer variáveis de ambiente para execução
> Use os exemplos abaixo do arquivo `.env` de desenvolvimento para definir as variáveis ambiente de produção.

### Desenvolvimento

Use o arquivo de tipo `.env` para definir as variáveis de ambiente necessárias para execução da aplicação.

```dotenv {.env}
DATASOURCE_USERNAME=postgres
DATASOURCE_PASSWORD=postgres
DATASOURCE_DATABASE=order
DATASOURCE_URL=jdbc:postgresql://postgresdb:5432/order

SERVER_PORT=8080
```

## Execução

Para executar a aplicação, basta executar o comando `docker-compose up -d` na raiz do projeto.
