Customer Support Communication Service
======================================

This is a customer service, which will allow the opening of tickets for the company's support and interact between the company's support agent and the customers.

# What you’ll need

* Docker
* JDK 6 or later
* IDE that support Spring apps
* Gradle

* USER MANAGMENT SERVICE **(Requeird for creating tickets and comments)** - The project we implemented during the course. We will check if users exists in the service and having the correct roles (customer/supportAgent)

* CUSTOMER SHOPPING CART SERVICE - Daniel Sasson's Team Project. It will be possible to open a ticket for a bug in a shopping cart or any other complaint according to its shoppingCartId.

* BLOG COMMENTS SERVICE - Miriam's team project. It will be possible to open a ticket to support a particular comment on a blog (to complain about inappropriate language, etc.) according to its commentId.

* TRACKING SERVICE - Gil's Team Project. It will be possible to open a ticket for a product that has not yet arrived according to its trackId.

* PRODUCT RETURN AND REFUND - Tomer's team project. It will be possible to open a ticket for money not returned as a result of returning a product, etc. according to its requestId.

* SHOPPING CATALOG SERVICE - The project we implemented during the course. It will be possible to open a ticket for a defective or defective product according to its productID.


# Docker

Download Postgres Docker:

    docker pull postgres

Run Postgres Docker:

    docker run -d -p 5432-5432:5432-5432 --name customerSupportCommunication postgres:latest


# Build

Open the project on your favorite IDE.

Run gradle build for building the project:

    gradle build


# How to run it?

Run the main function in src -> main -> java -> acs-> CustomerSupportCommunicationService
    
Make sure that the server is on listing to port 8082 on localhost:

    http://localhost:8082
    
# External Services Communication

The default URL of each service is http://localhost and it can be chnage in the application.properties file.

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
