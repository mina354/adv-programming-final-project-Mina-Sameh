# Advanced Programming Final Project

## Student Information
- Name: Mina Sameh
- ID: 241008183
- Course: Advanced Programming

## Project Description
This project is about developing a Student Grade Processing System for a university, covering the following sections:

• Encapsulation & OOP Relationships (Section III)

• Abstraction & Polymorphism (Section IV)

• Threading & Synchronization (Section V)

• Exception Handling & File I/O (Java Exceptions)

## How to Compile and Run
1. Open the project in your Java IDE (e.g., IntelliJ IDEA)
2. Ensure all .java files are in the correct package/directory
3. Place input files in:
src/reports/
4. Compile the project:
javac *.java
5. Run the program:
java Main

## Features Implemented
[✔] Encapsulation and OOP Relationships

[✔] Abstraction and Polymorphism

[✔] Exception Handling (as well as Custom Exceptions)

[✔] Multi-threading and Synchronization

[✔] File I/O Operations

[✔] Thread Safety and Deadlock Prevention

## Sample Output
Processed Results:

S001 → Avg: 84.3, Grade: B

S002 → Avg: 91.0, Grade: A

S003 → Avg: 76.5, Grade: C

(Actual results may vary depending on input files)

## Challenges Faced
One of the main challenges was implementing proper synchronization between threads using the producer-consumer model. Ensuring that threads correctly wait and notify each other without causing deadlocks required careful handling of shared resources.

Another challenge was managing thread safety while storing processed results. This was solved using synchronized methods along with a ConcurrentHashMap to allow safe concurrent access.

Additionally, handling invalid data and file-related exceptions in a clean and structured way required the use of custom exceptions and a centralized logging mechanism using the ReportGenerator class.
