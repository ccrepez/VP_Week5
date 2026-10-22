package com.christa.vp_week5.soal2.model

data class Question(
    val text: String,
    val options: List<String>,
    val correctIndex: Int
)