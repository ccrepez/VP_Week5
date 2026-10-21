package com.christa.vp_week5.soal1.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.christa.vp_week5.soal1.model.Expense
import com.christa.vp_week5.soal1.viewmodel.ExpenseViewModel
import com.christa.vp_week5.soal1.viewmodel.formatRupiah

@Composable
fun ExpenseScreen(viewModel: ExpenseViewModel, modifier: Modifier = Modifier) {
    val expenses by viewModel.expenses.collectAsState()
    val total by viewModel.totalExpense.collectAsState()

    var editing by remember { mutableStateOf<Expense?>(null) }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("Expenses", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Total Expense : ${formatRupiah(total)}", fontSize = 13.sp)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.name,
            onValueChange = viewModel::onNameChange,
            label = { Text("Expense Name") },
            singleLine = true,
            isError = viewModel.nameError != null,
            supportingText = viewModel.nameError?.let { { Text(it) } },
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.Top) {
            OutlinedTextField(
                value = viewModel.amount,
                onValueChange = viewModel::onAmountChange,
                label = { Text("Amount of Expense") },
                singleLine = true,
                isError = viewModel.amountError != null,
                supportingText = viewModel.amountError?.let { { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            FilledTonalIconButton(
                onClick = viewModel::addExpense,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(expenses, key = { it.id }) { expense ->
                ExpenseItem(
                    expense = expense,
                    onEdit = { editing = expense },
                    onDelete = { viewModel.deleteExpense(expense.id) }
                )
            }
        }
    }

    editing?.let { expense ->
        EditAmountScreen(
            expense = expense,
            onDismiss = { editing = null },
            onSave = { input -> viewModel.updateAmount(expense.id, input) }
        )
    }
}