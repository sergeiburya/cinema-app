<h1 align="center">CINEMA SERVICE</h1>
<div align="center"><img  src="kino.png" height="100" title="Logo" alt=""/></div></div>

<h2 align="center">Cinema Management Web Application</h2>

### Description:
The project implements the basic functionality of the cinema web application. 
The application implements user authentication and authorization.
Based on the role, the following functions are available to the user:
* POST: /register - all
* GET: /cinema-halls - user/admin
* POST: /cinema-halls - admin
* GET: /movies - user/admin
* POST: /movies - admin
* GET: /movie-sessions/available - user/admin
* POST: /movie-sessions - admin
* PUT: /movie-sessions/{id} - admin
* DELETE: /movie-sessions/{id} - admin
* GET: /orders - user
* POST: /orders/complete - user
* PUT: /shopping-carts/movie-sessions - user
* GET: /shopping-carts/by-user - user
* GET: /users/by-email - admin

### Project structure.
* DAO - Data Access Layer
* DTO - Data Transfer Object
* Service - Application logic layer
* Controllers - Presentation layer

### Languages and Tools:
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=java&logo=java&logoColor=white)
![Sql](https://img.shields.io/badge/SQL-F8F8FF?style=flat&logo=mysql&logoColor=0000CD)
![Git](https://img.shields.io/badge/Git-F8F8FF?style=flat&logo=Git&logoColor=FF0000)
![Spring](https://img.shields.io/badge/Spring-9ACD32?style=flat&logo=Spring&logoColor=F8F8FF)
![Maven](https://img.shields.io/badge/Maven-F8F8FF?style=flat&logo=apachemaven&logoColor=F4A460)
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=apache&logo=apache-tomcat&logoColor=black)
![JDBC](https://img.shields.io/badge/JDBC-008B8B?style=flat&logo=jdbc&logoColor=F8F8FF)
![Javax](https://img.shields.io/badge/Javax-FFE4B5?style=flat&logo=Javax&logoColor=F8F8FF)
![JSP](https://img.shields.io/badge/JSP-F8F8FF?style=flat&logo=jsp&logoColor=F4A460D)
![JSTL](https://img.shields.io/badge/JSTL-F8F8FF?style=flat&logo=JSTL&logoColor=F8F8FF)
![Html](https://img.shields.io/badge/HTML-F8F8FF?style=flat&logo=html5&logoColor=black)

### Quick Start:
1. Clone the [repository](https://github.com/sergeiburya/cinema-app)
2. Install MySQL
3. Configure Apache Tomcat version: 9.0.50
4. Set the necessary database connection settings in the file [src/main/resources/db.properties](src/main/resources/db.properties)
   to creating a schema and tables for the project
5. Configure [src/main/resources/log4j2.xml](src/main/resources/log4j2.xml)
   at line 7 with your ABSOLUTE_PATH to this project
6. Start tomcat server
7. The application will create one user each with administrator and user rights by default. 
You can edit the input data in the [file](src/main/java/cinema/config/DataInitializer.java).
8. The application is running.

### About me
I am a beginner java developer, you can learn more about me on my page - [Serhii Buria](https://github.com/sergeiburya).
