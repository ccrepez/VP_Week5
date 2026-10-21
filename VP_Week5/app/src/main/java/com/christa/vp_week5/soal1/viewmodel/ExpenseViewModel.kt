package com.christa.vp_week5.soal1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christa.vp_week5.soal1.data.ExpenseRepository
import com.christa.vp_week5.soal1.model.Expense
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ExpenseViewModel : ViewModel() {

    val expenses: StateFlow<List<Expense>> = ExpenseRepository.expenses

    val totalExpense: StateFlow<Long> = expenses
        .map { list -> list.sumOf { it.amount } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = expenses.value.sumOf { it.amount }
        )

    var name by mutableStateOf("")
        private set
    var amount by mutableStateOf("")
        private set
    var nameError by mutableStateOf<String?>(null)
        private set
    var amountError by mutableStateOf<String?>(null)
        private set

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

        ExpenseRepository.add(
            Expense(id = ExpenseRepository.newId(), name = name.trim(), amount = amount.toLong())
        )
        name = ""
        amount = ""
    }

    fun deleteExpense(id: Long) {
        ExpenseRepository.delete(id)
    }

    fun updateAmount(id: Long, input: String): String? {
        val error = validateAmount(input)
        if (error != null) return error
        ExpenseRepository.updateAmount(id, input.toLong())
        return null
    }
}

fun formatRupiah(value: Long): String =
    "Rp " + String.format(java.util.Locale.US, "%,d", value).replace(',', '.')