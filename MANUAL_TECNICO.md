# 📘 Manual Técnico - Backend Ferretería

## 📋 Índice
1. [Introducción](#introducción)
2. [Arquitectura del Sistema](#arquitectura-del-sistema)
3. [Modelo de Datos](#modelo-de-datos)
4. [API Endpoints](#api-endpoints)
5. [Funcionalidades Implementadas](#funcionalidades-implementadas)
6. [Configuración y Despliegue](#configuración-y-despliegue)
7. [Seeders de Datos](#seeders-de-datos)

---

## 🎯 Introducción

Este manual técnico documenta la arquitectura, funcionalidades y endpoints de la API REST del sistema de ferretería. El backend está desarrollado con **Spring Boot 3.x**, **Java 17**, **MySQL 8.0** y sigue el patrón de arquitectura **MVC** (Model-View-Controller).

### Tecnologías Utilizadas
- **Framework:** Spring Boot 3.x
- **Lenguaje:** Java 17
- **Base de Datos:** MySQL 8.0
- **ORM:** Spring Data JPA / Hibernate
- **Seguridad:** Spring Security + BCrypt
- **Build Tool:** Maven
- **Arquitectura:** REST API

---

## 🏗️ Arquitectura del Sistema

### Patrón de Capas

```
┌─────────────────────────────────────┐
│         CONTROLLER LAYER            │  ← Recibe peticiones HTTP
│   (@RestController, @RequestMapping)│
└──────────────┬──────────────────────┘
               │
               ↓
┌─────────────────────────────────────┐
│            DAO LAYER                │  ← Lógica de negocio
│     (Data Access Objects)           │
└──────────────┬──────────────────────┘
               │
               ↓
┌─────────────────────────────────────┐
│        REPOSITORY LAYER             │  ← Acceso a datos
│     (JpaRepository interface)       │
└──────────────┬──────────────────────┘
               │
               ↓
┌─────────────────────────────────────┐
│          MODEL LAYER                │  ← Entidades JPA
│    (@Entity, @Table)                │
└──────────────┬──────────────────────┘
               │
               ↓
        ┌──────────────┐
        │   MySQL DB   │
        └──────────────┘
```

### Componentes Principales

#### 1. **Controllers** (`controller/`)
Reciben peticiones HTTP y retornan respuestas JSON.
- Usan anotaciones `@RestController` y `@RequestMapping`
- Delegan lógica de negocio a DAOs
- Manejan DTOs para evitar ciclos de serialización

#### 2. **DAOs** (`dao/`)
Data Access Objects - Capa de lógica de negocio.
- Contienen métodos de consulta y transformación
- Usan `@Component` o `@Repository`
- Interactúan con Repositories

#### 3. **Repositories** (`repository/`)
Interfaces que extienden `JpaRepository`.
- Proveen métodos CRUD automáticos
- Pueden definir queries personalizadas con `@Query`

#### 4. **Models** (`model/`)
Entidades JPA que mapean tablas de la base de datos.
- Usan `@Entity` y `@Table`
- Definen relaciones con `@OneToMany`, `@ManyToOne`, etc.

#### 5. **DTOs** (`dto/`)
Data Transfer Objects - Objetos para transferencia de datos.
- Evitan ciclos infinitos de serialización JSON
- Controlan qué datos se exponen al frontend
- Ejemplos: `FinalizarCompraRequest`, `ClientePerfilResponse`

#### 6. **Seeders** (`seeder/`)
Clases que populan la base de datos con datos iniciales.
- Se ejecutan al iniciar la aplicación
- Útiles para desarrollo y testing

---

## 💾 Modelo de Datos

### Diagrama de Entidades Principales

```
┌─────────────┐       ┌──────────────┐       ┌─────────────┐
│  Cliente    │──1:N──│   Compra     │──1:N──│    Item     │
└─────────────┘       └──────────────┘       └──────┬──────┘
      │                                             │
      │ 1:N                                         │ N:1
      │                                             │
      ↓                                             ↓
┌─────────────┐                             ┌─────────────┐
│  Feedback   │                             │  Producto   │
└──────┬──────┘                             └─────────────┘
       │ N:1                                       │ N:1
       │                                           │
       ↓                                           ↓
┌──────────────┐                           ┌──────────────┐
│ Especialista │                           │  Categoría   │
└──────┬───────┘                           └──────────────┘
       │ N:1
       │
       ↓
┌──────────────┐
│    Oficio    │
└──────────────┘
```

### Entidades y Relaciones

#### **Cliente**
- Tabla: `clientes`
- Campos clave: `id`, `nombre`, `apellido`, `correo`, `password`, `puntosRecompensa`
- Relaciones:
  - `1:N` con `Compra`
  - `1:N` con `Feedback` (feedbacks escritos)
  - `N:1` con `Localidad`, `Provincia`, `Pais`

#### **Compra**
- Tabla: `compra`
- Campos clave: `id`, `fechaCompra`, `descuento`, `total`
- Relaciones:
  - `N:1` con `Cliente`
  - `1:N` con `Item`

#### **Item**
- Tabla: `item`
- Campos clave: `id`, `cantidad`, `precio_total`
- Relaciones:
  - `N:1` con `Compra`
  - `N:1` con `Producto`

#### **Producto**
- Tabla: `productos`
- Campos clave: `id`, `nombre`, `descripcion`, `precio_unitario`, `stock`, `imgUrl`
- Relaciones:
  - `N:1` con `ProductoCategoria`
  - `1:N` con `Item`

#### **Especialista**
- Tabla: `especialistas`
- Campos clave: `id`, `nombre`, `apellido`, `puntuacion`, `disponibilidad`, `perfilImgUrl`
- Relaciones:
  - `N:1` con `Oficio`
  - `1:N` con `Feedback` (feedbacks recibidos)

#### **Feedback**
- Tabla: `feedbacks`
- Campos clave: `id`, `fecha`, `clasificacion` (1-5), `comentario`
- Relaciones:
  - `N:1` con `Cliente` (quien escribe)
  - `N:1` con `Especialista` (quien recibe)

---

## 🔌 API Endpoints

### Autenticación

#### `POST /auth/register`
Registra un nuevo cliente.

**Request:**
```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "correo": "juan@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "email": "juan@example.com"
}
```

#### `POST /auth/login`
Autentica un cliente.

**Request:**
```json
{
  "correo": "juan@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "email": "juan@example.com"
}
```

---

### Clientes

#### `GET /clientes`
Obtiene todos los clientes.

#### `GET /clientes/{id}`
Obtiene el perfil completo de un cliente incluyendo:
- Datos personales
- Lista de compras con items y productos
- Lista de feedbacks escritos con especialistas

**Response:**
```json
{
  "id": 1,
  "nombre": "Juan",
  "apellido": "Pérez",
  "correo": "juan@example.com",
  "puntosRecompensa": 150,
  "compras": [...],
  "feedbacksEscritos": [...]
}
```

#### `POST /clientes`
Crea un nuevo cliente.

#### `PUT /clientes/{id}`
Actualiza un cliente existente.

#### `DELETE /clientes/{id}`
Elimina un cliente.

---

### Productos

#### `GET /productos`
Obtiene productos con filtros opcionales.

**Query Params:**
- `categoria` (String) - Filtrar por categoría
- `min` (Float) - Precio mínimo
- `max` (Float) - Precio máximo
- `stock` (Boolean) - Solo con stock
- `nombre` (String) - Búsqueda parcial

**Ejemplo:** `/productos?categoria=electricidad&min=10&max=100&stock=true`

#### `GET /productos/{id}`
Obtiene un producto por ID.

#### `POST /productos`
Crea un nuevo producto.

#### `PUT /productos/{id}`
Actualiza un producto.

#### `DELETE /productos/{id}`
Elimina un producto.

---

### Compras

#### `GET /compra`
Obtiene todas las compras.

#### `GET /compra/{id}`
Obtiene una compra por ID.

#### `POST /compra/finalizar`
Finaliza una compra (carrito → orden).

**Request:**
```json
{
  "clienteId": 1,
  "descuento": 0.0,
  "items": [
    {
      "productoId": 45,
      "cantidad": 2,
      "precioUnitario": 15.99
    }
  ]
}
```

**Proceso:**
1. Valida cliente y productos
2. Verifica stock disponible
3. Crea registro de compra
4. Crea items asociados
5. Reduce stock de productos
6. Calcula total
7. **Agrega puntos aleatorios (1-5) al cliente**
8. Establece fecha actual
9. Retorna compra creada

---

### Especialistas

#### `GET /especialistas`
Obtiene especialistas con filtros opcionales.

**Query Params:**
- `oficio` (String) - Filtrar por oficio
- `disponibilidad` (Boolean) - Solo disponibles
- `puntuacion` (Integer) - Puntuación mínima
- `nombre` (String) - Búsqueda parcial

#### `GET /especialistas/{id}`
Obtiene un especialista con todos sus feedbacks.

**Response:**
```json
{
  "id": 1,
  "nombre": "Carlos",
  "apellido": "Gómez",
  "oficio": "Electricista",
  "puntuacion": 5,
  "disponibilidad": true,
  "perfilImgUrl": "http://...",
  "feedbacksRecibidos": [
    {
      "id": 10,
      "fecha": "2025-11-10",
      "clasificacion": 5,
      "comentario": "Excelente trabajo",
      "cliente": {
        "id": 1,
        "nombre": "Juan",
        "apellido": "Pérez"
      }
    }
  ]
}
```

#### `POST /especialistas`
Crea un nuevo especialista.

#### `PUT /especialistas/{id}`
Actualiza un especialista.

#### `DELETE /especialistas/{id}`
Elimina un especialista.

---

### Feedbacks

#### `GET /feedbacks`
Obtiene todos los feedbacks.

#### `GET /feedbacks/{id}`
Obtiene un feedback por ID.

#### `POST /feedbacks/crear`
Crea un nuevo feedback.

**Request:**
```json
{
  "clienteId": 1,
  "especialistaId": 5,
  "clasificacion": 5,
  "comentario": "Excelente servicio, muy profesional"
}
```

**Proceso:**
1. Valida cliente y especialista
2. Valida clasificación (1-5)
3. Crea feedback con fecha actual
4. **Recalcula puntuación promedio del especialista**
5. Actualiza especialista
6. Retorna feedback creado

---

### Otros Endpoints

#### Categorías
- `GET /productocategoria` - Lista todas las categorías
- `GET /productocategoria/{id}` - Obtiene una categoría
- `POST /productocategoria` - Crea categoría
- `PUT /productocategoria/{id}` - Actualiza categoría
- `DELETE /productocategoria/{id}` - Elimina categoría

#### Oficios
- `GET /oficios` - Lista todos los oficios
- `GET /oficios/{id}` - Obtiene un oficio
- `POST /oficios` - Crea oficio
- `PUT /oficios/{id}` - Actualiza oficio
- `DELETE /oficios/{id}` - Elimina oficio

#### Ubicaciones
- `GET /paises` - Lista países
- `GET /provincias` - Lista provincias
- `GET /localidades` - Lista localidades

---

## ⚙️ Funcionalidades Implementadas

### 1. Sistema de Compras
- ✅ Validación de stock en tiempo real
- ✅ Cálculo automático de totales y descuentos
- ✅ Actualización automática de stock
- ✅ Sistema de puntos de recompensa (1-5 puntos por compra)
- ✅ Registro de fecha de compra automático
- ✅ Relación completa con items y productos

### 2. Sistema de Feedbacks
- ✅ Clasificación de 1 a 5 estrellas
- ✅ Cálculo automático de puntuación promedio del especialista
- ✅ Registro de fecha automático
- ✅ Vinculación cliente-especialista
- ✅ Comentarios con límite de 255 caracteres

### 3. Filtrado Avanzado
- ✅ Productos: Por categoría, rango de precio, stock, nombre
- ✅ Especialistas: Por oficio, disponibilidad, puntuación, nombre
- ✅ Usa Specification API de JPA para queries dinámicas

### 4. Perfiles Completos
- ✅ Perfil de cliente con historial de compras y feedbacks
- ✅ Perfil de especialista con todos sus feedbacks recibidos
- ✅ DTOs para evitar ciclos de serialización JSON

### 5. Autenticación y Seguridad
- ✅ Hash de contraseñas con BCrypt
- ✅ JWT tokens para autenticación
- ✅ Endpoints de login y registro

### 6. Carga de Archivos
- ✅ Upload de imágenes de productos
- ✅ Upload de fotos de perfil de especialistas
- ✅ Almacenamiento en carpeta `uploads/`

---

## 🌱 Seeders de Datos

Los seeders populan la base de datos con datos iniciales para desarrollo y testing.

### Orden de Ejecución

1. **PaisSeeder** → Países
2. **ProvinciaSeeder** → Provincias
3. **LocalidadSeeder** → Localidades
4. **ProductoCategoriaSeeder** → Categorías de productos
5. **TipoServicioSeeder** → Tipos de servicios
6. **ProductoSeeder** → Productos
7. **OficioSeeder** → Oficios
8. **EspecialistaSeeder** → Especialistas
9. **ClienteSeeder** → 10 clientes
10. **FeedbackSeeder** → 3-7 feedbacks por especialista

### ClienteSeeder

Crea **10 clientes** con:
- Contraseña: `Test12345` (mismo hash BCrypt para todos)
- Hash: `$2a$10$vUxW7PtEuNi9E2dDnwedO.W4H2yQr1iW27hqJIELWj/32WWyenogq`
- Puntos de recompensa: 30-200
- Ubicación: Buenos Aires, Argentina

**Emails:**
- juan.perez@example.com
- maria.gonzalez@example.com
- carlos.rodriguez@example.com
- ana.martinez@example.com
- diego.lopez@example.com
- laura.fernandez@example.com
- roberto.sanchez@example.com
- sofia.romero@example.com
- martin.torres@example.com
- valentina.diaz@example.com

### FeedbackSeeder

Crea feedbacks realistas:
- **50%** → 5 estrellas ⭐⭐⭐⭐⭐
- **30%** → 4 estrellas ⭐⭐⭐⭐
- **15%** → 3 estrellas ⭐⭐⭐
- **5%** → 2 estrellas ⭐⭐

**Proceso:**
1. Genera 3-7 feedbacks por especialista
2. Asigna cliente aleatorio
3. Calcula clasificación ponderada
4. Selecciona comentario según clasificación
5. Asigna fecha aleatoria (últimos 60 días)
6. **Calcula y actualiza puntuación promedio del especialista**

---

## 🚀 Configuración y Despliegue

### Variables de Entorno

Crear archivo `application.properties`:

```properties
# Servidor
server.port=8080

# Base de Datos
spring.datasource.url=jdbc:mysql://localhost:3306/ferreteria_database
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database=MYSQL

# JWT
jwt.secret=${JWT_SECRET}
jwt.expiration=86400000

# File Upload
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

### Compilar para Producción

```bash
# Compilar JAR
./mvnw clean package -DskipTests

# El JAR estará en: target/ferreteria-0.0.1-SNAPSHOT.jar

# Ejecutar
java -jar target/ferreteria-0.0.1-SNAPSHOT.jar
```

### Docker (Opcional)

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/ferreteria-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

---

## 📊 Métricas y Monitoreo

### Spring Boot Actuator

Agregar en `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Endpoints disponibles:
- `/actuator/health` - Estado de la aplicación
- `/actuator/info` - Información de la app
- `/actuator/metrics` - Métricas

---

## 🔒 Seguridad

### Contraseñas
- Hasheadas con **BCrypt** (factor 10)
- Nunca se almacenan en texto plano
- El mismo hash puede validar la misma contraseña en cualquier sistema

### Tokens JWT
- Tiempo de expiración: 24 horas
- Secret key configurado en variables de entorno
- Incluyen email del usuario

### CORS
Configurado para permitir requests del frontend en desarrollo:
```java
@CrossOrigin(origins = "http://localhost:3000")
```

---

## 📝 Notas de Desarrollo

### Convenciones de Código
- **Nombres de clases:** PascalCase (`ProductoController`)
- **Nombres de métodos:** camelCase (`obtenerTodos`)
- **Constantes:** UPPER_SNAKE_CASE (`MAX_FILE_SIZE`)
- **Packages:** lowercase (`com.example.ferreteria`)

### Buenas Prácticas
- ✅ Usar DTOs para evitar exponer entidades directamente
- ✅ Validar datos de entrada en controllers
- ✅ Manejar excepciones con try-catch
- ✅ Usar ResponseEntity para respuestas HTTP
- ✅ Documentar endpoints complejos
- ✅ Usar transacciones para operaciones múltiples

---

## 📚 Referencias

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [MySQL Reference Manual](https://dev.mysql.com/doc/)
- [JWT.io](https://jwt.io/)
- [BCrypt Documentation](https://en.wikipedia.org/wiki/Bcrypt)

---

**Versión:** 1.0  
**Última actualización:** Noviembre 2025  
**Mantenido por:** Equipo Backend Ferretería
