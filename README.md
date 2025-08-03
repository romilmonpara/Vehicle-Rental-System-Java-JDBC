# 🚗 Vehicle Rental System in Java

A simple **Vehicle Rental System** built using **Java**, **JDBC**, and **Data Structures** (Binary Search Tree & Queue). This project simulates the functionality of a real-world vehicle rental system, focusing on console-based interaction.

---

## 📌 Features

- ✅ **User Registration**
  - Fields: Name, Password, Mobile Number, License Number, Email, City
  - Validations included (email, mobile number, password)
  - Custom exceptions for invalid input (e.g., digits in name)

- ✅ **Display Vehicles**
  - Uses a **Binary Search Tree (BST)** to display vehicles sorted by rental price in descending order.

- ✅ **Vehicle Rental Requests**
  - Handled using a **Queue** structure to serve on a **First-Come, First-Served** basis.

- ✅ **Database Integration (JDBC)**
  - Stores user data and vehicle information in a relational database.

- ❌ **Return Car Functionality**
  - Not implemented intentionally for this version.

---

## 🛠️ Technologies Used

- Java (Core Java, OOP)
- JDBC (Java Database Connectivity)
- MySQL (Backend Database)
- Data Structures: Queue, Binary Search Tree (BST)

---

## 📂 Project Structure

```
VehicleRentalSystem/
├── bin/                                # Compiled .class files (not required to upload)
│   ├── Admin.class
│   ├── App.class
│   ├── BinarySearchTree.class
│   └── ...
│
├── lib/                                # External libraries
│   └── mysql-connector-j-8.3.0.jar
│
├── src/                                # Source code files
│   ├── Admin.java
│   ├── App.java
│   ├── BinarySearchTree.java
│   └── ...
│
├── README.md                           # Project documentation
└── .gitignore                          # (Recommended) Ignore bin/ and class files
```

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/vehicle-rental-system-java.git
   cd vehicle-rental-system-java
   ```

2. Import the project into your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).

3. Set up your **MySQL database** and tables.

4. Update your database credentials in `DBConnection.java`.

5. Compile and run the project.

---

## 📥 Database Setup

For the database `.sql` file, **please contact the developer.**

---

## 🧑‍💻 Author

**Romil Monpara**  
B.Tech IT, LJIET Ahmedabad  
[GitHub](https://github.com/romilmonpara)

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
