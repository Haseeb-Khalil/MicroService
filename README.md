# Spring Cloud Microservices Demo

A multi-module Spring Cloud project that demonstrates the core building blocks of a microservice architecture: a service registry, an API gateway and two domain services that talk to each other through the registry.

## What this demonstrates

- Service discovery with **Netflix Eureka** (Spring Cloud Netflix).
- Edge routing with **Spring Cloud Gateway**.
- Two independently deployable Spring Boot services that register themselves with Eureka.
- Inter-service communication, where `user-service` calls `department-service` via a Value Object response template.
- Standard Spring Boot patterns: JPA persistence (H2 in-memory), REST controllers, layered architecture.

## Architecture

```
                     +-------------------------+
   client ---------> |   cloud-gateway         |  (Spring Cloud Gateway, port 9191)
                     +-------------+-----------+
                                   |  routes via Eureka
                +------------------+------------------+
                v                                     v
     +---------------------+                +-----------------------+
     |   user-service      | ----- REST --> |  department-service   |
     |   port 9002         |   inter-svc    |  port 9001            |
     |   (DepartmentUser)  |                |  (Department)         |
     +---------------------+                +-----------------------+
                ^                                     ^
                | registers with                      |
                +---------------+---------------------+
                                v
                  +---------------------------+
                  |  service-registry         |  (Netflix Eureka, port 8761)
                  +---------------------------+
```

## Modules

| Module | Port | Role |
| --- | --- | --- |
| `service-registry` | 8761 | Netflix Eureka server. All services register here. |
| `cloud-gateway` | 9191 | Spring Cloud Gateway. Single entry point routing to the right service via Eureka. |
| `department-service` | 9001 | Spring Boot REST service exposing `Department` resources, persisted in H2 with JPA. |
| `user-service` | 9002 | Spring Boot REST service exposing `DepartmentUser` resources. Calls `department-service` and returns a combined response via `ResponseTemplateVO`. |

## Tech stack

- Java 21 + Spring Boot 3.2.12
- Spring Cloud 2023.0.4
- Spring Cloud Gateway
- Netflix Eureka (server and clients)
- Spring Data JPA + H2 (in-memory)
- Maven
- Lombok

## Run it on your machine

### Prerequisites
- Java 21 or newer (`java -version`)
- No need to install Maven, the project includes the Maven wrapper in each module

### Steps

Open four terminals, one per module, and start them in this order:

```bash
git clone https://github.com/Haseeb-Khalil/MicroService.git
cd MicroService

# Terminal 1: Eureka registry
cd service-registry && ./mvnw spring-boot:run

# Terminal 2: Department service (wait until Eureka is up)
cd department-service && ./mvnw spring-boot:run

# Terminal 3: User service
cd user-service && ./mvnw spring-boot:run

# Terminal 4: Gateway
cd cloud-gateway && ./mvnw spring-boot:run
```

On Windows replace `./mvnw` with `mvnw.cmd`.

### Verify it's running

- Eureka dashboard: `http://localhost:8761` (you should see USER-SERVICE, DEPARTMENT-SERVICE, API-GATEWAY registered)
- Department service direct: `http://localhost:9001/departments/1`
- User service direct: `http://localhost:9002/users/1`
- Through the gateway: `http://localhost:9191/departments/1` and `http://localhost:9191/users/1`

## Why I built this

Hands-on practice for the microservice patterns I work with at Capgemini on the HMRC Enterprise Integration Services (EIS) programme: independently deployable Spring Boot services, service discovery and routing, and clean inter-service communication.
