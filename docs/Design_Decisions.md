# MediTrack Design Decisions

This document outlines the core architectural and design choices made during the development of MediTrack.

## 1. Object-Oriented Programming (OOP) Principles
- **Encapsulation:** All fields in entity classes (`Patient`, `Doctor`, etc.) are private and accessed via getters and setters. Validation is handled before setting fields (e.g., via `Validator.java`).
- **Inheritance:** `Patient` and `Doctor` extend the abstract `Person` class, inheriting common properties like `id`, `name`, and `age`, reducing code duplication.
- **Polymorphism:** 
  - Overriding: `calculateBaseAmount()` is overridden in `StandardBill` and `DiscountedBill`.
  - Overloading: Implementing the `match(String query)` method from the `Searchable` interface across different models.
- **Abstraction:** The `Payable` and `Searchable` interfaces define contracts without detailing implementations. The generic `DataStore<T>` abstracts the underlying collection mechanisms.

## 2. Advanced OOP Features
- **Immutability:** `BillSummary.java` is strictly immutable. It has final fields, no setters, and performs a defensive copy on the `Date` field in the constructor and getter.
- **Deep vs. Shallow Cloning:** The `Patient` class overrides `.clone()`. While primitive fields are shallow-copied, the `currentMedications` list is explicitly instantiated as a new list, demonstrating deep copy principles.
- **Enums:** `Specialization` and `AppointmentStatus` provide type safety and restrict inputs to predefined constants.

## 3. Design Patterns Used
1. **Singleton Pattern:** Applied in `IdGenerator.java` to ensure only one instance generates unique IDs across the system, preventing collisions.
2. **Factory Pattern:** `BillFactory.java` is used to construct either a `StandardBill` or a `DiscountedBill` based on the requested type. This hides the instantiation logic from the client (`Main.java`).
3. **Observer Pattern:** The `AppointmentService` maintains a list of `AppointmentObserver`s. When an appointment is scheduled or cancelled, `ConsoleNotificationObserver` is automatically triggered.

## 4. Java 8 Streams and Lambdas
Streams were extensively used in `DoctorService` and `AppointmentService` for:
- Filtering Doctors by specialization.
- Calculating the average consultation fee (`mapToDouble().average()`).
- Computing analytics, specifically grouping appointments by doctor using `Collectors.groupingBy()`.

## 5. File I/O and Persistence
For robust handling of deep object relationships (e.g., an `Appointment` holding references to a `Doctor` and `Patient`), Java Serialization (`ObjectOutputStream`) was utilized. This allows the state of the entire system (`DataStore`) to be flushed to a `.ser` file and perfectly restored via the `--loadData` CLI argument.
