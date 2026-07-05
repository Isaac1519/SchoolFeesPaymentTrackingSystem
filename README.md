# School Fees Payment Tracking System

## Project Description

This **School Fees Payment Tracking System** is a GUI-based Java application developed to help schools record, monitor, and manage student school fees payments. The system allows users to add student payment records, calculate outstanding balances, view payment status, and search for student fee information.

This project was individually developed for **INF811D: Object Oriented Programming** as a semester term paper/practical project.

## Features

- Add student fee payment records
- Store student details such as Student ID, name, and programme/class
- Record total school fees and amount paid
- Automatically calculate outstanding balance
- Display payment status as:
  - Fully Paid
  - Partially Paid
  - Not Paid
- Search student payment records
- Update payment information
- Validate user input
- Display records using a graphical user interface

## Technologies Used

- Java
- Java GUI Framework: Java Swing or JavaFX
- Object-Oriented Programming
- IntelliJ IDEA / NetBeans / Eclipse / VS Code

## Object-Oriented Programming Concepts Applied

### Encapsulation

Class attributes are kept private and accessed through getter and setter methods. This protects student and payment data from direct modification.

### Inheritance

The system can use a parent class such as `Person`, with `Student` inheriting common properties like ID and name.

### Polymorphism

Methods such as `displayDetails()` can be overridden in different classes to show different types of information.

### Abstraction

Abstract classes or interfaces can be used to define important payment operations such as calculating balance and checking payment status.

## Suggested Project Structure

```text
SchoolFeesPaymentTrackingSystem/
│
├── src/
│   ├── Main.java
│   ├── Student.java
│   ├── Payment.java
│   ├── FeeRecord.java
│   └── FeeManager.java
│
├── screenshots/
│   ├── dashboard.png
│   ├── add-record.png
│   └── payment-status.png
│
├── README.md
└── School_Fees_Payment_Tracking_System_Technical_Report.docx
```

## How to Run the Application

1. Download or clone this repository.
2. Open the project in your preferred Java IDE, such as IntelliJ IDEA, NetBeans, Eclipse, or VS Code.
3. Make sure Java is installed on your computer.
4. Open the `src` folder.
5. Run the `Main.java` file.
6. Use the graphical interface to manage student fee payment records.

## Sample Use of the System

1. Enter the student ID, student name, programme/class, total fees, amount paid, and payment date.
2. Click the **Add Record** button.
3. The system calculates the outstanding balance automatically.
4. The payment status is displayed as Fully Paid, Partially Paid, or Not Paid.
5. Use the search function to find a student record.

## Screenshots
<img width="959" height="508" alt="Single Search" src="https://github.com/user-attachments/assets/f0374b76-e406-4121-b6f3-318ca9c52945" />
<img width="948" height="500" alt="registrating student" src="https://github.com/user-attachments/assets/2781fde6-4d73-44ca-a2cc-1b2a56cd993d" />
<img width="948" height="506" alt="Record Payment" src="https://github.com/user-attachments/assets/067333a5-e15e-4d9d-a07e-513cbc7e6ca3" />
<img width="956" height="495" alt="Payment History" src="https://github.com/user-attachments/assets/92a315e7-993c-4aba-8662-939a07a5f852" />
<img width="959" height="507" alt="ERROR MESSAGE" src="https://github.com/user-attachments/assets/1b7c05a4-3960-45db-8495-ea4bd5655472" />
<img width="953" height="495" alt="Dashboard" src="https://github.com/user-attachments/assets/37baf683-c427-479a-87b6-50841d4c4b3e" />
<img width="947" height="505" alt="All search" src="https://github.com/user-attachments/assets/0a6630a7-6721-463c-b648-ceca99505a10" />






## Author

**Name:** Isaac Otabil  
**Index Number:** MS/ITE/25/0014

**Programme:** MSc Information Technology  
**Course:** INF811D - Object Oriented Programming  
**Institution:** University of Cape Coast  

## GitHub Repository

GitHub repository link here:https://github.com/OtabilIsaac19/SchoolFeesPaymentTrackingSystem


```text
https://github.com/OtabilIsaac19/SchoolFeesPaymentTrackingSystem
```

## Conclusion

The School Fees Payment Tracking System provides a simple and organized way to manage student school fees payments. It improves accuracy, reduces manual record-keeping errors, and demonstrates the practical application of Object-Oriented Programming concepts in Java.
