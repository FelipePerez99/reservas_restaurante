# Sistema de Reservas para Restaurantes

Este proyecto implementa un sistema de reservas para restaurantes utilizando el framework Spring en Java y una base de datos PostgreSQL. El sistema permite a los usuarios realizar reservas de mesas, explorar información detallada sobre restaurantes y gestionar eficientemente los recursos del establecimiento.

# Características Principales

**1. Usuario**
   - Registro seguro y autenticación mediante tokens JWT.
   - Actualización de perfiles y gestión de información personal.

**2. Restaurante**
   - Creación y gestión detallada de información sobre restaurantes.
   - Asociación con mesas y horarios disponibles.

**3. Mesa**
   - Creación y gestión de mesas en un restaurante.
   - Asociación con capacidad y restaurante específico.

**4. Horario**
   - Definición de horarios de operación para el restaurante.
   - Asociación con un restaurante específico.

**5. Reserva**
   - Creación de reservas para usuarios registrados.
   - Asociación con mesas y restaurantes específicos.
   - Búsqueda y cancelación de reservas.

**6. Seguridad**
   - Autenticación segura mediante tokens JWT.
   - Privacidad de datos y protección de la información del usuario.
     
# Tecnologías Utilizadas
- **Java y Spring Framework**
   - Desarrollo del backend utilizando Java y el framework Spring para facilitar la creación de aplicaciones Java empresariales.
     
- **Spring Boot**
   - Implementación de la aplicación como una aplicación Spring Boot para simplificar la configuración y el despliegue.
     
- **Spring Data JPA**
   - Utilización de Spring Data JPA para la capa de acceso a datos, facilitando la interacción con la base de datos PostgreSQL.
  
- **PostgreSQL**
   - Base de datos relacional para el almacenamiento persistente de la información del sistema de reservas.

- **JSON Web Tokens (JWT)**
   - Empleo de tokens JWT para la autenticación segura de usuarios y la gestión de sesiones.

- **Spring Security**
   - Integración de Spring Security para implementar medidas de seguridad robustas, incluyendo control de acceso basado en roles.
     
- **Maven**
   - Gestión de dependencias y construcción del proyecto mediante Maven para simplificar la administración de bibliotecas y la compilación.
     
- **IntelliJ IDEA**
   - Entorno de desarrollo integrado (IDE) utilizado para la codificación, depuración y construcción del proyecto.
