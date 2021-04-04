# Assessment
#### Given date  01.04.2021
#### Deadline 04.04.2021
## By Kasun Jayaweera
The application contains two code bases; the frontend Angular and the 
backend spring boot application.
### Frontend App (Angular)
The application was developed by using Redux pattern. The Angular's
@ngrx (@ngrx/store, @ngrx/data, @ngrx/effects ...) library which is 
an implementation of the Redux pattern was used.

### Backend API (Spring boot)
Developed with Spring Boot framework. It follows JPA standards for persisting 
data and is implemented through Hibernate library.

### Test cases
JUnit along with Mockito was used to test the business logic. Total 
of 14 test cases were created to validate the business logic.

### Installation
Setup environment variables
```
1. Setup these in the Spring app run configurations
# DB configuration
MYSQL_HOST=<localhost>
MYSQL_DB=<db_assessment>
MYSQL_USER=<db_user>
MYSQL_PASS=<db_pass>

# Cors configuration
ORIGIN_ALLOWED_FRONTEND=<http://localhost:4200>

1. Setup these in the Angular app environments.ts file
# Frontend API BASE
# Set your api url here
environment.urls.base=<http://localhost:8080/api>

```

Then build and run the application
