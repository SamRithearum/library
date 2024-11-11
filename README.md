# library

This is a small Demo app with java spring boot.

# Running this project:

## Step 1:

Clone this project

```
git clone git@github.com:SamRithearum/library.git
```

## Step 2:

Run this project with docker-ps

Go to project folder

```
cd library
```

Run docker-compose

```
docker-compose up --build
```

If applicaiton running successfully we shall get:

1. PostgresSQL
2. PGAdmin Dashboard
   url: http://localhost:5050/login?next=/
   username: sam@example.com
   password: sampass
3. Eureka Server: http://localhost:8761
4. Gateway Service: http://localhost:8080
5. Student Service: http://localhost:8081
6. Book Service: http://localhost:8082

For swagger-ui on each service should be on "/swagger-ui/index.html"

PS: For some dummy data we can execute "student-migration.sql" and "v2-book-migration.sql" from PGAdmin dashboard.
