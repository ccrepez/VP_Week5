package com.christa.vp_week5.soal2.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.christa.vp_week5.soal2.viewmodel.QuizViewModel

@Composable
fun StartScreen(viewModel: QuizViewModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mini Quiz", fontSize = 22.sp)
        Text("How many questions do you want? (1 - ${viewModel.totalAvailable})", fontSize = 13.sp)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.countInput,
            onValueChange = viewModel::onCountChange,
            label = { Text("Number of questions") },
            singleLine = true,
            isError = viewModel.countError != null,
            supportingText = viewModel.countError?.let { { Text(it) } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        Button(
            onClick = viewModel::startQuiz,
            enabled = viewModel.canStart,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start Quiz")
        }
    }
}