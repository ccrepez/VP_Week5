package com.christa.vp_week5.soal1.view

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import com.christa.vp_week5.soal1.model.Expense

@Composable
fun EditAmountScreen(
    expense: Expense,
    onDismiss: () -> Unit,
    onSave: (String) -> String?
) {
    var input by remember { mutableStateOf(expense.amount.toString()) }
    var error by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit ${expense.name}") },
        text = {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it.filter { c -> c.isDigit() }; error = null },
                label = { Text("Amount of Expense") },
                singleLine = true,
                isError = error != null,
                supportingText = error?.let { { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        },
        confirmButton = {
            TextButton(onClick = {
                val result = onSave(input)
                if (result == null) onDismiss() else error = result
            }) { Text("Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}