# Proyecto de Microservicios para Manejo de Transacciones

Este proyecto implementa una arquitectura basada en microservicios utilizando **Spring Boot**, con **Eureka** para el exposición de servicios y **Spring Cloud Gateway** para la gestión del enrutamiento.

## 🚀 Arquitectura del Proyecto

El sistema está compuesto por los siguientes módulos:

- 🗂️ **app-entities**: Contiene las entidades compartidas entre los microservicios.
- 🛳️ **app-father**: Implementa la configuración de **Eureka Server** y **Spring Cloud Gateway**.
- 👥 **app-person**: Expone los servicios relacionados con la gestión de clientes y reportes.
- 💰 **app-transaction**: Maneja los servicios relacionados con transacciones y cuentas.
- 📦 **container-dependencies**: Almacena las dependencias compartidas entre los microservicios para mantener consistencia.
- 🗄️ **database**: Contiene la configuración necesaria para la generación de la base de datos.

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Cloud (Eureka, Gateway, Config Server)**
- **JPA / Hibernate**
- **PostgreSQL / MySQL** (según configuración)
- **Docker & Docker Compose** (para contenedorización)

## 🌐 Contextos Principales

| 🏷️ Servicio       | 🌍 Contexto Base     | 📌 Descripción                    |
| ------------------ | ------------------- | --------------------------------- |
| 👥 app-person      | `/api/persons`      | Gestión de clientes y reportes    |
| 💰 app-transaction | `/api/transactions` | Manejo de transacciones y cuentas |

## 📜 Licencia

Este proyecto está bajo la licencia MIT. Para más información, revisa el archivo `LICENSE`.

---

💡 **Nota**: Si tienes dudas o encuentras algún problema, no dudes en abrir un **issue** o contactarnos.

