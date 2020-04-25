# Demo-Shop als Code-Beispiel

* Standard Spring Boot App
* Java 11, Maven, In-Memory-Database
* Starten mit mvn `spring-boot:run` oder `java -jar shop-0.0.1-SNAPSHOT.jar`

-> http://localhost:8080

## API

Durch Spring Data REST (https://spring.io/guides/gs/accessing-data-rest/) wird unter http://localhost:8080/items eine
vollständige zur CRUD-API zur Verfügung gestellt.

Beispiele:
```
# Items anzeigen
curl http://localhost:8080/items

# Item anlegen
curl -i -X POST -H "Content-Type:application/json" -d '{ "sku": "1234567890", "name": "Test", "price": "99.98", "quantity": "10" }' http://localhost:8080/items
```