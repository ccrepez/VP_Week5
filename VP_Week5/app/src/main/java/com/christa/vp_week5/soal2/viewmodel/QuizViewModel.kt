package com.christa.vp_week5.soal2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.christa.vp_week5.soal2.data.QuestionRepository
import com.christa.vp_week5.soal2.model.Question
import com.christa.vp_week5.soal2.model.WrongAnswer

enum class QuizPage { START, QUIZ, RESULT }

class QuizViewModel : ViewModel() {

    val totalAvailable: Int
        get() = QuestionRepository.questions.value.size

    var page by mutableStateOf(QuizPage.START)
        private set

    var countInput by mutableStateOf("")
        private set
    var countError by mutableStateOf<String?>(null)
        private set
    val canStart: Boolean
        get() = countInput.isNotBlank() && countError == null

    private var questionCount = 3
    private val answers = mutableListOf<Int>()

    var questions by mutableStateOf<List<Question>>(emptyList())
        private set
    var currentIndex by mutableIntStateOf(0)
        private set
    var selectedOption by mutableStateOf<Int?>(null)
        private set

    val currentQuestion: Question get() = questions[currentIndex]
    val isLastQuestion: Boolean get() = currentIndex == questions.lastIndex

    var score by mutableIntStateOf(0)
        private set
    var wrongAnswers by mutableStateOf<List<WrongAnswer>>(emptyList())
        private set

    fun onCountChange(value: String) {
        countInput = value.filter { it.isDigit() }
        val n = countInput.toIntOrNull()
        countError = when {
            countInput.isBlank() -> null
            n == null -> "Must be a number"
            n < 1 -> "Minimum is 1 question"
            n > totalAvailable -> "Maximum is $totalAvailable questions"
            else -> null
        }
    }

    fun startQuiz() {
        if (!canStart) return
        questionCount = countInput.toInt()
        newRound()
    }

    private fun newRound() {
        questions = QuestionRepository.getRandomQuestions(questionCount)
        currentIndex = 0
        selectedOption = null
        answers.clear()
        page = QuizPage.QUIZ
    }

    fun selectOption(index: Int) {
        selectedOption = index
    }

    fun next() {
        val selected = selectedOption ?: return
        answers.add(selected)
        if (isLastQuestion) {
            finishQuiz()
        } else {
            currentIndex++
            selectedOption = null
        }
    }

    private fun finishQuiz() {
        score = questions.indices.count { answers[it] == questions[it].correctIndex }
        wrongAnswers = questions.indices
            .filter { answers[it] != questions[it].correctIndex }
            .map { i ->
                WrongAnswer(
                    number = i + 1,
                    question = questions[i].text,
                    yourAnswer = questions[i].options[answers[i]],
                    correctAnswer = questions[i].options[questions[i].correctIndex]
                )
            }
        page = QuizPage.RESULT
    }

    fun restartQuiz() = newRound()
}