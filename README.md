# Orders Management

A Java desktop application for managing a store's clients, products and orders, with a PostgreSQL database and a Swing GUI. Placing an order checks the stock, updates it and generates a bill automatically.

Built as a university assignment for the **Programming Techniques** course (2026).

## Features

- Full CRUD for **clients** and **products**
- **Placing orders:**
  - the quantity must be positive and enough stock must be available
  - the stock is decreased and a **bill** is created
- Bills are immutable and can only be added or viewed. They are modeled as a Java `record`.
- The GUI has tabs for Clients, Products, Orders and Bills. Tables are built automatically from the model classes using reflection.

## Design highlights

- **Generic DAO with reflection:** `AbstractDAO<T>` finds the entity type through `ParameterizedType` and builds `SELECT`, `INSERT`, `UPDATE` and `DELETE` queries from the class fields. `ClientDAO`, `ProductDAO` and `OrderDAO` therefore need almost no code of their own.
- **Reflection-based table model:** `ReflectionTableModel.createTableModel(List<T>)` builds the columns and rows of a `JTable` for any entity type.
- **Layered architecture:**

| Package | Responsibility |
|---|---|
| `model` | `Client`, `Product`, `Orders`, `Bill` (record) |
| `dataAccessLayer` | Generic `AbstractDAO<T>` plus the specific DAOs |
| `businessLayer` | Validation and business rules (`OrderBLL.placeOrder`, …) |
| `presentation` | Swing GUI and the reflection-based table model |
| `connection` | `ConnectionFactory` (JDBC connection) |

- Javadoc for the whole project is included in [`javadoc/`](javadoc), and UML diagrams are in [`src/main/resources`](src/main/resources).

## Tech stack

Java 25 · Swing · JDBC · PostgreSQL · Maven · Javadoc

## Setup

1. **Create the database** and load the schema with sample data:

   ```bash
   createdb orders_management
   psql -d orders_management -f database.sql
   ```

2. **Configure the connection.** Copy the example config and fill in your PostgreSQL password:

   ```bash
   cp config.example.properties config.properties
   ```

   `config.properties` is git-ignored. You can also use the environment variables `DB_URL`, `DB_USER` and `DB_PASSWORD` instead.

3. **Run:**

   ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="org.example.main.Main"
   ```
