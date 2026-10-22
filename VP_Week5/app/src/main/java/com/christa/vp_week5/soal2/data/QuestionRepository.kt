package com.christa.vp_week5.soal2.data

import com.christa.vp_week5.soal2.model.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object QuestionRepository {
    private val _questions = MutableStateFlow(
        listOf(
            Question("Ibu Kota Di Indonesia?", listOf("Surabaya", "Bandung", "Jakarta", "Medan"), 2),
            Question("Ada berapa hari dalam seminggu?", listOf("5", "6", "7", "11"), 2),
            Question("Natal dirayakan pada bulan?", listOf("Agustus", "Oktober", "November", "Desember"), 3),
            Question("Mata Kuliah VP pakai apa?", listOf("Word", "Excel", "Android Studio", "Photoshop"), 2),
            Question("Planet terbesar di tata surya?", listOf("Bumi", "Mars", "Jupiter", "Saturnus"), 2),
            Question("Bahasa utama untuk Android modern?", listOf("Kotlin", "Python", "PHP", "Ruby"), 0),
            Question("Hasil dari 8 x 7?", listOf("54", "56", "64", "48"), 1)
        )
    )
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()

    fun getRandomQuestions(count: Int): List<Question> =
        _questions.value.shuffled().take(count)
}