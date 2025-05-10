# Recomendaciones:

- Código funcionando
- Mantener estructura de código clara y limpia: utilización **obligatoria de arquitectura hexagonal**.
- Tener el código mínimo: no implementar cosas no requeridas, y tener cuidado a las dependencias del **pom**. Se pueden añadir librerías (lombok, swagger, etc… mientras tienen sentido y aportan). Nada de código muerto.
- Calidad de código,
- Tests unitarios
- Test funcionales: se pide en el enunciado que se testen por lo menos 5 casuísticas. Se pueden implementar como tests de integración, pero se valora más tener tests e2e (karate, cucumber, restassured, postman, …)
- API first es altamente recomendable
- Gestión de Excepciones: tener controlado los casos de no encontrar precio, o de parámetros incorrectos
- Tener un README que presenta como ejecutar el programa y testearlo, y algunas especificades del código si hay.
- Se valora muy positivamente la configuración de SwaggerUI y/o Docker en la resolución del ejercicio.