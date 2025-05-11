# GFT prueba técnica

## Elecciones del proyecto

- Java 21 (24 es muy reciente)
- Spring boot 3.4.5
- H2 en memoria tal como se indica en los requisitos, incluye en resorces el squema.sql y data.sql para insertar datos
  - la consola de h2 es accesible desde http://localhost:8080/h2-console
- Swagger-ui accesible desde http://localhost:8080/swagger-ui/index.html


## Lanzar la app

Se ha añadido el wrapper de maven (mvnw), para que se pueda gestionar el proyecto.

Instalar y lanzar el proyecto con:
```shell
# Instalar las dependencias 
./mvnw install

# lanzar con
./mvnw spring-boot:run
```
También se puede ejecutar el proyecto con la opción de ejecución de Intellij, en caso de usarse este IDE


