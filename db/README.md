# Database

Download the official MySQL world sample database:

https://downloads.mysql.com/docs/world-db.zip

Import it into a local MySQL instance (or use Docker):

```bash
# Example with Docker MySQL
docker run --name world-mysql -e MYSQL_ROOT_PASSWORD=example -e MYSQL_DATABASE=world -p 3306:3306 -d mysql:8

# Then import the SQL file from the zip
mysql -h 127.0.0.1 -P 3306 -u root -pexample world < world.sql
```

Connection details will be configured in the application (environment variables or properties file).
