# AI_DAS_BE

Backend service for AI DAS. Java 17, Spring Boot, MySQL (JPA), MongoDB, Apache Kafka.

## Stack

| Concern | Technology |
| --- | --- |
| Runtime | Java 17 |
| Framework | Spring Boot (Web MVC, Validation, Actuator) |
| Relational store | MySQL via Spring Data JPA |
| Document store | MongoDB via Spring Data MongoDB |
| Messaging | Apache Kafka via Spring Kafka |
| Build | Maven (`./mvnw`) |

## Run locally

Start the infrastructure dependencies, then the service:

```bash
docker compose up -d
./mvnw spring-boot:run
```

The API listens on `http://localhost:8080`.

## Configuration

All settings are environment-overridable (see `src/main/resources/application.yml`):

| Variable | Default |
| --- | --- |
| `SERVER_PORT` | `8080` |
| `MYSQL_URL` | `jdbc:mysql://localhost:3306/ai_das?...` |
| `MYSQL_USER` / `MYSQL_PASSWORD` | `ai_das` / `ai_das` |
| `MONGODB_URI` | `mongodb://localhost:27017/ai_das` |
| `KAFKA_BOOTSTRAP_SERVERS` | `localhost:9092` |
| `KAFKA_EVENTS_TOPIC` | `ai-das.events` |
| `CORS_ALLOWED_ORIGINS` | `http://localhost:5173` |

## Endpoints

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/actuator/health` | Health check |
| `GET` | `/api/v1/events` | List events stored in MySQL |
| `POST` | `/api/v1/events` | Persist an event in MySQL and publish it to Kafka |

Events published to Kafka are consumed by `EventConsumer` and archived in MongoDB, which
exercises the full MySQL -> Kafka -> MongoDB path end to end.

## Tests

```bash
./mvnw test
```

Tests are slice tests and do not require MySQL, MongoDB, or Kafka to be running.
