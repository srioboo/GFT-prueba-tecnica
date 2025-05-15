# GFT prueba técnica

## Elecciones del proyecto

- Java 21 y Spring boot 3.4.5
- H2 en memoria tal como se indica en los requisitos, incluye en resorces el squema.sql y data.sql para insertar datos
	- la consola de h2 es accesible desde http://localhost:8080/h2-console (user y pass en application.properties)
- Swagger-ui accesible desde http://localhost:8080/swagger-ui/index.html (activación en application.properties)
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

También se puede ejecutar el proyecto con la opción de ejecución de Intellij o VSCode, en caso de usarse estos IDE

## Lanzar los test

Se pueden lanzar los test desde el IDE o bien desde el comando

```shell
./mvnw test

# si se quiere evitar el lanzamiento de los test
./mvnw install -DskipTests
```

Test incluidos:

- **HexagonalArchitectureTest.java**: para revisar la arquitectura hexagonal
- **PricesBrandProductDateTest.java**: realiza los test solicitados en la prueba y se añaden algunos adicionales
	- se testea que si no hay precios para los datos dados se devuelve un objeto no price a null
	- se revisan puntos en los que la fecha dada coincide con la fecha de comienzo
	- se revisan otras marcas
- **PricesJsonTest.java**: con pruebas para validar el json (archivo json de prueba en "resources/json/prices.json")
- **PricesRestAssuredTest.java**: prueba con rest assured para testear el punto prices que devuelve todos los datos
  se verifica que el primer elemento es correcto
- **PricesRestTest.java**: verifica usando test de spring que los objetos de la lista recibidas no son null

## Agregar y actualizar dependencias

```shell
# Descargar las dependencias forzando actualización
./mvnw dependency:resolve -U

# Para "refrescar" las dependencias
./mvnw clean install
```

## Despliegue de la aplicación con docker

Para mostrar la utilización de docker, se ha creado un dockerfile que construye la aplicación y crea una imagen,
además se ha creado un docker-compose para desplegar la aplicación en un docker

Para ejecutarla, lanzar:

```shell
docker-compose up --build
```

La aplicación debe desplegarse en el puerto 9090

```shell
# se puede lanzar el rest de prices desde

http://localhost:9090/prices
```