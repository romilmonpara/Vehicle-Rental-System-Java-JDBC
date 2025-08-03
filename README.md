# 🚗 Vehicle Rental System in Java

[![Java](https://img.shields.io/badge/Java-17-blue?style=flat-square&logo=java)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-Database-blue?style=flat-square&logo=mysql)](https://www.mysql.com/)
[![JDBC](https://img.shields.io/badge/JDBC-Connector-green?style=flat-square)](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A complete console-based Java project for managing a vehicle rental system using JDBC and MySQL. This system supports two user panels—Admin and User—and includes complete payment handling (Cash & Credit Card).

---

## 📌 Features

- ✅ User Registration with validation and custom exceptions
- ✅ Admin and User Panels with separate functionality
- ✅ Add / Edit / Delete / View Vehicles (Admin)
- ✅ Browse and Book Vehicles (User)
- ✅ Queue-based rental requests
- ✅ Binary Search Tree (BST) for vehicle sorting by rent
- ✅ Payment through Cash and Credit Card (CVV included)
- ✅ Return Car functionality included
- ✅ MySQL JDBC Integration

---

## 🛠️ Technologies Used

- Java (OOP)
- JDBC (Java Database Connectivity)
- MySQL (Database)
- Data Structures: Queue, Binary Search Tree (BST)
- Console UI (Color-coded)

---

## 📂 Project Structure

```
VehicleRentalSystem/
├── bin/                                # Compiled .class files (ignored)
├── lib/                                # MySQL JDBC connector .jar
│   └── mysql-connector-j-8.3.0.jar
├── src/                                # Source code
│   ├── Admin.java
│   ├── App.java
│   ├── BinarySearchTree.java
│   ├── Payment.java (if separated)
│   └── ...
├── README.md
└── .gitignore
```

---

## 🚀 How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/vehicle-rental-system-java.git
   cd vehicle-rental-system-java
   ```

2. Open in IntelliJ, Eclipse or any Java IDE.

3. Set up your MySQL database and update credentials in DBConnection.java.

4. Add the MySQL JDBC driver (lib/mysql-connector-j-8.3.0.jar) to your classpath.

5. Compile and run the App.java

---

## 📥 Database Setup

For the `.sql` database file, project report/presentation `.ppt`, or complete zip of the working project:  
📩 **Please contact the developer directly.**

---

## 🧑‍💻 Author

**Romil Monpara**  
B.Tech IT, LJIET Ahmedabad  
📧 [GitHub](https://github.com/romilmonpara)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
