# GFT prueba técnica

## Elecciones del proyecto

- Dado que los requisitos hablan de pom, uso maven en lugar de Gradle
- He elegido java 21, dado que la 24 es demasiado reciente
- para spring boot si he elegido la versión estable más reciente 3.4.5
- se incluye en resorces el squema.sql y data.sql para insertar datos 
  - no se ha solicitado liquibase o flyway así que no uso estos métodos

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