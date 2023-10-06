# CodeChella-Api

## Properties
### Banco em nuvem
```
# MSSQL CONFIG
#DATASOURCE
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.datasource.url=jdbc:sqlserver://codechella.database.windows.net:1433;database=bd-codechella;user=adm@codechella;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;

spring.datasource.username=adm
spring.datasource.password=Lucas2004

#JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.profiles.active=open

jwt.secret=secret
```