# School Fees Payment Tracking System

## Project Description

The **School Fees Payment Tracking System** is a GUI-based Java application developed to help schools record, monitor, and manage student school fees payments. The system allows users to add student payment records, calculate outstanding balances, view payment status, update payment information, and search for student fee records.

This project was individually developed for **INF811D: Object-Oriented Programming** as a semester term paper/practical project.

## Features

* Add student fee payment records
* Store student details such as student ID, name, and programme/class
* Record total school fees and amount paid
* Automatically calculate outstanding balance
* Display payment status as:

  * Fully Paid
  * Partially Paid
  * Not Paid
* Search student payment records
* Update payment information
* Validate user input
* Display records using a graphical user interface

## Technologies Used

* Java
* Java Swing
* Object-Oriented Programming
* IntelliJ IDEA
* ArrayList collection

## Object-Oriented Programming Concepts Applied

### Encapsulation

In the Student, Person, and Payment classes, attributes such as the student ID, name, total fees, and amount paid are declared as private. These fields are accessed and modified only through public getter and setter methods, which protects the student and payment data from direct or invalid modification from outside the class.

### Inheritance

The Student class extends the base Person class, inheriting common properties such as ID and name. In the same way, the three payment types — CashPayment, BankTransferPayment, and MobileMoneyPayment — all extend the base Payment class, inheriting its shared attributes and behaviour instead of repeating the same code in each class.

### Polymorphism

Each payment subclass (CashPayment, BankTransferPayment, and MobileMoneyPayment) overrides the processPayment() method inherited from the Payment class. This means the same processPayment() call behaves differently depending on the payment type selected, allowing the system to handle all payment methods through a single, consistent interface.

### Abstraction

The Payment class defines the common structure and operations shared by all payment types, such as calculating balances and processing a payment, while each subclass provides its own specific implementation. This hides the internal details of how each payment type works and exposes only the operations the rest of the system needs.

## Project Structure

```text
SchoolFeesPaymentTrackingSystem/
│
├── src/
│   ├── SchoolFeesApp.java              # Application entry point (main method)
│   ├── MainFrame.java                  # Main window, tabs, GUI components & event handling
│   │
│   ├── Person.java                     # Base class for people in the system
│   ├── Student.java                    # Student details (extends Person)
│   ├── Admin.java                      # Administrative user
│   │
│   ├── Payment.java                    # Base payment class / abstraction
│   ├── CashPayment.java                # Cash payment (extends Payment)
│   ├── BankTransferPayment.java        # Bank transfer payment (extends Payment)
│   ├── MobileMoneyPayment.java         # Mobile money payment (extends Payment)
│   │
│   ├── FeeInvoice.java                 # Combines a Student with their Payment record
│   ├── FeeManager.java                 # Manages all fee records (add, search, update, list)
│   │
│   ├── DuplicateStudentException.java  # Thrown when a Student ID already exists
│   ├── InvalidAmountException.java     # Thrown for non-numeric / negative amounts
│   ├── OverpaymentException.java       # Thrown when a payment exceeds the balance
│   └── StudentNotFoundException.java   # Thrown when a searched Student ID is missing
│
├── screenshots/                        # Application screenshots used in this README
├── README.md                           # Project documentation (this file)
└── .gitignore
```

## How to Run the Application

1. Download or clone this repository.
2. Open the project in a Java IDE such as IntelliJ IDEA, NetBeans, Eclipse, or VS Code.
3. Make sure Java is installed on your computer.
4. Open the `src` folder.
5. Run the `SchoolFeesApp.java` file.
6. Use the graphical interface to manage student fee payment records.

## Sample Use of the System
1. Enter the student ID, student name, programme/class, level/year, contact number, and total fees due.
2. Click the **Register Student** button.
3. Go to the **Record Payment** section.
4. Enter the student ID, amount paid, payment method, cashier name, mobile number, and transaction ID.
5. The system calculates the outstanding balance automatically.
6. The payment status is displayed as Fully Paid, Outstanding, or Not Paid.
7. Use the search function to find a student record.

## ScreenShots
<img width="959" height="508" alt="Single Search" src="https://github.com/user-attachments/assets/284b5d67-4997-4623-840c-591ff6d8fe14" />
<img width="948" height="500" alt="Registering Student" src="https://github.com/user-attachments/assets/9717dda4-5633-425f-9986-9a085a7e190a" />
<img width="948" height="506" alt="Record Payment" src="https://github.com/user-attachments/assets/798347e8-cb30-4fb3-91b6-d0c2a97d664b" />
<img width="956" height="495" alt="Payment History" src="https://github.com/user-attachments/assets/d14c8167-60df-42e8-96a1-e197eb130a0b" />
<img width="959" height="507" alt="Error Message" src="https://github.com/user-attachments/assets/49b91cf9-0df1-43ee-a601-e7168b3419dc" />
<img width="953" height="495" alt="Dashboard" src="https://github.com/user-attachments/assets/0ca69ffe-7780-45ea-8553-3ca226b34e07" />
<img width="947" height="505" alt="All Search" src="https://github.com/user-attachments/assets/89f661cb-1415-4263-b6b1-2a1f5feb3bcb" />


## Author
**Name:** Isaac Otabil  
**Index Number:** MS/ITE/25/0014  
**Programme:** MSc Information Technology  
**Course:** INF811D - Object-Oriented Programming  
**Institution:** University of Cape Coast  

## GitHub Repository

Link: https://github.com/OtabilIsaac19/SchoolFeesPaymentTrackingSystem


## Conclusion

The School Fees Payment Tracking System provides a simple and organized way to manage student school fees payments. It improves accuracy, reduces manual record-keeping errors, and demonstrates the practical application of Object-Oriented Programming concepts in Java.
