package com.christa.vp_week5.soal1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.christa.vp_week5.soal1.model.Expense
import java.util.Locale

class ExpenseViewModel : ViewModel() {

    private val _expenses = mutableStateListOf<Expense>()
    val expenses: List<Expense> get() = _expenses

    val totalExpense: Long get() = _expenses.sumOf { it.amount }

    var name by mutableStateOf("")
        private set
    var amount by mutableStateOf("")
        private set
    var nameError by mutableStateOf<String?>(null)
        private set
    var amountError by mutableStateOf<String?>(null)
        private set

    private var nextId = 0L

    fun onNameChange(value: String) {
        name = value
        nameError = null
    }

    fun onAmountChange(value: String) {
        amount = value.filter { it.isDigit() }
        amountError = null
    }

    fun validateAmount(input: String): String? {
        val value = input.toLongOrNull()
        return when {
            input.isBlank() -> "Amount is required"
            value == null -> "Amount must be a number"
            value <= 0 -> "Amount must be greater than 0"
            else -> null
        }
    }

    fun addExpense() {
        nameError = if (name.isBlank()) "Name is required" else null
        amountError = validateAmount(amount)
        if (nameError != null || amountError != null) return

        _expenses.add(Expense(id = nextId++, name = name.trim(), amount = amount.toLong()))
        name = ""
        amount = ""
    }

    fun deleteExpense(id: Long) {
        _expenses.removeAll { it.id == id }
    }

    fun updateAmount(id: Long, input: String): String? {
        val error = validateAmount(input)
        if (error != null) return error

        val index = _expenses.indexOfFirst { it.id == id }
        if (index == -1) return "Expense not found"

        _expenses[index] = _expenses[index].copy(amount = input.toLong())
        return null
    }
}

fun formatRupiah(value: Long): String =
    "Rp " + String.format(Locale.US, "%,d", value).replace(',', '.')