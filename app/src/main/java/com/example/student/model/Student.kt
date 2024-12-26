package com.example.student.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate=true) val id:Int=0,
    val name:String,
    val age:Int,
    val grade:String,
    val note:Float


    )
