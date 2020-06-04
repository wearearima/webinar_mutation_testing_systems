# Caminando con paso firme hacia software de calidad: **Mutation Testing Systems**

Código fuente del ejemplo utilizado durante el webinar.

**Status**
* Caso de uso. Genera un "report" en base a los worklogs de un usuario en un día concreto: Dado el nombre de usuario y la fecha indica si las horas están correctas, faltan o sobran.

**Ejecutar la app**

Se trata de una aplicación Springboot + Maven, así que es suficiente con, situarse en la carpeta del proyecto y hacer:

```./mvnw spring-boot:run```

La aplicación estará disponible en [http://localhost:8080](http://localhost:8080)

**Ejecutar Pitest**

Ya está configurado en el pom.xml el plugin de pitest para maven. así que es suficiente con, situarse en la carpeta del proyecto y ejecutar:

```./mvnw clean test```

El report generado está configurado para guardarse en target/site/pit-test/index.html
