# 🚆 Train Ticket APP

A Spring Boot-based train ticket purchasing system with in-memory storage, featuring endpoints for ticket purchasing, user to seats management, and seat allocation. Swagger UI is integrated for easy API testing.

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.4.4
- Spring Web
- Springdoc OpenAPI (Swagger UI)
- Lombok
- Maven

## ▶️ Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+

### Clone the repository

```bash
git clone https://github.com/neha-midha/train-ticket.git
cd train-ticket-booking
```

### Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

The application will start at:  
http://localhost:8080

## 📘 API Documentation

Swagger UI is available at:  
http://localhost:8080/swagger-ui.html

You can view and test all available APIs from the browser using the Swagger interface.

## ✅ Available APIs

### 🎟 Ticket APIs

| Method | Endpoint                          | Description                         |
|--------|-----------------------------------|-------------------------------------|
| POST   | `/ticket/purchase`               | Purchase a new ticket               |
| GET    | `/ticket/receipt/{ticketId}`     | Get ticket receipt by ticket ID     |
| DELETE | `/ticket/remove/{ticketId}`      | Delete ticket and user by ticket ID |
| PUT    | `/ticket/change-seat/{ticketId}` | Change seat for the given ticket    |

### 🚄 Train APIs

| Method | Endpoint                                                   | Description                                      |
|--------|------------------------------------------------------------|--------------------------------------------------|
| GET    | `/train/{trainName}/user-seats/{section}`                 | Get occupied seats in a section of a train       |

## 🧪 Sample Test Cases
One train with name LONDON_FRANCE_EXPRESS is added to the in-memory datastore with routes (LONDON -> BRUSSELS, LONDON -> PARIS, BRUSSELS -> PARIS).
Train is registered with 10 seats in each section (A & B)
The Station names are enum values hence, only use the registered values or Add more by referring to DataStore.java file
Section is another enum with two possible values "A" & "B"

## 🧹 Notes

- The app uses in-memory data structures (no database).
- Restarting the app will reset all data.
- Enum values like `LONDON_FRANCE_EXPRESS` are used directly in requests.
