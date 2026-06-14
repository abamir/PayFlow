# How to Run the Application

## Prerequisites

Before running the project, ensure the following are installed:

* Java 17 or later
* Maven 3.8+
* IntelliJ IDEA / Eclipse / VS Code (optional)

Verify installation:

```bash
java -version
mvn -version
```

---

## Clone the Repository

```bash
git clone <repository-url>
cd payflow
```

---

## Build the Project

Compile and package the application:

```bash
mvn clean install
```

If the build is successful, Maven will generate the application JAR file inside the `target` directory.

---

## Run the Application

### Option 1: Using Maven

```bash
mvn spring-boot:run
```

### Option 2: Using the Generated JAR

```bash
java -jar target/payflow-0.0.1-SNAPSHOT.jar
```

---

## Verify Application Startup

Once the application starts successfully, you should see logs similar to:

```text
Tomcat started on port(s): 8080
Started PayFlowApplication
```

The application will be available at:

```text
http://localhost:8080
```

---

## Access H2 Database Console

Open your browser and navigate to:

```text
http://localhost:8080/h2-console
```

Use the following connection details:

```text
JDBC URL : jdbc:h2:mem:payflowdb
Username : sa
Password :
```

Click **Connect**.

---

## Test REST APIs

### Create User

```bash
curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
"name":"Priya",
"upiId":"priya@okaxis",
"phoneNumber":"9876543210",
"balance":10000
}'
```

### Get All Users

```bash
curl http://localhost:8080/users
```

### Get User By ID

```bash
curl http://localhost:8080/users/1
```

### Get User By UPI ID

```bash
curl http://localhost:8080/users/upi/priya@okaxis
```

### Send Money

```bash
curl -X POST http://localhost:8080/transactions \
-H "Content-Type: application/json" \
-d '{
"senderUpiId":"priya@okaxis",
"receiverUpiId":"rahul@okhdfc",
"amount":500
}'
```

---

## Stop the Application

Press:

```text
CTRL + C
```

in the terminal where the application is running.

---

## Expected Outcome

After creating users and transactions:

* User records will be stored in the `users` table.
* Transaction records will be stored in the `transactions` table.
* Data can be verified through the H2 Console using SQL queries:

```sql
SELECT * FROM users;
SELECT * FROM transactions;
```
