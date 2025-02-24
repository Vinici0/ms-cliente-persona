
# Microservicio de Clientes

Este microservicio gestiona la información de clientes, construido con Spring Boot y PostgreSQL.

## Requisitos Previos

- Java 17
- Maven 3.8.7+
- Docker y Docker Compose
- Git

## Configuración del Entorno

1. Clonar el repositorio:
```bash
git clone [URL_DEL_REPOSITORIO]
cd ms-cliente-persona
```

2. Configurar variables de entorno:
   - Crear archivo `.env` en la raíz del proyecto con las siguientes variables:
```properties
SPRING_APPLICATION_NAME=
SERVER_PORT=
API_PREFIX=

# Database Configuration
DB_NAME=
DB_USERNAME=
DB_PASSWORD=
DB_PORT=
```

## Ejecutar la Aplicación

### Usando Docker Compose (Recomendado)

1. Construir y levantar los contenedores:
```bash
docker-compose up --build
```

2. Para detener los contenedores:
```bash
docker-compose down
```

### Ejecución Local (Sin Docker)

1. Compilar el proyecto:
```bash
mvn clean package
```

2. Ejecutar la aplicación:
```bash
java -jar target/app.jar
```

## Endpoints

La API estará disponible en: `http://localhost:9090`

### Documentación API

- Swagger UI: `http://localhost:9090/swagger-ui.html`
- API Docs: `http://localhost:9090/v3/api-docs`

## Base de Datos

- PostgreSQL 15
- Puerto: 5432
- Los datos persisten en un volumen Docker llamado `postgres_data`

## Desarrollo

Para desarrollo local, puedes ejecutar solo la base de datos en Docker:
```bash
docker-compose up postgres -d
```

## Troubleshooting

- Si hay problemas con los puertos, verificar que 9090 y 5432 estén disponibles
- Para limpiar volúmenes: `docker-compose down -v`

## Contribuir

1. Crear rama feature: `git checkout -b feature/nueva-caracteristica`
2. Commit cambios: `git commit -am 'feat: agregar nueva caracteristica'`
3. Push a la rama: `git push origin feature/nueva-caracteristica`
4. Crear Pull Request
