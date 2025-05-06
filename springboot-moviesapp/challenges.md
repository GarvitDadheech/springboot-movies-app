# Challenges Faced During Implementation

## 1. Entity Relationship Mapping

**Challenge**: Setting up the proper bidirectional relationship between Movie and Director entities.

**Solution**: Used `@OneToMany` on the Director entity and `@ManyToOne` on the Movie entity with appropriate cascade settings to ensure that changes propagate properly. Implemented proper handling of the relationship on both sides.

## 2. Date Handling

**Challenge**: Handling date formatting consistently between the database, Java objects, and JSP views.

**Solution**: Used `LocalDate` in the entity classes and implemented proper formatting using JSTL's `fmt` tags in the JSP files to ensure dates are displayed in a user-friendly format.

## 3. Form Binding

**Challenge**: Properly binding complex objects like the Director entity within the Movie form.

**Solution**: Used Spring's form binding capabilities with `itemValue` and `itemLabel` in the JSP form to correctly manage the relationship selection, ensuring that the proper Director entity is associated with each Movie.

## 4. JSP Integration

**Challenge**: Setting up the Spring Boot application to work with JSP views as Spring Boot doesn't include JSP support by default.

**Solution**: Added the necessary dependencies (tomcat-embed-jasper and JSTL) to the pom.xml and configured the ViewResolver in application.yml to properly handle JSP files.

## 5. Data Initialization

**Challenge**: Creating a database initialization approach that populates sample data without duplicating records on application restart.

**Solution**: Implemented a `CommandLineRunner` bean that checks if data already exists before populating the database, ensuring that sample data is only added on first run.

## 6. Custom Query Implementation

**Challenge**: Implementing a custom query to perform inner joins between Movies and Directors.

**Solution**: Used JPQL with the `@Query` annotation to create a custom method in the repository that fetches all movies with their associated directors in a single optimized query.

## 7. Package Structure

**Challenge**: Organizing the application with a proper package structure to follow best practices.

**Solution**: Created separate packages for models, repositories, services, controllers, and configuration to ensure a clean separation of concerns and maintainable codebase. 