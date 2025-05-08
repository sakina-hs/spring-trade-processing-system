
# Trade Processing System

The **Trade Processing System** is a microservices-based application designed to handle trade processing, risk management, fund management, notifications, and settlements. It leverages Spring Boot(security,MVC,Cloud,Batch,JPA) and Kafka for backend services and a modern frontend built with Vite and Tailwind CSS.

## Project Structure

The project is organized into multiple services, each responsible for a specific domain:

- **api-gateway**: Acts as the entry point for all client requests, routing them to the appropriate microservices.
- **AuthService**: Handles authentication and authorization for the system.
- **FundService**: Manages fund-related operations.
- **NotificationService**: Sends notifications to users about trade updates.
- **risk-service**: Evaluates and manages risks associated with trades.
- **settlement-service**: Handles the settlement of trades.
- **trade-service**: Processes trade requests and integrates with other services.
- **trade-frontend**: A modern web-based frontend for interacting with the system.

## Features

- **Microservices Architecture**: Each service is independently deployable and scalable.
- **Spring Boot**: Backend services are built using Spring Boot for rapid development and robust performance.
- **Apache Kafka**: For live data handling.
- **Frontend**: A responsive and user-friendly frontend built with Vite and Tailwind CSS.
- **Dockerized Deployment**: All services are containerized for easy deployment using Docker and Docker Compose.
- **Monitoring and Metrics**: Integrated with Grafana and Prometheus for monitoring and observability.

## Prerequisites

- **Java 17** or higher
- **Node.js** (for the frontend)
- **Docker** and **Docker Compose**
- **Maven** (for building backend services)

## Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd spring-trade-processing-system
```

### 2. Start the Application
Use Docker Compose to start all services:
```bash
docker-compose up --build
```

### 3. Access the Application
- **Frontend**: [http://localhost:5173]
- **API Gateway**: [http://localhost:9000]
- **Grafana Dashboard**: [http://localhost:3000]
- **Kafka UI**: [http://localhost:8086]

## Configuration

- **API Gateway**: Configure routes in `api-gateway/src/main/resources/application.properties`.
- **Database**: Update database configurations in each service's `application.properties` file.
- **Monitoring**: Grafana and Prometheus configurations are located in the docker directory.
