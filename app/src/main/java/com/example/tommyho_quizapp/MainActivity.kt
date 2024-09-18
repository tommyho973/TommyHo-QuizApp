package com.example.tommyho_quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tommyho_quizapp.ui.theme.TommyHoQuizAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TommyHoQuizAppTheme {
                quiz()
            }
        }
    }
}

@Composable
fun quiz(){
    val questions = listOf("What is the capital of France?",
        "Who painted the Mona Lisa?",
        "What is the highest mountain in the world?",
        "How many continents are there?",
        "What is the largest ocean on Earth?",
        "What is the chemical symbol for water?",
        "What is the largest planet in our solar system?",
        "What is the smallest planet in our solar system?",
        "What is the capital of Japan?",
        "What is the largest country by land area?"
        )
    val answers = listOf("Paris",
        "Leonardo da Vinci",
        "Mount Everest",
        "7",
        "Pacific Ocean",
        "H2O",
        "Jupiter",
        "Mercury",
        "Tokyo",
        "Russia"
        )
    var currentQuestion by remember {mutableIntStateOf(0)}
    var answer by remember{mutableStateOf("")}
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Column(){
        Card(modifier = Modifier.padding(16.dp)){
            if(currentQuestion != questions.size) {
                Text(
                    text = questions[currentQuestion],
                    modifier = Modifier.padding(16.dp)
                )
                TextField(value = answer, onValueChange = { answer = it })
                Button(onClick = {
                    if (answer.lowercase() == answers[currentQuestion].lowercase()) {
                        scope.launch {
                            val result = snackbarHostState
                                .showSnackbar(
                                    message = "Correct",
                                    actionLabel = "Next",
                                    duration = SnackbarDuration.Indefinite
                                )
                            when (result) {
                                SnackbarResult.ActionPerformed -> {
                                    answer = ""
                                    currentQuestion++
                                }

                                SnackbarResult.Dismissed -> {

                                }
                            }
                        }
                    } else {
                        scope.launch {
                            val result = snackbarHostState
                                .showSnackbar(
                                    message = "Incorrect",
                                    actionLabel = "Next",
                                    duration = SnackbarDuration.Indefinite
                                )
                            when (result) {
                                SnackbarResult.ActionPerformed -> {
                                    answer = ""
                                    currentQuestion++
                                }

                                SnackbarResult.Dismissed -> {

                                }
                            }
                        }
                    }
                }) {
                    Text(text = "Submit")
                }
            }
            else{
                Text(text = "You have answered all the questions")
                Button(onClick = {currentQuestion = 0}){
                    Text(text = "Restart Quiz")
                }
            }
        }
        SnackbarHost(hostState = snackbarHostState)
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TommyHoQuizAppTheme {

    }
}