# Projeto Nuvem

Aplicação Spring Boot para gestão de instâncias virtuais e balanceamento de carga em um ambiente em nuvem.

## Tecnologias

- Java 19
- Spring Boot
- Spring Data JPA
- Spring Security
- H2 Database
- JWT

## Como executar

```bash
./mvnw spring-boot:run
```

## Testes

```bash
./mvnw test
```

## Principais rotas

- GET /balanceador - atribui a requisição para uma instância ativa
- POST /instancia-virtual/nova - cria uma nova instância virtual
- GET /instancia-virtual/todas - lista todas as instâncias
- GET /instancia-virtual/{id} - busca uma instância pelo ID
- PATCH /instancia-virtual/{id}/ativar - ativa uma instância
- PATCH /instancia-virtual/{id}/inativar - inativa uma instância
- POST /instancia-virtual/simular-elasticidade - simula o custo hora de elasticidade

## Estrutura

- control - controllers da API
- model - entidades e enums
- repository - acesso aos dados
- service - regras de negócio
- security - configuração de autenticação e JWT

## Observação

O projeto usa H2 em memória por padrão, então os dados são reiniciados quando a aplicação é encerrada.
