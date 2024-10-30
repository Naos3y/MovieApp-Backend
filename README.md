# MovieApp Backend

A movie management application that allows users to register, rate, and view details about movies they have watched or wish to watch. This application uses Spring Boot for the backend, implementing security with authentication and authorization.

## Features

- User registration with data validation.
- Secure login with JWT authentication.
- Add movies to watched and wishlist.
- View details of movies.
- Rate movies by users.
- Differentiation of permissions for users and administrators.

## Technologies Used

- **Backend:** Spring Boot
- **Database:** PostgreSQL
- **Security:** Spring Security with JWT
- **Frontend:** React (not yet developed)

## How to Run the Project

### Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL

### Database Setup

1. Create a database in PostgreSQL.
2. Configure the database credentials in the `application.yml` file.

### Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/Naos3y/MovieApp-Backend.git
   ```
2. Navigate to the project directory:
   ```bash
   cd MovieApp-Backend
   ```
3. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```
