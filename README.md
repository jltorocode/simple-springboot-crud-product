# 📦 simple-springboot-crud-product

Este proyecto es un microservicio REST construido con **Spring Boot 3.5** que expone una API para gestionar productos. Está conectado con el microservicio `simple-springboot-crud-company` mediante `RestTemplate`, lo que permite asociar productos a compañías.

---

## ⚙️ Tecnologías utilizadas

- Java 17
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- Maven
- Swagger (OpenAPI 3)
- H2 Database
- Lombok
- DTO Pattern
- Comunicación REST entre microservicios (RestTemplate)
- Validación con `javax.validation`

---

## 🔗 Relación con `simple-springboot-crud-company`

Este microservicio se conecta a `simple-springboot-crud-company` usando `RestTemplate` para:
- Obtener información de una compañía cuando se consulta un producto.
- Validar que la compañía exista antes de crear un producto.

Requiere que el servicio de compañías esté activo en:  
```
http://localhost:8081/api/companies/{id}
```

---

## 🚀 Endpoints disponibles (Product API)

Base URL: `http://localhost:8082/api/products`

| Método | Endpoint              | Descripción                      |
|--------|-----------------------|----------------------------------|
| GET    | `/`                   | Listar todos los productos       |
| GET    | `/{id}`               | Obtener un producto por ID       |
| POST   | `/`                   | Crear un nuevo producto          |
| PUT    | `/{id}`               | Actualizar un producto existente |
| DELETE | `/{id}`               | Eliminar un producto             |

---

## 📁 Estructura del Proyecto

```
src/
├── controller/        # Endpoints REST
├── dto/               # Objetos de transferencia (DTO)
├── entity/            # Modelo JPA
├── service/           # Lógica de negocio
├── repository/        # Persistencia (JpaRepository)
├── config/            # Beans globales (RestTemplate, CORS, etc)
└── exception/         # Manejador de errores
```

---

## 🔧 Configuración de conexión a CompanyService

`application.properties`:

```properties
server.port=8082
spring.datasource.url=jdbc:h2:mem=productdb
spring.jpa.hibernate.ddl-auto=update

# URL del microservicio de compañías
company.service.url=http://localhost:8081/api/companies
```

---

## 📄 Ejemplo de producto

```json
{
  "id": 1,
  "name": "Zapatos deportivos",
  "price": 29990,
  "companyId": 1
}
```

Cuando se consulta el producto, la respuesta incluirá los datos de la compañía obtenidos vía REST.

---

## 🧪 Swagger UI

Puedes acceder a la documentación Swagger en:

```
http://localhost:8082/swagger-ui/index.html
```

---

## 🧪 Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/simple-springboot-crud-product.git
   cd simple-springboot-crud-product
   ```

2. Compila:
   ```bash
   mvn clean install
   ```

3. Ejecuta:
   ```bash
   mvn spring-boot:run
   ```

> ⚠️ Asegúrate de tener corriendo `simple-springboot-crud-company` en `localhost:8081` antes de usar este servicio.

---

## 📚 Recursos útiles

- [Spring Boot Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Swagger UI](http://localhost:8082/swagger-ui/index.html)
- [H2 Console](http://localhost:8082/h2-console)  
  JDBC URL: `jdbc:h2:mem=productdb`

---

## 🪪 Licencia

MIT © 2025 — Este microservicio forma parte del ecosistema de Wuizio.
