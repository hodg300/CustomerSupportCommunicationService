Customer Support Communication Service
======================================

This is a customer service, which will allow the opening of tickets for the company's support and interact between the company's support agent and the customers.

# What you’ll need

* Docker
* JDK 6 or later
* IDE that support Spring apps
* Gradle
* Kafka

* USER MANAGEMENT SERVICE **(Required for creating tickets and comments)** - The project we implemented during the course. We will check if users exists in the service and having the correct roles (customer/supportAgent)

* CUSTOMER SHOPPING CART SERVICE - Daniel Sasson's Team Project. It will be possible to open a ticket for a bug in a shopping cart or any other complaint according to its shoppingCartId.

* BLOG COMMENTS SERVICE - Miriam's team project. It will be possible to open a ticket to support a particular comment on a blog (to complain about inappropriate language, etc.) according to its commentId.

* TRACKING SERVICE - Gil's Team Project. It will be possible to open a ticket for a product that has not yet arrived according to its trackId.

* PRODUCT RETURN AND REFUND - Tomer's team project. It will be possible to open a ticket for money not returned as a result of returning a product, etc. according to its requestId.

* SHOPPING CATALOG SERVICE - The project we implemented during the course. It will be possible to open a ticket for a defective or defective product according to its productID.

# Kafka

Follow the following guide:

https://kafka.apache.org/quickstart

After uploading the servers, create two topics: 

1. ticket

2. comment 

# Docker

Download Postgres Docker:

    docker pull postgres

Run Postgres Docker:

    docker run -d -p 5433:5432 --name customerSupportCommunication -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=customerSupportCommunication postgres:latest

make sure port 5433 is not taken by another app.

# Build

Open the project on your favorite IDE.

Run gradle build for building the project:

    gradle build


# How to run it?

Run the main function in src -> main -> java -> acs-> CustomerSupportCommunicationService
    
Make sure the server is listing to port 8082 on localhost:

    http://localhost:8082
    
# External Services Communication

The default URL of each service is http://localhost, but it can be change in the application.properties file.

userManagementService.port=8081

shoppingCatalogService.port=8084

productReturnAndRefundsService.port=8085

trackingService.port=8086

blogCommentsService.port=8087

shoppingCartService.port=8088

# How to use it? 

After the server is running, you can use Swagger to send request to the service:

```
http://localhost:8082/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```
You can also create Kafka producer to create a ticket or a comment.

Example of ticket creation with Kafka producer:

         ➜ kafka_2.13-2.7.0 bin/kafka-console-producer.sh --topic ticket --bootstrap-server localhost:9092
         
        >{"id":"string","name":"string","email":"mor@g.g","subjectType":"GENERAL","externalId":"string","createdTimeStamp":"2021-02-10T14:53:13.878Z","closingTimeStamp":"2021-02-10T14:53:13.878Z","open":true}
        
Example of comment creation with Kafka producer:

         ➜  kafka_2.13-2.7.0 bin/kafka-console-producer.sh --topic comment --bootstrap-server localhost:9092
         
        >{"id":"string","ticketId":"e413a69d-bfbd-416a-947b-f64cdfae7b0c","email":"mor@g.g","description":"string","createdTimeStamp":"2021-02-10T15:02:30.034Z"}