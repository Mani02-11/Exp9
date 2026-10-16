<img width="1080" height="2460" alt="1000177366" src="https://github.com/user-attachments/assets/e881c7e0-f701-4082-9bdf-82e2e9119594" /># 📱 Experiment 9 – Data Persistence using SharedPreferences and SQLite

## Develop an Android Application Using SharedPreferences and SQLite Database

---

## 👨‍💻 Author

**Name:** Manikandan P  
**USN:** 25MCAR0186  
**Subject:** Mobile Application Development Lab

---

## 📖 Experiment Description

This experiment demonstrates data persistence in Android using **SharedPreferences** and **SQLite Database** (`SQLiteOpenHelper`) with Kotlin and XML.

The application provides a clean and interactive interface for managing student records. It allows storing and retrieving lightweight user preferences (Student Name and USN) using **SharedPreferences**, as well as performing full CRUD (Create, Read, Delete) operations on structured student records stored in a local **SQLite Database**.

Users can input student details, save and load preferences, insert new student records into the database, view all stored records, delete specific records using USN, and clear input/output fields.

### Scenario

The application simulates a **Student Information Data Management System**.

```text
          Student Data Manager App
                    ↓
            Enter Name and USN
                    ↓
          ┌─────────┴──────────┐
          ↓                    ↓
   SharedPreferences     SQLite Database
          ↓                    ↓
  ┌───────┴───────┐    ┌───────┼───────┐
  ↓               ↓    ↓       ↓       ↓
Save            Load  Insert  View   Delete
Prefs           Prefs Student Records Student
          └───────┬────────────┘
                  ↓
            Display Output
```

---

## 🧠 Concept / Technology Used

### SharedPreferences and SQLite Database

Android provides multiple data storage options depending on application needs. **SharedPreferences** is ideal for saving simple key-value pairs, while **SQLite Database** provides a lightweight, embedded relational database for managing structured data locally.

This application uses the following concepts:

* **SharedPreferences** – Stores key-value pairs persistently across application launches.
* **SQLite Database (`SQLiteOpenHelper`)** – Creates, manages, and executes SQL operations (`CREATE TABLE`, `INSERT`, `QUERY`, `DELETE`) on local database tables.
* **ContentValues & Cursor** – Standard Android API classes used to construct database rows and iterate over query result sets.
* **Toast Messages** – Provides immediate visual feedback to the user upon performing data actions.
* **ScrollView & LinearLayout** – Constructs a structured, user-friendly layout that scales well across screen sizes.
* **Kotlin** – Implements event handling, database queries, and preference management logic.
* **XML** – Defines the user interface components, input fields, buttons, and layout hierarchy.
* **Android Studio** – Used for application development, building, and testing.

---

## 🚀 Features

* 🎓 Student Data Management Interface
* 👤 Input fields for Student Name and USN
* 💾 Save Student Details to SharedPreferences
* 📂 Load Saved Preferences from SharedPreferences
* ➕ Insert Student Records into SQLite Database
* 📜 View All Student Records from SQLite Database
* 🗑️ Delete Specific Student Records by USN
* 🧹 Clear Input Fields and Output Area
* 💬 Status messages via Toasts for user operations
* 📱 Scrollable user interface layout
* 🏷️ Footer displaying student details (Name & USN)

---

# 📂 Project Structure

```text
app/
│
├── manifests/
│   └── AndroidManifest.xml
│
├── java/
│   └── com/
│       └── project/
│           └── exp9/
│               ├── MainActivity.kt
│               └── StudentDatabaseHelper.kt
│
├── res/
│   ├── layout/
│   │   └── activity_main.xml
│   │
│   └── values/
│       ├── colors.xml
│       ├── strings.xml
│       └── themes.xml
│
└── Gradle Scripts
```

---

# 🎨 User Interface

The application features a clean, organized user interface containing:

* Title and Experiment Header (`Student Data Manager`)
* Input fields for **Student Name** and **USN**
* **SharedPreferences Section** with buttons to:
  * Save Preferences
  * Load Preferences
* **SQLite Database Section** with buttons to:
  * Insert Student
  * View All Records
  * Delete by USN
  * Clear Fields
* **Output Display Area** (`TextView`) showing operation results and database records
* Footer displaying author details (**Manikandan P | USN: 25MCAR0186**)

---

# 🧪 Test Cases

## Test Case 1 – Save and Load SharedPreferences

### Objective

To verify that student details (Name and USN) can be saved to and loaded from SharedPreferences.

### Input

1. Enter Name: `Manikandan P` and USN: `25MCAR0186`.
2. Tap **Save Preferences**.
3. Clear fields or relaunch app, then tap **Load Preferences**.

### Expected Result

The student details should be saved in key-value format and loaded back into the input fields and output display area upon tapping Load Preferences.

### Actual Result

The preferences were successfully saved and retrieved from SharedPreferences.

### Status

✅ **Passed**

<img width="400" height="660" alt="1000177366" src="https://github.com/user-attachments/assets/b6025fa4-95ce-4fe3-b329-1c2e1fa2d4e5" />

---

## Test Case 2 – Insert and View Records in SQLite Database

### Objective

To verify that student records can be inserted into the SQLite database and retrieved using View All Records.

### Input

1. Enter Name and USN in the input fields.
2. Tap **Insert Student**.
3. Tap **View All Records**.

### Expected Result

The student record should be inserted into the database table, and tapping View All Records should output all stored student records with auto-generated IDs.

### Actual Result

Records were inserted into the SQLite database and displayed correctly in the output area.

### Status

✅ **Passed**

<img width="400" height="660" alt="1000177368" src="https://github.com/user-attachments/assets/3900863e-22fd-4c1d-9214-52054d2ce4db" />

---

## Test Case 3 – Delete Record by USN and Clear Fields

### Objective

To verify deleting a specific record from the SQLite database using USN and clearing input/output fields.

### Input

1. Enter USN in the USN field and tap **Delete by USN**.
2. Tap **Clear Fields**.

### Expected Result

The record associated with the given USN should be removed from the SQLite database. Tapping Clear Fields should reset the input fields and output TextView.

### Actual Result

Record deletion succeeded and fields were cleared as expected.

### Status

✅ **Passed**

<img width="400" height="660" alt="1000177369" src="https://github.com/user-attachments/assets/572ab56c-48e5-4240-b38b-be80e8376b8e" />
