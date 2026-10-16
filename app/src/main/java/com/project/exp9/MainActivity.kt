package com.project.exp9

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etUsn: EditText
    private lateinit var tvOutput: TextView
    private lateinit var databaseHelper: StudentDatabaseHelper

    private val preferencesName = "StudentPreferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { view, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        etName = findViewById(R.id.etName)
        etUsn = findViewById(R.id.etUsn)
        tvOutput = findViewById(R.id.tvOutput)

        databaseHelper = StudentDatabaseHelper(this)

        val btnSavePrefs = findViewById<Button>(R.id.btnSavePrefs)
        val btnLoadPrefs = findViewById<Button>(R.id.btnLoadPrefs)
        val btnInsert = findViewById<Button>(R.id.btnInsert)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnClear = findViewById<Button>(R.id.btnClear)

        // Save data using SharedPreferences
        btnSavePrefs.setOnClickListener {
            val name = etName.text.toString().trim()
            val usn = etUsn.text.toString().trim()

            if (name.isEmpty() || usn.isEmpty()) {
                showMessage("Enter name and USN")
                return@setOnClickListener
            }

            val preferences = getSharedPreferences(
                preferencesName,
                MODE_PRIVATE
            )

            preferences.edit()
                .putString("student_name", name)
                .putString("student_usn", usn)
                .apply()

            tvOutput.text =
                "SharedPreferences\n\nName: $name\nUSN: $usn"

            showMessage("Preferences saved successfully")
        }

        // Load saved data from SharedPreferences
        btnLoadPrefs.setOnClickListener {
            val preferences = getSharedPreferences(
                preferencesName,
                MODE_PRIVATE
            )

            val name = preferences.getString("student_name", null)
            val usn = preferences.getString("student_usn", null)

            if (name == null || usn == null) {
                showMessage("No saved preferences found")
            } else {
                etName.setText(name)
                etUsn.setText(usn)

                tvOutput.text =
                    "Loaded from SharedPreferences\n\n" +
                            "Name: $name\nUSN: $usn"

                showMessage("Preferences loaded")
            }
        }

        // Insert data into SQLite
        btnInsert.setOnClickListener {
            val name = etName.text.toString().trim()
            val usn = etUsn.text.toString().trim()

            if (name.isEmpty() || usn.isEmpty()) {
                showMessage("Enter name and USN")
                return@setOnClickListener
            }

            val result = databaseHelper.insertStudent(name, usn)

            if (result != -1L) {
                tvOutput.text =
                    "SQLite Database\n\nStudent inserted successfully\n" +
                            "Name: $name\nUSN: $usn"

                showMessage("Student inserted")
            } else {
                showMessage("Insert failed")
            }
        }

        // View all records from SQLite
        btnView.setOnClickListener {
            val records = databaseHelper.getAllStudents()

            tvOutput.text = "SQLite Student Records\n\n$records"
        }

        // Delete a record using USN
        btnDelete.setOnClickListener {
            val usn = etUsn.text.toString().trim()

            if (usn.isEmpty()) {
                showMessage("Enter the USN to delete")
                return@setOnClickListener
            }

            val deletedRows = databaseHelper.deleteStudent(usn)

            if (deletedRows > 0) {
                tvOutput.text =
                    "Student with USN $usn deleted successfully"

                showMessage("Record deleted")
            } else {
                showMessage("No record found for this USN")
            }
        }

        // Clear input fields and output
        btnClear.setOnClickListener {
            etName.text.clear()
            etUsn.text.clear()
            tvOutput.text = "Results will appear here"

            showMessage("Fields cleared")
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroy() {
        databaseHelper.close()
        super.onDestroy()
    }
}