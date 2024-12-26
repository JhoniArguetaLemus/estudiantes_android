package com.example.student.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentsDao {
    @Query("SELECT * FROM students")
    fun getAll(): Flow<List<Student>>

    @Delete
   suspend fun delete(student: Student)

    @Query("SELECT * FROM students WHERE id = :id")
   suspend fun getStudentById(id: Int): Student


    @Insert
   suspend fun insertStudent(student: Student)


}