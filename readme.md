# Data-Access-Layer-And-Service-Layer
- A SpringBoot based project to learn the Data Access and Service layer implementations using JPA and Hibernate
- This project builds a simple movie ticket booking app to learn these concepts

# Notes
## JDBC API
- Java Database Connectivity API provides a standardized interface to work with the Database related activities
- A collection of interfaces that are implemented by Database Vendors (Oracle, MySQL, PostgreSQL etc.) to provide the JDBC Drivers 
- Without JDBC API, the apps become tightly-coupled with underlying Database implementation and makes switching to a different Database very difficult

## JPA
- Java Persistence API is a standard that allows us to bind Java objects to records in a relational database.
- It's an approach to Object Relationship Mapping(ORM), allowing programmers to perform CRUD operations on a relational database using Java objects.
- There are many JPA providers in the market like Hibernate, EclipseLink, or OpenJPA.
- JPA, in general, and Hibernate, in particular, which depends on JDBC internally, offer many advantages
  * Fetch entities, DTOs, or hierarchical parent-child DTO projections
  * Enable JDBC batching without changing the data access code
  * Support for optimistic or pessimistic locking
- Useful annotations
  * @Entity
  * @Id (for Primary Keys)
  * @Table (for providing the table name)
  * @Column (for customizing column attributes)
    - name
    - length
    - nullable
    - unique
  * @GeneratedValue
    - IDENTITY (auto-increment)
    - SEQUENCE (uses a database sequence)
    - TABLE (uses the underlying database table to ensure uniqueness)
    - AUTO (delegates the decision to the underlying database to decides the best strategy)

## Spring ORM
- It's a Spring module that helps in integrating Spring applications with JPA/Hibernate
- This enables us to work with ORM tools whilst taking advantage of Springs' IoC/DI capabilities
