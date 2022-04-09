# DepartamentService

## Installation


 ````
 git clone https://github.com/yhtyyar/UserService
 ````
 
## Build project

 ````
 mvn clean install
 ````
 
 ## API 
 
 <h4> GET    (http://localhost:8081/api/v1/departments/{ id })          Get Department by id     </h4>
 <h4> GET    (http://localhost:8081/api/v1/departments/)                Get all Department       </h4> 
 <h4> POST   (http://localhost:8081/api/v1/departments/{ Department })  Create Department        </h4>  
 <h4> PUT    (http://localhost:8081/api/v1/departments/{ Department })  Update Department        </h4> 
 <h4> DELETE (http://localhost:8081/api/v1/departments/{ id })          Delete Department by id  </h4> </br> 
 
 <h4> GET    (http://localhost:8081/api/v1/departments/getAllUsers)     Get all Users with UserService  </br> </h4>
 
 
 ## Data for authorization
 
 ### ROLE USER
 
 E-mail:
 ````
 user@mail.com
 ````
 password:
  ````
 user
 ````
 
 ### ROLE ADMIN
 
  E-mail:
  ````
 admin@mail.com
 ````
 password:
  ````
 admin
 ````
 
 ## Authorization link:  http://localhost:8081/auth/login 
