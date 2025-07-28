# ✅ To-Do List Manager

A simple desktop To-Do List app built using **Java Swing** and **MySQL**. It allows users to **add**, **view**, and **delete** tasks. Uses **JDBC** for database connection and works with **MAMP** on macOS.

---

## 📌 Features

- 📋 Add tasks
- 🗑️ Delete tasks
- 🪟 Clean Swing UI
- 🛢️ MySQL backend
- 🔁 Real-time task refresh

---

## 🧱 Technologies Used

- Java (JDK 8+)
- Swing (UI)
- JDBC (Database Connection)
- MySQL (with MAMP)
- MVC Structure

---

## 🗃️ Database Setup

### 1. Open MAMP and phpMyAdmin  
Create database manually:
```sql
CREATE DATABASE todo_list;

USE todo_list;

CREATE TABLE tasks (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);
