package com.christa.vp_week5.soal2.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.christa.vp_week5.soal2.viewmodel.QuizViewModel

@Composable
fun ResultScreen(viewModel: QuizViewModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quiz Finished!", fontSize = 22.sp)
        Text("Your Score: ${viewModel.score} / ${viewModel.questions.size}", fontSize = 15.sp)
        Spacer(Modifier.height(16.dp))

        if (viewModel.wrongAnswers.isEmpty()) {
            Text("Perfect! No wrong answers.", color = Color(0xFF4A5A8A))
        } else {
            viewModel.wrongAnswers.forEach { wrong ->
                Column(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                    Text("Q${wrong.number}: ${wrong.question}", fontSize = 13.sp)
                    Text("Your Answer: ${wrong.yourAnswer}", fontSize = 12.sp, color = Color(0xFFC62828))
                    Text("Correct Answer: ${wrong.correctAnswer}", fontSize = 12.sp, color = Color(0xFF4A5A8A))
                    Spacer(Modifier.height(6.dp))
                    HorizontalDivider()
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = viewModel::restartQuiz,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A5A8A)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Restart Quiz")
        }
    }
}