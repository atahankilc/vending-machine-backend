# Vending Machine Backend Application

deployed on render -> https://vending-machine-backend-6ov3.onrender.com

Not: Since I use free version of render, sometimes it can stay unresponsive for a long time, or it gives a direct error. As far as I can see, it allocates resources according to the number of incoming requests, and so it reduces the resources if there is no request for a long time. Page refreshing mostly solves the problem. 

## Endpoint Structure

- api
  - [product](./src/main/java/com/example/vendingmachinebackend/controller/ProductController.java)
    - getAll 
    - add
    - update
    - remove/{productName}
  - [user](./src/main/java/com/example/vendingmachinebackend/controller/UserController.java)
    - me
    - addMoney
    - insertCoin
    - refundCoin
  - [checkout](./src/main/java/com/example/vendingmachinebackend/controller/CheckoutController.java)
  - [supplier](./src/main/java/com/example/vendingmachinebackend/controller/SupplierController.java)
    - authenticate
    - collect
- swagger-ui.html

#### Public Endpoint

- swagger-ui.html
- GET /api/product/getAll

#### Secured Endpoints

- /api/**

## Services 

- [ProductService](./src/main/java/com/example/vendingmachinebackend/service/ProductService.java) -> responsible for managing product repository
  - ProductRepository
- [UserService](./src/main/java/com/example/vendingmachinebackend/service/UserService.java)  -> responsible for managing user repository
  - UserRepository
- [SupplierService](./src/main/java/com/example/vendingmachinebackend/service/SupplierService.java)  -> responsible for managing exclusive operations
  - StatusRepository
- [CheckoutService](./src/main/java/com/example/vendingmachinebackend/service/CheckoutService.java)  -> responsible for the transaction requests
  - ProductRepository
  - UserRepository
  - StatusRepository
- [TemperatureService](./src/main/java/com/example/vendingmachinebackend/service/TemperatureService.java)  -> responsible for "cooling" the application

## Project Dependencies

- Database -> MongoDb
    - Atlas Database Service is deployed for this task. Application connects to the database specified in [application.properties](./src/main/resources/application.properties) file
- Outh2 Authorization Server -> Google
    - Application is structured as an ouath2 resource server, it expects jwt to permit accessing secured endpoints.
      Google Authentication Service is used for verify access tokens.
    