# Spring Boot + Oauth 2.0

## Getting started
### Prerequisites:
- Java 8
- Maven
- PostgreSQL

## Start Environment
This command start pgadmin4 and PostgreSQl.
```
docker compose up -d
```

## Swagger
![1.png](docs%2F1.png)

## Testing database schema
![database-schema](docs/db_schema.png)

### Postman
[spring-boot-oauth.postman_collection.json](src%2Ftest%2Fjava%2Fspring-boot-oauth.postman_collection.json)

### Authentication

```
curl -X POST \
  http://localhost:8080/oauth/token \
  -F grant_type=password \
  -F username=admin \
  -F password=admin1234 \
  -F client_id=spring-security-oauth2-read-write-client
```

### Accessing secured endpoints

```
curl -X GET \
  http://localhost:8080/secured/company/ \
  -H 'authorization: Bearer TOKEN_HERE'
```
