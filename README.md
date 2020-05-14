# ecommerce-app

## This application is developed using Spring Boot and MySql. Below is the API documentation -

APIs - 
1. Fetch the product details using id : **GET http://localhost:8080/api/products/{id}**
   > **Response -**
   *{
	    "id": 1,
	    "name": "Nike",
	    "price": 2000.00,
	    "quantity": 2
    }*
2. Fetch all products details : **GET http://localhost:8080/api/products**
   > **Response -** 
   *[
	    {
		    "id": 1,
		    "name": "Nike",
		    "price": 2000.00,
		    "quantity": 2
	    },
	    {
		    "id": 2,
		    "name": "Puma",
		    "price": 1800.00,
		    "quantity": 1
	    }
    ]*
3. Add new entries of products : **POST http://localhost:8080/api/products**
   > **Response -**
   *[{
	    "name": "Nike",
	    "price": 2000.00,
	    "quantity": 2 
    }]*
4. Edit/Update an entry of product : **PUT http://localhost:8080/api/products**
   > **Response -**
   *{
	    "id": 1,
	    "name": "Nike",
	    "price": 1999.99,
	    "quantity": 2
    }*
5. Remove an entry of product : **DELETE http://localhost:8080/api/products?id=3**
   > **Response -** 
   *Removed!*
6. Fetch all the orders : **GET http://localhost:8080/api/orders**
   > **Response -**
   *[
	    {
		    "id": 1,
		    "createdOn": "14/05/2020",
		    "emailId": "abc@gmail.com",
		    "status": "PAID"
	    },
	    {
		    "id": 2,
		    "createdOn": "14/05/2020",
		    "emailId": "xyz@gmail.com",
		    "status": "PAID"
	    }
    ]*
7. Place an order : **POST http://localhost:8080/api/orders**
   > **Response -**
   *{
	    "orderProductDtoList": [{
		    "id": 1,
		    "name": "Nike",
		    "price": 2000.00,
		    "quantity": 1
	    }],
	    "emailId": "abc@gmail.com"
    }*

