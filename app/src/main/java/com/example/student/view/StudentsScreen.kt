package com.example.student.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.student.model.Student
import com.example.student.viewmodel.StudentsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsView(viewModel: StudentsViewModel){
    val students by viewModel.students.collectAsState()
    var name by remember {mutableStateOf("")}
    var age by remember {mutableStateOf("")}
    var grade   by remember {mutableStateOf("")}
    var note by remember {mutableStateOf("")}

    val keyboardController = LocalSoftwareKeyboardController.current




    Column (
        modifier = Modifier.fillMaxSize()
            .padding(top = 50.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        Text("Students",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp)
            )
        OutlinedTextField(
            value=name,
            onValueChange = {name=it},
            label={Text("Name")},
            textStyle = TextStyle(Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Black
            )
        )
        Spacer(modifier=Modifier.height(10.dp))

        OutlinedTextField(
            value=age,
            onValueChange = {age= it.toInt().toString() },
            label={Text("Age")},
            textStyle = TextStyle(Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Black
            )
        )
        Spacer(modifier=Modifier.height(10.dp))

        OutlinedTextField(
            value=grade ,
            onValueChange = {grade=it},
            label={Text("Grade")},
            textStyle = TextStyle(Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Black
            )

        )
        Spacer(modifier=Modifier.height(10.dp))

        OutlinedTextField(
            value=note,
            onValueChange = {note= it },
            label={Text("Note")},
            textStyle = TextStyle(Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Black
            )
        )
        Spacer(modifier=Modifier.height(10.dp))

        Button(
            onClick = {
                viewModel.insertStudent(Student(name=name, age=age.toInt(), grade=grade, note=note.toDouble().toFloat()))
                name=""
                age=""
                grade=""
                note=""
                keyboardController?.hide()

            },
            shape= CircleShape,
            modifier = Modifier
                .width(200.dp)
            ,
            colors=ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {

            Text("Save")

        }

        Spacer(modifier=Modifier.height(10.dp))

        LazyColumn{
           items(students){student->
              itemCard(student, viewModel=viewModel)


           }
        }

    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun itemCard(student: Student, viewModel:StudentsViewModel){

    var openDialog by remember { mutableStateOf(false) }

    Card(

        colors = CardDefaults.cardColors(
            containerColor = Color.Blue,
            contentColor = Color.White
        ),
        modifier=Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .padding(top = 5.dp)

    ){
       Row(
           modifier=Modifier.padding(10.dp)
               .fillMaxWidth()
           ,
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically

       ) {
           Text(student.name)
           Text(student.age.toString())
           Text(student.grade)
           Text(student.note.toString())
           IconButton(onClick = {
               openDialog=true


           }){
              Icon(Icons.Default.Delete, contentDescription = "Delete")

           }
       }
    }

    if(openDialog){
        AlertDialog(
            onDismissRequest = { openDialog=false },
            title={
                Text(text = "Atención")
            },
            text={
                Text(text = "¿Deseas eliminar este estudiante?")

            },
            confirmButton = {
                Button(onClick = {
                    viewModel.deleteStudent(student)
                    openDialog=false
                },
                    colors=ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                    ){
                    Text(text = "Aceptar")
                }
            },
            dismissButton = {
                Button(onClick = { openDialog=false },
                    colors=ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White

                    )) {
                    Text(text = "Cancelar")
                }

            }

        )


    }

}


