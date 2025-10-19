# Ferretería - Instrucciones rápidas

Este README contiene instrucciones simples y directas para:
1. Clonar el proyecto
2. Crear la base de datos `ferreteria_database`
3. Crear el archivo `application.properties` en `src/main/resources` con los datos de la imagen

Además incluye una guía breve del flujo de trabajo con branches, commits y pull requests hacia `develop`.

---

## 1) Clonar el proyecto

- Por HTTPS:
  git clone https://github.com/matuxer/PractProf2Backend.git

---

## 2) Crear la base de datos `ferreteria_database`

Con MySQL (línea de comandos):

- Con usuario root:
  mysql -u root -p
  (te pedirá la contraseña)
  Dentro del prompt de MySQL:
  CREATE DATABASE ferreteria_database CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
  EXIT;

- O en una sola línea:
  mysql -u root -p -e "CREATE DATABASE ferreteria_database CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

Opcional: crear usuario y otorgar permisos (ajusta nombre/host/password):
  CREATE USER 'ferreteria'@'localhost' IDENTIFIED BY 'tu_password';
  GRANT ALL PRIVILEGES ON ferreteria_database.* TO 'ferreteria'@'localhost';
  FLUSH PRIVILEGES;

---

## 3) Crear `application.properties` en `src/main/resources`

Crea (o edita) el archivo:
- Ruta: `src/main/resources/application.properties`  
  (nota: si tu proyecto usa una ruta diferente, ajústala; la ruta correcta típica es `src/main/resources` — no `.src`)

Contenido de ejemplo basado en la imagen (ajusta usuario, contraseña y host según tu entorno):

server.port=8080

spring.jpa.database=MYSQL
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/ferreteria_database
spring.datasource.username=${MYSQL_USER:root}
spring.datasource.password=${MYSQL_PASSWORD:}

Explicación rápida:
- `${MYSQL_HOST:localhost}` usa la variable de entorno MYSQL_HOST si existe, si no usa `localhost`.
- `${MYSQL_USER:root}` y `${MYSQL_PASSWORD:}` hacen lo mismo para credenciales; reemplaza por valores reales o exporta las variables antes de arrancar la app:
  export MYSQL_HOST=localhost
  export MYSQL_USER=root
  export MYSQL_PASSWORD=tu_password

Si prefieres valores directos (menos seguro):
spring.datasource.url=jdbc:mysql://localhost:3306/ferreteria_database
spring.datasource.username=root
spring.datasource.password=tu_password

---

## Flujo de trabajo con branches, commits y Pull Requests (PR) — Guía simple

Objetivo: trabajar en ramas por tarea/código, hacer commits claros y enviar PR hacia `develop`.

1) Nombres de branch
- Formato recomendado: <tipo>/<CÓDIGO_TAREA>-descripción-corta
  - Ejemplos:
    - feat/FRT-123-login
    - fix/FRT-234-corregir-nullpointer
    - chore/FRT-345-actualizar-deps
- `CÓDIGO_TAREA` es el identificador de la tarea (p. ej. de Jira, Trello o del tracker interno). Usa ese código exactamente.

2) Crear y cambiar a la rama
  git checkout develop
  git pull origin develop
  git checkout -b feat/FRT-123-login

3) Hacer cambios y commits
- Haz cambios pequeños y coherentes por commit.
- Mensajes de commit recomendados:
  - Primera línea corta (máx ~72 caracteres): [FRT-123] Añade endpoint de login
  - Si hace falta, agregar cuerpo con más detalle.
- Comandos:
  git add .
  git commit -m "[FRT-123] Descripción corta del cambio"

4) Push de la rama al remoto
  git push -u origin feat/FRT-123-login

5) Abrir Pull Request (PR)
- Crea el PR desde tu branch hacia `develop`.
- Título: incluir el código de la tarea y descripción corta, p. ej. "[FRT-123] Implementa login".
- Descripción del PR:
  - Qué hace
  - Capturas o comandos para probar (si aplica)
  - Issues relacionados
- Asigna revisores según la política del equipo.

6) Revisiones y cambios
- Si piden cambios, realiza commits adicionales en la misma rama y pushea; el PR se actualizará automáticamente.
- Mantén la rama actualizada con develop:
  - Opción 1 (merge): git fetch origin && git merge origin/develop
  - Opción 2 (rebase, más limpio): git fetch origin && git rebase origin/develop
  - Resuelve conflictos localmente, commit/push, y continúa el PR.

7) Merge del PR
- Mergiar en `develop` cuando aprobado.
- Política común:
  - Squash and merge para mantener historial limpio (opcional)
  - O merge commit si prefieren historial completo
- Después del merge, borra la rama remota y local:
  git push origin --delete feat/FRT-123-login
  git branch -d feat/FRT-123-login

8) Buenas prácticas rápidas
- Commits atómicos: un cambio lógico por commit.
- Mensajes claros y con código de tarea.
- PRs pequeños y fáciles de revisar.
- Añadir pasos para reproducir / pruebas en la descripción del PR.

---

Si quieres, puedo:
- Generar el archivo `application.properties` listo para copiar/pegar.
- Crear un ejemplo de mensaje de commit/PR adaptado a una tarea real.
- Añadir instrucciones para Docker/MySQL en contenedor si trabajas con contenedores.

He preparado este README con los pasos básicos: clonación, creación de la base de datos, el archivo de propiedades con los valores mostrados en la imagen (adaptados a `ferreteria_database`) y una guía compacta del flujo de trabajo con branches, commits y PRs hacia `develop`. ¿Quieres que lo ponga en tu repo como README.md? ¿O que genere el archivo `application.properties` exacto listo para descargar?
