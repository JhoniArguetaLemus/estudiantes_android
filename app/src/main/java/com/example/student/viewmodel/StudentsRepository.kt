package com.example.student.viewmodel

import com.example.student.model.Student
import com.example.student.model.StudentsDao
import kotlinx.coroutines.flow.Flow

class StudentsRepository(private val studentsDao: StudentsDao) {
    val allStudents: Flow<List<Student>> = studentsDao.getAll()

    suspend fun insertStudent(student: Student){
        studentsDao.insertStudent(student)
    }

    suspend fun deleteStudent(student: Student){
        studentsDao.delete(student)
    }


}