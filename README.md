# Spring Trade Processing System

## Overview

The Spring Trade Processing System is a microservices-based application designed to handle various aspects of trade processing. It is built using Spring Boot and follows a modular architecture, with each service responsible for a specific domain. The system leverages Docker for containerization and includes monitoring and logging capabilities.

## Current Status

- The project is in the **development stage**.
- Implemented Key features such as
  - data streaming using kafka, authantication and authorization using JWT token,
  - api gatway for single entry point and token validation,
  - Fault tolerrance using circuit breaker pattern,
  - swagger implementation
  - trade cycle(trade creation (produced using kafka)-> consume it(in risk service)-> and save trade record in postgres DB(settlement service))
  - configuration with observability tools like Graphana, Promithious , Tempo and Loki
  - implemented CI workflow in GitHub Action for build image -> push to dockerHub repository on every commit.
- Ongoing work includes Spring Cloud Configs, Advance use of Spring JPA in FundService and spring batch
- Regular updates will be made as progress continues.

## Project Structure

The project is organized into the following modules:

- **api-gateway**: Acts as the entry point for all client requests, routing them to the appropriate services.
- **AuthService**: Handles authentication and authorization.
- **FundService**: Manages fund-related operations.
- **settlement-service**: Handles trade settlements.
- **trade-service**: Manages trade-related operations.

Additionally, the project includes:

- **data/tempo**: Configuration for distributed tracing.
- **docker**: Contains Docker configurations for Grafana, Prometheus, and Tempo.

## Prerequisites

- Java 17 or higher
- Maven 3.8+
- Docker and Docker Compose

## Getting Started

### Build and Run

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd spring-trade-processing-system
   ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Start the services using Docker Compose:
   ```bash
   docker-compose up
   ```

### Accessing the Application

- API Gateway: http://api-gateway
- Kafka UI: http://kafka-ui
- Grafana Dashboard: http://grafana
- Trade Service: http://trade-service
- Risk Service: http://risk-service
- Settlement Service: http://settlement-service
- Auth Service: http://auth-service

## Monitoring and Logging

The system uses Prometheus for metrics collection and Grafana for visualization. Tempo is used for distributed tracing.
