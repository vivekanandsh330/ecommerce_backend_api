# Ecommerce_Backend_API
Backend
* This is a Maven Project. Ensure, Maven is installed on your system.
* It is Recommended that you use Linux Based OS.
* It might happen that you have installed XAMPP/LAMPP software (by Bitnami) on your system. Instead of using the db provided by XAMPP/LAMPP by bitnami, it is recommended that you install ``mariadb-server`` and use it as  database while running this application.  

## How to run in local
1. Rename ``src/main/resources/application.properties.example`` to ``src/main/resources/application.properties``.
1. Change the Application Properties (E.g. username/password of DB) present in ``resources/application.properties``  according to your local postgres-server.
1. Go to application.properties and comment / uncomment the corresponding front-end url and enter the STRIPE API Keys
1. Create a database called `ecommerce` with ``CHARACTER SET utf8mb4`` and `COLLATE utf8mb4_0900_ai_ci`. MariaDB does not support `COLLATE utf8mb4_0900_ai_ci`. So, if you are using MariaDB, open `database-dump.sql` file and replace `COLLATE utf8mb4_0900_ai_ci` with `COLLATE utf8mb4_general_ci`  
1. Import `database-dump.sql` in it.
1. To run the application, run the command ``sh run.sh`` i.e. execute the ``run.sh`` file. 


## Create a Category (POST Request)
* URL: http://localhost:9292/category/create
* Method: POST
* Headers:
* Content-Type: application/json
* Body: Raw JSON
```
{
    "id":"1",
    "category_name": "Electronics",
    "description": "Electronics category description",
    "image_Urls" : ""
}
```
## Update a Category (POST Request)
* URL: http://localhost:9292/category/update/{categoryID}
* Replace {categoryID} with the actual ID of the category you want to update.
* Method: POST
* Headers:
* Content-Type: application/json
* Body: Raw JSON
```
{
    "id":"1",
    "category_name": "Electronics",
    "description": "Updated Electronics category description",
    "image_Urls" : ""
}
```
## Get All Categories (GET Request)
* URL: http://localhost:9292/category/
* Method: GET
* Headers: None
* Body: Not needed for GET requests.
* Like this many more request for other api tested using Postman


## In case of "java.lang.IllegalStateException: Unable to load cache item error":
- Go into pom.xml and comment out the scope for the "spring-boot-starter-tomcat" dependency


## How to use prod profile
mvn package
java -jar -Dspring.profiles.active=prod <package name in target>

## Run in production server with latest changes in master


#admin access
email: vivekanandsh330@gmail.com
password: @Vv841234
firstname: Vivekanand

