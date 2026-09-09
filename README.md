# ✂️ Faca & Bigode

**Faca & Bigode** é um sistema **SaaS para gerenciamento de barbearias**, desenvolvido com arquitetura **Frontend + API REST + Banco de Dados**.

O projeto foi estruturado pensando em **escalabilidade, organização e separação de responsabilidades**, permitindo a execução de múltiplas instâncias do backend e o balanceamento das requisições através do **Nginx**.

A aplicação utiliza **Angular** no frontend, **Spring Boot** na API REST e **PostgreSQL** como banco de dados.

---

## 🧩 Tecnologias

<table>
  <tr>
    <td align="center" width="25%">
      <strong>☕ Backend</strong><br><br>
      Java 21<br>
      Spring Boot 4.0.3
    </td>
    <td align="center" width="25%">
      <strong>🅰️ Frontend</strong><br><br>
      Angular 21.2.0<br>
      Node.js 20.20.1
    </td>
    <td align="center" width="25%">
      <strong>🐘 Database</strong><br><br>
      PostgreSQL 16
    </td>
    <td align="center" width="25%">
      <strong>🐳 Infraestrutura</strong><br><br>
      Docker<br>
      Docker Compose<br>
      Nginx
    </td>
  </tr>
</table>

---

## 📁 Estrutura do projeto

```text
faca-bigode/
│
├── back/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   │
│   │   └── test/
│   │
│   ├── Dockerfile
│   ├── pom.xml
│   └── mvnw
│
├── front/
│   ├── src/
│   ├── public/
│   ├── angular.json
│   ├── package.json
│   ├── proxy.conf.json
│   └── Dockerfile
│
├── docker_postgres/
│   └── *.sql
│
├── nginx/
│   └── nginx.conf
│
├── docker-compose.yml
├── .env
└── README.md
```

### Organização

| Diretório            | Responsabilidade                       |
| -------------------- | -------------------------------------- |
| `back/`              | API REST desenvolvida com Spring Boot  |
| `front/`             | Aplicação web desenvolvida com Angular |
| `docker_postgres/`   | Scripts de inicialização do PostgreSQL |
| `nginx/`             | Configuração do Nginx e balanceamento  |
| `docker-compose.yml` | Orquestração dos containers            |
| `.env`               | Variáveis de ambiente                  |
| `README.md`          | Documentação do projeto                |

---

## ⚙️ Arquitetura

A aplicação utiliza o Nginx como ponto de entrada para as requisições da API.

```text
                         Browser
                            │
                            ▼
                       Angular :4200
                            │
                            ▼
                        Nginx :80
                            │
                  ┌─────────┴─────────┐
                  │                   │
                  ▼                   ▼
             Backend-1            Backend-2
             Spring Boot          Spring Boot
                  │                   │
                  └─────────┬─────────┘
                            │
                            ▼
                       PostgreSQL
                         :5432
```

O **Nginx distribui as requisições entre as duas instâncias do Spring Boot**, permitindo testar localmente uma arquitetura com múltiplos backends.

Cada instância possui um `INSTANCE_NAME` diferente para facilitar a identificação das requisições nos logs.

Exemplo:

```text
[backend-1] GET /auth/me
[backend-2] GET /services/me
[backend-1] POST /appointments
```

---

## 🚀 Executar localmente

### Backend

#### Linux / macOS

```bash
cd back
./mvnw spring-boot:run
```

#### Windows

```powershell
cd back
.\mvnw.cmd spring-boot:run
```

### Frontend

```bash
cd front
npm install
npm start
```

Acesse:

```text
http://localhost:4200
```

---

## 🐳 Executar com Docker

Na raiz do projeto:

```bash
docker compose up -d --build
```

### Ver containers

```bash
docker compose ps
```

### Ver logs

```bash
docker compose logs -f
```

### Parar containers

```bash
docker compose down
```

---

## 🔄 Rebuild dos backends

Após alterações no código Java:

```bash
docker compose build --no-cache backend-1 backend-2
docker compose up -d
```

---

## 📋 Logs

### Backend 1 e Backend 2

```bash
docker compose logs -f backend-1 backend-2
```

### Nginx

```bash
docker compose logs -f nginx
```

### PostgreSQL

```bash
docker compose logs -f postgres
```

---

## ⚖️ Balanceamento de carga

O Nginx utiliza um `upstream` para distribuir as requisições entre as instâncias do backend:

```nginx
upstream backend {
    server backend-1:8080;
    server backend-2:8080;
}
```

As requisições destinadas à API são encaminhadas pelo Nginx:

```text
/api/*
   │
   ▼
Nginx
   │
   ├──► backend-1:8080
   │
   └──► backend-2:8080
```

Isso permite simular localmente um ambiente com múltiplas instâncias da API.

---

## 🗄️ Banco de dados

O PostgreSQL é executado em um container separado e utiliza um volume Docker para persistência dos dados.

```text
Backend-1 ──┐
            │
            ├──► PostgreSQL :5432
            │
Backend-2 ──┘
```

Os scripts presentes em `docker_postgres/` são utilizados na inicialização do banco.

---

## ⚠️ Recriar o banco de dados

Para remover o banco local e recriá-lo do zero:

```bash
docker compose down -v
docker compose up -d --build
```

> ⚠️ **Atenção:** o parâmetro `-v` remove os volumes do Docker. Isso apagará os dados locais do PostgreSQL.

---

## 🔐 Variáveis de ambiente

As configurações sensíveis são mantidas em um arquivo `.env`.

Exemplo:

```env
POSTGRES_DB=auth_users
POSTGRES_USER=postgres
POSTGRES_PASSWORD=root
```

> ⚠️ Não versionar o arquivo `.env` contendo credenciais reais.

---

## 📌 Status do projeto

O projeto está em desenvolvimento, com foco na construção de uma arquitetura SaaS escalável para gerenciamento de barbearias.

Principais pilares da arquitetura:

* ✅ Frontend Angular
* ✅ API REST com Spring Boot
* ✅ PostgreSQL
* ✅ Docker
* ✅ Docker Compose
* ✅ Nginx
* ✅ Múltiplas instâncias do backend
* ✅ Balanceamento de carga
* ✅ Autenticação baseada em JWT
* ✅ Cookies HttpOnly
* ✅ Separação entre frontend e backend
* 🚧 Novos módulos e funcionalidades em desenvolvimento
