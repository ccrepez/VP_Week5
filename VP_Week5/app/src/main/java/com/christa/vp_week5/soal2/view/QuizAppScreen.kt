package com.christa.vp_week5.soal2.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.christa.vp_week5.soal2.viewmodel.QuizPage
import com.christa.vp_week5.soal2.viewmodel.QuizViewModel

@Composable
fun QuizAppScreen(viewModel: QuizViewModel, modifier: Modifier = Modifier) {
    when (viewModel.page) {
        QuizPage.START -> StartScreen(viewModel, modifier)
        QuizPage.QUIZ -> QuizScreen(viewModel, modifier)
        QuizPage.RESULT -> ResultScreen(viewModel, modifier)
    }
}