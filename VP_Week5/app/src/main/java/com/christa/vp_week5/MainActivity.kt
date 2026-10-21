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
import com.christa.vp_week5.soal1.view.ExpenseScreen
import com.christa.vp_week5.soal1.viewmodel.ExpenseViewModel

class MainActivity : ComponentActivity() {
    private val expenseViewModel: ExpenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VP_Week5Theme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExpenseScreen(
                        viewModel = expenseViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}