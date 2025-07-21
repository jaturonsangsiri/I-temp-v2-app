package com.example.lgs_app.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainTables() {
  val tableData = (1..8).mapIndexed { index, _ ->
    index + 1 to "Item ${index + 1}"
  }
  // The LazyColumn will be our table. Notice the use of the weights below
  LazyColumn (Modifier.fillMaxSize()) {
    // Here is the header
    item {
      Row(Modifier.background(Color.Gray)) {
        TableCell(text = "No.", weight = 0.1f, true)
        TableCell(text = "Date", weight = 0.3f, true)
        TableCell(text = "Time", weight = 0.2f, true)
        TableCell(text = "Temp1", weight = 0.2f, true)
        TableCell(text = "Temp2", weight = 0.2f, true)
      }
    }
    // Here are all the lines of your table.
    items(tableData) {
      val (id, text) = it
      Row(Modifier.fillMaxWidth()) {
        TableCell(text = id.toString(), weight = 0.1f)
        TableCell(text = text, weight = 0.3f)
        TableCell(text = text, weight = 0.2f)
        TableCell(text = text, weight = 0.2f)
        TableCell(text = text, weight = 0.2f)
      }
    }
  }
}

@Composable
private fun RowScope.TableCell(text: String, weight: Float, isTitle: Boolean = false) {
  Text(text = text, Modifier.weight(weight).padding(8.dp), color = if (isTitle) Color.White.copy(alpha = 0.6f) else Color.Black.copy(alpha = 0.7f) )
}