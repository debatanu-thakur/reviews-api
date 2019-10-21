# Reviews API Midterm
This application is built using spring-boot and MySQL. The reviews api stores and gets the product, its reviews and comments into and from the database.

## Background
The features include
1. The reviews api does CRUD operation using Spring Boot JPA repositories and MySQL

## How To Run
1. Have Java 1.8 installed
2. Have maven package installed
3. Clone the repository first and checkout the branch develop-final
    ```$cmd
    git clone https://github.com/debatanu-thakur/reviews-api.git
    ```
4. Make sure http://localhost:8080, 3306, 8100 is available
5. Run using command
    ```
    mvn spring-boot:run
    ```
   Or
   
   Under the root folder
   ```
   docker-compse up
   ```
6. To test, run `mvn test`
### License
MIT