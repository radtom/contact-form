# Contact Form App
Simple contact form web application in Spring Boot and React.js

App lets you create requests and saves them to database. Request types can be created and are also saved to database.

<h2>Build</h2> 
App can be started by running maven command <b>spring-boot:run</b>. .jar can be created by running maven command <b>clean package</b>. Compatible Java version is Java 8.

<h2>Request Type Creation</h2>
App lets you create your own request types. At least one request type must be created after first start of application.
Request Type can be created by <b>POST REST call to {baseUrl}/rest/request-type</b>. Request body must be String value - name of Request Type, content type must be set to application/json.
