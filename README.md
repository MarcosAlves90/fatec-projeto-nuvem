# Projeto Nuvem

[![CI](https://img.shields.io/github/actions/workflow/status/MarcosAlves90/fatec-projeto-nuvem/ci.yml?branch=main)](https://github.com/MarcosAlves90/fatec-projeto-nuvem/actions/workflows/ci.yml)
![Java](https://img.shields.io/badge/Java-19-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-6DB33F?logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white)
![H2](https://img.shields.io/badge/H2-Database-1F6FEB)
![JWT](https://img.shields.io/badge/JWT-Auth-000000)
![Cache](https://img.shields.io/badge/Spring%20Cache-Enabled-6DB33F)

API Spring Boot para gestão de instâncias virtuais, balanceamento de carga, autenticação JWT e CRUD de usuários com cache.

## Visão geral

O projeto expõe uma API stateless protegida por JWT. As rotas de usuários, instâncias e balanceamento exigem autenticação, enquanto `/autenticacao/**` permanece pública para geração de tokens.

### Principais capacidades

- CRUD de usuários com cache de leitura
- Login com JWT e validade configurável em minutos
- Proteção de rotas via Spring Security
- Gestão de instâncias virtuais
- Ativação, inativação e simulação de custo hora
- Balanceamento simples entre instâncias ativas
- Base H2 em memória com dados iniciais

## Arquitetura

```mermaid
flowchart LR
    Client[Cliente HTTP] --> Auth[POST /autenticacao/login]
    Auth -->|JWT| Client
    Client -->|Authorization: Bearer token| Security[Spring Security + JWT Filter]
    Security --> Users[UsuarioController]
    Security --> Instances[InstanciaVirtualController]
    Security --> Balancer[BalanceadorController]

    Users --> UserService[UsuarioService]
    Users --> Cache[CachingService]
    UserService --> Repo[UsuarioRepository]
    Cache --> Repo
    Instances --> InstanceService[InstanciaVirtualService]
    Instances --> InstanceRepo[InstanciaVirtualRepository]
    Balancer --> BalanceService[BalanceadorService]
    BalanceService --> InstanceRepo
```

## Tecnologias

- Java 19
- Spring Boot 4.0.3
- Spring Web MVC
- Spring Data JPA
- Spring Security
- Spring Cache
- H2 Database
- JWT com `jjwt` 0.12.6

## Como executar

```bash
./mvnw spring-boot:run
```

## Acesso rápido

- Base URL local: `http://localhost:8080`
- Console H2: `http://localhost:8080/h2-console`
- Autenticação: `POST /autenticacao/login`

## Configuração

Não há variáveis de ambiente obrigatórias para subir a aplicação localmente. As principais configurações estão em `src/main/resources/application.properties` e podem ser sobrescritas se necessário.

| Propriedade | Valor atual | Finalidade |
| --- | --- | --- |
| `spring.application.name` | `projeto_nuvem` | Nome lógico da aplicação |
| `spring.datasource.url` | `jdbc:h2:mem:nuvem_db` | Banco H2 em memória |
| `spring.datasource.username` | `sa` | Usuário do banco H2 |
| `spring.datasource.password` | vazio | Senha do banco H2 |
| `spring.h2.console.enabled` | `true` | Habilita o console web do H2 |
| `spring.h2.console.path` | `/h2-console` | Caminho do console H2 |
| `spring.jpa.show-sql` | `true` | Exibe SQL no console |
| `spring.jpa.hibernate.ddl-auto` | `create` | Recria o schema ao subir a aplicação |
| `spring.jpa.database-platform` | `org.hibernate.dialect.H2Dialect` | Dialeto do banco |

## Testes

```bash
./mvnw test
```

## Banco de dados

### H2 em memória

- URL: `jdbc:h2:mem:nuvem_db`
- Console: `/h2-console`
- Usuário: `sa`
- Senha: vazia

### Seed inicial

O `import.sql` cria instâncias virtuais de exemplo e um usuário inicial:

- E-mail: `pessoa1@claudinho.com`
- Senha: `senha`
- Role: `USER`

## Autenticação

Gerar token:

```http
POST /autenticacao/login?username=...&password=...&duracao=15
```

- `duracao` é opcional e representa a validade do token em minutos
- A resposta é o JWT
- Nas demais requisições, envie:

```http
Authorization: Bearer <token>
```

### Exemplo com `curl`

```bash
curl -X POST "http://localhost:8080/autenticacao/login?username=pessoa1@claudinho.com&password=senha&duracao=15"
```

### Exemplo de resposta

```text
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwZXNzb2ExQGNsYXVkZGluaG8uY29tIiwiaWF0IjoxNzE3MDAwMDAwLCJleHAiOjE3MTcwMDA5MDB9....
```

## Rotas

### Autenticação

| Método | Rota | Descrição |
| --- | --- | --- |
| POST | `/autenticacao/login` | Gera token JWT |

### Usuários

| Método | Rota | Descrição |
| --- | --- | --- |
| GET | `/usuario/todos` | Lista todos os usuários |
| GET | `/usuario/{id}` | Busca um usuário por ID |
| POST | `/usuario/novo` | Cria um usuário |
| PUT | `/usuario/{id}/atualizar` | Atualiza um usuário |
| DELETE | `/usuario/{id}/remover` | Remove um usuário |

Exemplo autenticado:

```bash
curl -H "Authorization: Bearer <token>" \
  http://localhost:8080/usuario/todos
```

### Exemplo de resposta

```json
[
  {
    "email": "pessoa1@claudinho.com",
    "status": "ATIVO",
    "pessoaDTO": {
      "id": 1,
      "nome": "Pessoa 1",
      "emailAlternativo": "pessoa1@gmail.com"
    }
  }
]
```

### Instâncias virtuais

| Método | Rota | Descrição |
| --- | --- | --- |
| POST | `/instancia-virtual/nova` | Cria uma nova instância virtual |
| GET | `/instancia-virtual/todas` | Lista todas as instâncias |
| GET | `/instancia-virtual/{id}` | Busca uma instância por ID |
| PATCH | `/instancia-virtual/{id}/ativar` | Ativa uma instância |
| PATCH | `/instancia-virtual/{id}/inativar` | Inativa uma instância |
| POST | `/instancia-virtual/simular-elasticidade` | Calcula o custo hora de elasticidade |

Exemplo de simulação:

```bash
curl -X POST \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"virtualCpu":4,"armazenamentoGb":80,"memoriaRamGb":16}' \
  http://localhost:8080/instancia-virtual/simular-elasticidade
```

### Exemplo de resposta

```text
4.0
```

### Balanceamento

| Método | Rota | Descrição |
| --- | --- | --- |
| GET | `/balanceador` | Atribui a requisição para uma instância ativa |

Exemplo:

```bash
curl -H "Authorization: Bearer <token>" \
  http://localhost:8080/balanceador
```

### Exemplo de resposta

```text
A requisição/sessão foi atribuída para instância:
Nome: instancia-1
SO: LINUX_UBUNTU
Status: Ativa
Qtd de Requisições: 1
```

## Fluxo de uso

```mermaid
sequenceDiagram
    autonumber
    participant U as Usuário
    participant A as API
    participant S as Spring Security
    participant J as JWTUtil
    participant R as Repositórios

    U->>A: POST /autenticacao/login
    A->>S: autentica credenciais
    S->>R: carrega usuário por e-mail
    A->>J: gera JWT
    J-->>U: token
    U->>A: requisição com Bearer token
    A->>S: valida token
    S->>R: carrega usuário autenticado
    A-->>U: resposta da rota protegida
```

## Estrutura do projeto

- `control` - controllers da API
- `dto` - objetos de transferência
- `mapper` - conversão manual entre entidades e DTOs
- `model` - entidades e enums
- `repository` - acesso aos dados
- `security` - autenticação, JWT e configuração de segurança
- `service` - regras de negócio

## Observações

- O projeto usa H2 em memória, então os dados são recriados ao subir a aplicação.
- A listagem de usuários usa cache e as operações de alteração limpam o cache.
- Todas as rotas, exceto `/autenticacao/**`, exigem autenticação.
