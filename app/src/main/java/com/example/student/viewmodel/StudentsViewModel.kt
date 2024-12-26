package com.example.student.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.student.model.Student
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


//se pasa el repositorio como parametro
class StudentsViewModel(private val repository: StudentsRepository):ViewModel() {
    val students:StateFlow<List<Student>> = repository.allStudents.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun insertStudent(student: Student){
        viewModelScope.launch {
            repository.insertStudent(student)
        }
    }
    fun deleteStudent(student: Student){
        viewModelScope.launch {
            repository.deleteStudent(student)

        }
    }


    class  Factory(private val repository:StudentsRepository):ViewModelProvider.Factory{
        override  fun <T:ViewModel> create(modelClass:Class<T>):T{
            if(modelClass.isAssignableFrom(StudentsViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return StudentsViewModel(repository) as T

            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}