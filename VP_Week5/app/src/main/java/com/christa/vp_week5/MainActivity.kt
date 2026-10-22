package com.christa.vp_week5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.christa.vp_week5.ui.theme.VP_Week5Theme
//uncomment untuk run soal yang mana

//import com.christa.vp_week5.soal1.view.ExpenseScreen //soal1
//import com.christa.vp_week5.soal1.viewmodel.ExpenseViewModel //soal1
import com.christa.vp_week5.soal2.view.QuizAppScreen //soal2
import com.christa.vp_week5.soal2.viewmodel.QuizViewModel //soal2

class MainActivity : ComponentActivity() {
    //uncomment untuk run soal yang mana

    //private val expenseViewModel: ExpenseViewModel by viewModels() //soal1
    private val quizViewModel: QuizViewModel by viewModels() //soal2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VP_Week5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //uncomment untuk run soal yang mana

                    //ExpenseScreen(viewModel = expenseViewModel, modifier = Modifier.padding(innerPadding)) //soal1
                    QuizAppScreen(viewModel = quizViewModel, modifier = Modifier.padding(innerPadding)) //soal2
                }
            }
        }
    }
}