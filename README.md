<!-- Banner -->
<p align="center">
  <img src="https://img.shields.io/badge/Java-Project-blue?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/MySQL-Database-green?style=for-the-badge&logo=mysql" />
  <img src="https://img.shields.io/badge/JDBC-Integration-orange?style=for-the-badge" />
  <img src="https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge" />
</p>

<h1 align="center">🚗 Vehicle Rental System using Java & MySQL</h1>
<p align="center">
  A fully functional console-based vehicle rental system built in Java, featuring admin and user panels, queue management, BST-based sorting, payment integration, and JDBC connectivity.
</p>

---

## ✨ Overview

This project simulates a real-world vehicle rental system with a strong emphasis on object-oriented design, custom data structures, and clean CLI interactions. It provides separate workflows for Admin and Users, handles vehicle management, payments, and maintains rental records using a MySQL database.

---

## 🔧 Core Features

- 🧍‍♂️ User Registration with input validation
- 🧑‍💼 Admin & User Panel with role-specific functionality
- 🚘 Add / Edit / Delete / View Vehicles (Admin)
- 📊 View Vehicles Sorted by Rent using Binary Search Tree (BST)
- 🧾 Queue Management for Vehicle Booking (FIFO)
- 💳 Payment via Cash and Credit Card (CVV + Expiry)
- 🔁 Rent & Return a Car functionality
- 🗃️ JDBC-based MySQL Database Integration

---

## 🗃️ Tech Stack

| Language   | Java (JDK 17+) |
|------------|----------------|
| Backend    | MySQL          |
| Connector  | JDBC           |
| UI Type    | Console (CLI)  |
| Structures | Queue, BST     |

---

## 📁 Folder Structure

```
VehicleRentalSystem/
├── src/                      # Core source files
│   ├── App.java              # Entry point with role routing
│   ├── Admin.java            # Admin panel logic
│   ├── BinarySearchTree.java # Vehicle BST structure
│   └── ...
├── lib/                      # External dependencies
│   └── mysql-connector-j-8.3.0.jar
├── bin/                      # Compiled class files (ignored in Git)
├── README.md                 # Project overview
└── .gitignore                # File exclusions
```

---

## 🚀 Getting Started

1. 📥 Clone the repository:
```bash
https://github.com/romilmonpara/Vehicle-Rental-System-Java-JDBC.git
```

2. 🛠 Import into any Java IDE (VSCode, IntelliJ, Eclipse)

3. 🧩 Configure MySQL Database
   - Import the SQL schema (see below)

4. ▶️ Run `App.java` and start using

---

## 📥 For Database & Resources

- 🔐 Database `.sql` file
- 📊 Project Presentation `.ppt`
- 🗂️ Full working project zip (compiled + resources)

📩 Please contact the developer directly.

---

## 👨‍💻 Author

> 🚀 Romil Monpara  
> 🎓 B.Tech IT, LJIET Ahmedabad  
> 🌐 [GitHub Profile »](https://github.com/romilmonpara)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE)

---

⭐ Star this repo if you like it!
