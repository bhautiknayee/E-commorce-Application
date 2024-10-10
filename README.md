## E-Commerce Microservices Project

This project is a scalable e-commerce application built using **Spring Boot** and **Spring Cloud**. It consists of multiple services: **Customer**, **Product**, **Order**, and **Payment**. Each service handles its own domain but works together to provide a complete e-commerce experience.

### Key Features:
- **Configuration Server**: Centralized configuration management for all microservices.
- **Discovery Server (Eureka)**: Used for service registration and discovery, so services can easily find each other.
- **API Gateway**: Acts as a single entry point for clients to interact with various microservices.
- **Asynchronous Communication with Kafka**: Used for sending messages between services without waiting, ensuring non-blocking communication.
- **Synchronous Communication with OpenFeign and RestTemplate**: For direct service-to-service communication.
- **Distributed Tracing with Zipkin and Spring Actuator**: Helps track and visualize requests as they move across multiple services.
- **Security with Keycloak**: Secured the application using OAuth2 with Keycloak for authentication and authorization.
- **Infrastructure with Docker & Docker Compose**: All services are containerized and can be easily deployed using Docker Compose.

### Summary:
This e-commerce app leverages microservices to ensure scalability, flexibility, and resilience. It's built to handle high loads and can scale easily across various environments using **Docker** and **Kubernetes**.
