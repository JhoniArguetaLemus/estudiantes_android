package com.example.student

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.student.model.AppDatabase
import com.example.student.ui.theme.StudentTheme
import com.example.student.view.StudentsView
import com.example.student.viewmodel.StudentsRepository
import com.example.student.viewmodel.StudentsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db= AppDatabase.getDatabase(this)
        val studentsDao=db.studentsDao()
        val repository= StudentsRepository(studentsDao)

        enableEdgeToEdge()
        setContent {
            StudentTheme {
                val viewModel= StudentsViewModel(repository)
                StudentsView(viewModel)

            }
        }
    }
}
