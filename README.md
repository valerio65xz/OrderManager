# Oder Manager

Creates a Simple Spring Boot project to manage creation and shipping of orders by EverythingEverythere and ACME.

# Requirements

Two elements are madatory to run the project:
* Java JDK 18 
* Docker

If you want also to check and debug the code, you can use an IDE like IntelliJ
(recommended) or Eclipse cloning the project from GIT.

# Installation 🛠️

You can clone the project from this link:

```sh
git clone https://github.com/valerio65xz/OrderManager.git
```

If you’re a Windows user, maybe you have to redirect the Docker URL into the localhost. So please open in admin mode 
the file *“C:/Windows/system32/drivers/etc/hosts”* and put this string at the end of the file:

```sh
127.0.0.1 kubernetes.docker.internal
```

# Usage ℹ️

If you want to just execute the project without an IDE, open a terminal in your installation folder and type first:

```sh
docker compose up
```

to run the Docker Container with Kafka, and then

```sh
java -jar OrderManager.jar
```

to run the application.

Therefore if you use Postman, you may import `Ipitq.postman_collection.json` to import the collection. There are
four operations (2 for create an ACME or EverythingEverywhere order, 2 for send it). Body examples are already inside
the collection.

if you don't have or don't want to use postman, you can use Swagger (see Docs) or manually call the application:
* The URL to call to create EE Order: `http://localhost:9001/order/create/ee`
* The URL to call to create Acme Order: `http://localhost:9001/order/create/acme`
* The URL to call to ship EE Order: `http://localhost:9001/order/ship/ee`
* The URL to call to ship Acme Order: `http://localhost:9001/order/ship/ee`
* Params: `type` (POST)
* Mandatory body: you can see the swagger documentation.

Finally, to check the Kafka Topics and events, you can access the AKHQ console at this link:

`http://localhost:8081/ui/kafka/topic`

# Docs 📚

You can find the detailed documentation in `Docs.pdf` and the OpenAPI 3.0 Docs at `http://localhost:9001/swagger-ui/index.html`.
