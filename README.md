# GFT prueba técnica

## Elecciones del proyecto

- Java 21, ya que la 24 es muy reciente
- Spring boot 3.4.5
- H2 en memoria tal como se indica en los requisitos, incluye en resorces el squema.sql y data.sql para insertar datos
  - la consola de h2 es accesible desde http://localhost:8080/h2-console
- Swagger-ui accesible desde http://localhost:8080/swagger-ui/index.html
- ArchUnit: librería de testing para arquitectura
- .editorconfig: con algunas reglas para tener un código uniforme, estas reglas solo funcionan en IDES compatibles o
si se ha instalado el plugin correspondiente
- lombok, para evitar tener que añadir setter y getters en clases de objetos
- spring-devtools, para agilizar el desarrollo

## Lanzar la app

Se ha añadido el wrapper de maven (mvnw), para que se pueda gestionar el proyecto. Se puede usar mvn en caso de estar
instalado en el sistema.

Instalar y lanzar el proyecto con:
```shell
# Instalar las dependencias 
./mvnw install

# lanzar con
./mvnw spring-boot:run
```
También se puede ejecutar el proyecto con la opción de ejecución de Intellij, en caso de usarse este IDE

## Lanzar los test

Se pueden lanzar los test desde el IDE o bien desde el comando

```shell
./mvnw test

# si se quiente evitar el lanzamiento de los test
./mvnw install -DskipTests
```

Test incluidos:

- HexagonalArchitectureTest: para revisar la arquitectura hexagonal

## Agregar y actualizar dependencias

```shell
# Descargar las dependencias forzando actualización
./mvnw dependency:resolve -U

# Para "refrescar" las dependencias
./mvnw clean install
```