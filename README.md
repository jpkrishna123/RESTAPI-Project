
A Sample Micro Service application to fetch order and product information. 

Requirements
============
### Task 1) ###
The Micro Service use to fetch order and product information.

#### Criteria 1: Filter all the orders which are shipped. ####
 * This search criteria should be applied on the `status` column of the `orders` table.
 * The value of the `status` should be `SHIPPED`.

#### Criteria 2: Filter all the orders where discount has been applied. ####
 * This search criteria should be applied on the `discount` column of the `orders` table.

#### Criteria 3: Filter all the orders having more that two products in the transaction. ####
```json
 eg:
 {
         "orderNumber": "RTL_1003",
         "discount": 19.99,
         "taxPercent": 8.5,
         "total": 139.97,
         "totalTax": 11.89,
         "grandTotal": 131.87,
         "status": "SHIPPED",
         "products": [
             {
                 "upc": "1358743283",
                 "sku": "7394650110003",
                 "description": "Polo Shirt",
                 "price": 19.99
             },
             {
                 "upc": "1458843283",
                 "sku": "7394750120000",
                 "description": "Floral Swing Skirt",
                 "price": 69.99
             },
             {
                 "upc": "1258793283",
                 "sku": "7394950140000",
                 "description": "True Skinny Jeans",
                 "price": 49.99
             }
         ]
     }
```

#### Criteria 4: Filter all the products whose price is more than $30. ####
 * This search criteria should be applied on the `price` column of the `products` table.

### Task 2) ###
 * Add JUnit tests for all the existing classes in the workspace. Also, make sure to test-drive the new code being added.

### Task 3) ###
 * Add **Spring Integration Tests** for all the `API endpoints`.

Technical Information
=====================
 * You should have Java 8, Maven and Git installed.
 * Execute `OrdersApplication.java` to start the server.
 * The server will be started on `8088` port.
 * The sample data has been pre-loaded so that the new `Search API` can be tested. Please refer to `data-h2.sql`.

Tech Stack
==========
 * Java 8.x
 * Maven 3.x
 * Spring Framework 4.x
 * Spring Boot 1.5.6
 * Hibernate
 * JPA
 * H2 database
 * JUnit 4.x
 * Mockito 2.x
 * Hamcrest
 * Spring Integration Tests
 
How to Build
==========

mvn clean install

How to Run
==========
java -jar target/orders-0.0.1-SNAPSHOT.jar

URL Details
==========
1) List Orders:
   **[GET]** `http://localhost:8088/orders`

2) Fetch Order Details:
   **[GET]** `http://localhost:8088/orders/{order_id}`

3) List Products:
   **[GET]** `http://localhost:8088/products`

4) Fetch Product Details:
   **[GET]** `http://localhost:8088/products/{product_id}`

Note: post man collections was checked into docs folder
