# 🏪 Backend Ferretería - API REST con Spring Boot

Este es el backend del proyecto de ferretería, una **API REST** moderna hecha con **Spring Boot** y **MySQL** que maneja todos los datos y operaciones del negocio.

## 🎯 ¿Qué es una API REST?

Una **API REST** es como un "mesero digital" que recibe peticiones y devuelve información:

- El **frontend** (sitio web) le pide datos: *"Dame la lista de productos"*
- La **API** consulta la base de datos y responde: *"Aquí tienes 50 productos"*
- Todo se comunica usando **JSON** (formato de texto estructurado)

**¿Qué hace este backend?**
- ✅ **Gestiona productos** - Crear, leer, actualizar, eliminar
- ✅ **Maneja usuarios** - Registro, login, perfiles
- ✅ **Controla inventario** - Stock, categorías, precios
- ✅ **Procesa pedidos** - Carritos, órdenes, pagos
- ✅ **Base de datos** - Guarda todo en MySQL

---

## 🚀 Instalación y Uso Rápido

### 1. Requisitos previos
- **Java 11 o superior** ([Descargar Oracle JDK](https://www.oracle.com/java/technologies/downloads/) o [OpenJDK](https://openjdk.org/))
- **MySQL 8.0+** ([Descargar MySQL](https://dev.mysql.com/downloads/mysql/))
- **Git** (opcional, para colaborar)
- **IDE recomendado:** [IntelliJ IDEA](https://www.jetbrains.com/idea/) o [VS Code](https://code.visualstudio.com)

### 2. Clonar el proyecto
```bash
# Por HTTPS (recomendado)
git clone https://github.com/matuxer/PractProf2Backend.git

# Entrar al directorio
cd PractProf2Backend
```

### 3. Configurar la base de datos

**Opción A: Con MySQL Workbench (Interfaz gráfica)**
1. Abrir MySQL Workbench
2. Conectar a tu servidor local
3. Ejecutar: `CREATE DATABASE ferreteria_database;`

**Opción B: Por línea de comandos**
```bash
# Conectar a MySQL
mysql -u root -p

# Crear la base de datos
CREATE DATABASE ferreteria_database CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# Salir
EXIT;
```

### 4. Configurar la conexión a la base de datos

Crear el archivo `src/main/resources/application.properties`:

```properties
# Puerto del servidor (donde escucha la API)
server.port=8080

# Configuración de JPA/Hibernate
spring.jpa.database=MYSQL
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

# Conexión a MySQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/ferreteria_database
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD_AQUI
```

**🔧 Personalizar la configuración:**
- Cambia `TU_PASSWORD_AQUI` por tu contraseña real de MySQL
- Si MySQL está en otro puerto, cambia `3306`
- Si usas otro usuario, cambia `root`

### 5. Ejecutar el proyecto

**Opción A: Con tu IDE**
1. Importar el proyecto como "Maven Project"
2. Ejecutar la clase `FerreteriaApplication.java`

**Opción B: Por línea de comandos**
```bash
# Windows
./mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

🎉 **¡Ya tienes la API funcionando en http://localhost:8080!**

---

## 📂 Estructura del Proyecto (Fácil de Entender)

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/ferreteria/    # 📝 Código Java
│   │   │   ├── controller/                 # 🎮 Controladores (reciben peticiones)
│   │   │   ├── dao/                        # 💾 DAOs (Data Access Objects - acceso a datos)
│   │   │   ├── repository/                 # �️ Repositorios (JPA - acceso a base de datos)
│   │   │   ├── model/                      # 📋 Entidades (tablas de BD)
│   │   │   └── FerreteriaApplication.java  # 🚀 Clase principal
│   │   └── resources/
│   │       ├── application.properties      # ⚙️ Configuración
│   │       └── application.properties.template # 📄 Plantilla de config
│   └── test/                              # 🧪 Pruebas unitarias
├── target/                                # 📦 Archivos compilados
├── pom.xml                               # 📋 Dependencias de Maven
└── README.md                             # 📖 Este archivo
```

**Dónde trabajar:**
- ✅ **`src/main/java/`** - Tu código Java
- ✅ **`src/main/resources/`** - Configuración
- ✅ **`src/test/`** - Pruebas
- ❌ **`target/`** - NO tocar (se genera automáticamente)

---

## 🛠️ Comandos Útiles

### Para Desarrollo (día a día)
```bash
# Ejecutar la aplicación
./mvnw spring-boot:run

# Ejecutar solo las pruebas
./mvnw test

# Compilar sin ejecutar
./mvnw compile

# Limpiar archivos compilados
./mvnw clean
```

### Para Producción
```bash
# Compilar JAR para producción
./mvnw clean package

# Ejecutar el JAR generado
java -jar target/ferreteria-0.0.1-SNAPSHOT.jar
```

---

## 🔧 Cómo Hacer Cambios

### 1. Añadir un nuevo endpoint (ruta de API)
```java
@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    
    @Autowired
    private ProductoDAO productoDAO;
    
    @GetMapping
    public List<Producto> obtenerTodos() {
        // Usar el DAO para obtener datos
        return productoDAO.obtenerTodos();
    }
    
    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoDAO.guardar(producto);
    }
}
```

### 2. Crear un nuevo DAO (Data Access Object)
```java
@Component
public class ProductoDAO {
    
    @Autowired
    private ProductoRepository repository;
    
    public List<Producto> obtenerTodos() {
        return repository.findAll();
    }
    
    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }
    
    public Optional<Producto> buscarPorId(Long id) {
        return repository.findById(id);
    }
    
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
```

### 3. Crear una nueva entidad (tabla de BD)
```java
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private Double precio;
    
    // Getters y setters...
}
```

### 4. Modificar configuración
- Editar `src/main/resources/application.properties`
- Los cambios se aplican al reiniciar la aplicación

---

## 🔄 Flujo de Trabajo con Git (Para Equipos)

### Estructura de Branches
- **`main`** - Rama principal **PROTEGIDA** (solo administradores)
- **`develop`** - Rama de desarrollo (aquí van todos los Pull Requests)
- **Ramas de tareas** - Una rama por cada tarea específica

### Convención de Nombres de Tareas
Las tareas siguen este formato: **FERRB-XX** (FERRETERIA BACKEND N°XX)

Ejemplos:
- `FERRB-18` - Tarea #18 del backend de ferretería
- `FERRB-25` - Tarea #25 del backend de ferretería

### 🚀 Cómo Trabajar en una Tarea

**1. Crear rama desde develop:**
```bash
git checkout develop
git pull origin develop
git checkout -b feat/FERRB-18-api-productos
```

**2. Hacer cambios y commits:**
```bash
# Hacer tus cambios en el código
git add .
git commit -m "[FERRB-18] Añade CRUD completo para productos"
```

**3. Subir y crear Pull Request:**
```bash
git push -u origin feat/FERRB-18-api-productos
```
Luego crear PR hacia **`develop`** (NO hacia `main`)

**4. Después del merge, limpiar:**
```bash
git checkout develop
git pull origin develop
git branch -d feat/FERRB-18-api-productos
```

### 📋 Convenciones de Commits
```bash
git commit -m "[FERRB-XX] Descripción clara de lo que hace"
```

**Ejemplos:**
- `[FERRB-18] Añade endpoints CRUD para productos`
- `[FERRB-19] Corrige validación de datos en usuarios`
- `[FERRB-20] Actualiza configuración de base de datos`

**Tipos de cambios:**
- **feat/** - Nueva funcionalidad
- **fix/** - Corrección de errores
- **chore/** - Tareas de mantenimiento
- **docs/** - Documentación

---

## 🧪 Testing (Pruebas)

```bash
# Ejecutar todas las pruebas
./mvnw test

# Ejecutar con detalles
./mvnw test -Dspring.profiles.active=test
```

**Ubicación de pruebas:** `src/test/java/com/example/ferreteria/`

---

## 🆘 Problemas Comunes

**❌ "java: command not found"**
- **Solución:** Instala Java y configura JAVA_HOME
- **Verificar:** `java -version` debe mostrar Java 11+

**❌ "Could not create connection to database"**
- **Solución:** Verifica que MySQL esté corriendo
- **Verificar:** 
  - MySQL service activo
  - Credenciales correctas en `application.properties`
  - Base de datos `ferreteria_database` existe

**❌ "Port 8080 was already in use"**
- **Solución:** Cambia el puerto en `application.properties`:
  ```properties
  server.port=8081
  ```

**❌ "Maven dependencies not found"**
- **Solución:** 
  ```bash
  ./mvnw clean install
  ```

**❌ "Application failed to start"**
- **Solución:** Revisa los logs en la consola
- **Común:** Error de configuración de base de datos

---

## 📚 Recursos Útiles

- **Spring Boot Docs:** [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
- **Spring Data JPA:** [https://spring.io/projects/spring-data-jpa](https://spring.io/projects/spring-data-jpa)
- **MySQL Docs:** [https://dev.mysql.com/doc/](https://dev.mysql.com/doc/)
- **REST API Best Practices:** [https://restfulapi.net/](https://restfulapi.net/)

---

**¡Listo para crear una API REST increíble! 🚀**
