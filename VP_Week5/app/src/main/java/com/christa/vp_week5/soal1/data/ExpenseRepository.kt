package com.christa.vp_week5.soal1.data

import com.christa.vp_week5.soal1.model.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object ExpenseRepository {
    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses.asStateFlow()

    private var lastId = 0L
    fun newId(): Long = ++lastId

    fun add(expense: Expense) {
        _expenses.update { it + expense }
    }

    fun delete(id: Long) {
        _expenses.update { list -> list.filterNot { it.id == id } }
    }

    fun updateAmount(id: Long, amount: Long) {
        _expenses.update { list ->
            list.map { if (it.id == id) it.copy(amount = amount) else it }
        }
    }
}