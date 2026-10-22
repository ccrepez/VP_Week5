package com.christa.vp_week5.soal2.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.christa.vp_week5.soal2.viewmodel.QuizViewModel

@Composable
fun QuizScreen(viewModel: QuizViewModel, modifier: Modifier = Modifier) {
    val question = viewModel.currentQuestion

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Question ${viewModel.currentIndex + 1}", fontSize = 22.sp)
        Text(question.text, fontSize = 13.sp)
        Spacer(Modifier.height(16.dp))

        question.options.forEachIndexed { index, option ->
            val selected = viewModel.selectedOption == index
            Button(
                onClick = { viewModel.selectOption(index) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selected) Color(0xFF4A5A8A) else Color(0xFFDDE1F5),
                    contentColor = if (selected) Color.White else Color.Black
                ),
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                Text(option)
            }
        }
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = viewModel::next,
            enabled = viewModel.selectedOption != null,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A5A8A)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (viewModel.isLastQuestion) "Finish" else "Next")
        }
    }
}