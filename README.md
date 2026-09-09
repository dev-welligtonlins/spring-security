# Faca & Bigode

## Tecnologias

* **Backend:** Java 21 + Spring Boot 4.0.3
* **Frontend:** Angular 21.2.0 + Node.js 20.20.1
* **Banco de dados:** PostgreSQL 16
* **Infraestrutura:** Docker, Docker Compose e Nginx

---

## 🚀 Executar localmente

### Backend

Linux/macOS:

```bash
cd back
./mvnw spring-boot:run
```

Windows:

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

### Rebuild dos backends

Após alterações no código Java:

```bash
docker compose build --no-cache backend-1 backend-2
docker compose up -d
```

### Logs dos backends

```bash
docker compose logs -f backend-1 backend-2
```

### Logs do Nginx

```bash
docker compose logs -f nginx
```

---

## ⚖️ Arquitetura Docker

```text
                    Angular
                       │
                       ▼
                  Nginx :80
                       │
              ┌────────┴────────┐
              ▼                 ▼
         Backend-1          Backend-2
              │                 │
              └────────┬────────┘
                       ▼
                  PostgreSQL
```

O **Nginx** distribui as requisições entre as duas instâncias do Spring Boot.

Cada instância possui um `INSTANCE_NAME` diferente para identificação nos logs.

---

## ⚠️ Banco de dados

Para recriar o banco de dados do zero:

```bash
docker compose down -v
docker compose up -d --build
```

> ⚠️ O comando `down -v` remove o volume do PostgreSQL e apaga os dados locais.
