# Movie & Director Management Application

This Spring Boot application manages information for Movies and Directors, demonstrating three basic operations: creating, reading, and updating data.

## Entity Relationship Design

The application is built around two main entities:

1. **Director**
   - Attributes: id, firstName, lastName, dateOfBirth, nationality, biography
   - Relationship: One-to-Many with Movie (One Director can have many Movies)

2. **Movie**
   - Attributes: id, title, releaseDate, description, genre, durationMinutes, rating
   - Relationship: Many-to-One with Director (Many Movies can belong to one Director)

## Technology Stack

- **Backend**: Spring Boot 2.6.3, Java 17
- **Database**: MySQL
- **ORM**: JPA/Hibernate
- **Frontend**: JSP pages, Bootstrap 5
- **Build Tool**: Maven

## Features

### Create Operation
- Add new Directors via form
- Add new Movies via form with Director selection

### Read Operation
- View all Directors with their details
- View all Movies with their respective Directors
- Custom query method to perform inner join between Movies and Directors

### Update Operation
- Edit Director information
- Edit Movie details including changing Director assignment

## How to Run

1. Clone the repository
2. Configure MySQL database credentials in `application.yml`
3. Run the application:
```
mvn spring-boot:run
```
4. Access the application at: http://localhost:8080

## Database Setup

The application is configured to automatically update database schema and populate initial data with 10 sample Directors and Movies on first run.

## Testing

The application includes comprehensive tests for all layers:

### Repository Tests
- Tests for saving, retrieving, and finding entities
- Custom query tests for finding movies with their directors

### Service Tests
- Unit tests using Mockito for service layer logic
- Tests for all CRUD operations

### Controller Tests
- Web layer tests using MockMvc
- Tests for form submission, validation, and view rendering

### Integration Tests
- Tests for entity relationships and database interactions

Run tests with:
```
mvn test
```

## Project Structure

```
src/main/java/com/movieapp/
├── config/
│   └── DataInitializer.java
├── controller/
│   ├── DirectorController.java
│   ├── HomeController.java
│   └── MovieController.java
├── model/
│   ├── Director.java
│   └── Movie.java
├── repository/
│   ├── DirectorRepository.java
│   └── MovieRepository.java
├── service/
│   ├── DirectorService.java
│   ├── DirectorServiceImpl.java
│   ├── MovieService.java
│   └── MovieServiceImpl.java
└── SpringbootJpaEntityManagerApplication.java
``` 