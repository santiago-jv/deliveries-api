# Deliveries API
 **NOTE:This server has been updated. Check the release here: https://github.com/santiago-jv/deliveries-rest-api**
 
This is my first REST API created in Spring Boot. During the development of this API I learned to use the spring ORM to handle in a simpler way the relationships of the entities created in the program.

This REST API is consumed by the following application: [Repository](https://github.com/santiago-jv/deliveries-app-with-reactjs) 

## Configuration to run

This application uses Java 11 so make sure you have the same version or change the version you have in the pom.xml file.

### Database configuration
In the file aplication.properties you will find the necessary settings to use postgresql as a database manager.  

Make sure you have maven installed on your machine.
To run a spring boot application you must execute the following command:

`mvn spring-boot: run`

### Important

Once the server started, some tables should have been created in your database. Go to the 'roles' table and add 2 roles:

`id:1 name: ROLE_ADMIN`

`id:2 name: ROLE_USER`

***After completing the configuration, your server will be displayed at http: // localhost: 8080***
