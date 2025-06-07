# Wallet Application

This project is a Spring Boot application that can be run using maven using the dependencies in docker compose file. 

## ✅ Prerequisites

Before getting started, make sure you have the following tools installed:

- [Java 17+](https://adoptopenjdk.net/)
- [Maven Wrapper (`mvnw`)](https://maven.apache.org/wrapper/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## 🚀 How to Run the Application

### 1. Start the dependencies

From the root project, start all containers (dependencies) using Docker Compose:

```
docker-compose up -d
```

### 2. Start the application

Run the following command from the project root to run the application using Spring Boot:

```
.\mvnw spring-boot:run 
```
 

### Stopping the containers

To stop the application and its dependencies, run:

```
docker-compose down
```

