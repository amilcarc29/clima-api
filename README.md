# clima-api

> Proyecto hecho con:

* Java 8.
* Jetty.
* Maven.
* MongoDB: _mLab_.
* Spring Boot.
* JSON: _org.json_.
* Math: _Apache Commons_.

* Obtener todas las consultas previas de clima:

   http://...:8080/api/

* Nueva consulta:

   http://...:8080/api/clima?dias={dias} => http://...:8080/api/clima?dias=270
   
* Ver los planetas cargados en la base de datos:
   
   http://...:8080/api/planetas/

* Ver detalle de un planeta específico:

   http://...:8080/api/planetas/{nombre} => http://...:8080/api/planetas/Ferengi
