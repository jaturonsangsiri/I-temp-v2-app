package com.example.lgs_app.pages

import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lgs_app.constants.minuteOptions
import com.example.lgs_app.models.Probe

@Composable
fun SetUpDevice(paddingValues: PaddingValues) {
  val (selectedOption, onOptionSelected) = remember { mutableStateOf(minuteOptions[2]) }
  val probes = remember { mutableStateOf(listOf(Probe("Fester1", 20.0, 45.0, 2.0), Probe("Fester2", 20.0, 45.0, 2.0))) }
  val context = LocalContext.current

  Column(modifier = Modifier.padding(paddingValues), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
    Row {
      Card(modifier = Modifier.weight(0.3f).height(370.dp).padding(10.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Column(modifier = Modifier.fillMaxSize().padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text("Record Interval", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 5.dp))

          // Radio Button with text
          minuteOptions.forEach { text ->
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start, modifier = Modifier.height(35.dp).fillMaxWidth().selectable(selected = (text == selectedOption), onClick = { onOptionSelected(text) })) {
              RadioButton(selected = (text == selectedOption), onClick = { onOptionSelected(text) }, modifier = Modifier.padding(end = 4.dp), colors = RadioButtonDefaults.colors(selectedColor = com.example.lgs_app.ui.theme.BabyBlue, unselectedColor = Color.DarkGray), interactionSource = remember { MutableInteractionSource() })
              Text(text = text, modifier = Modifier.padding(start = 4.dp))
            }
          }
          Spacer(modifier = Modifier.height(10.dp))

          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button( onClick = { Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show() }, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(contentColor = Color.White.copy(alpha = 0.70f), containerColor = com.example.lgs_app.ui.theme.BabyBlue)) {
              Text("Save")
            }
          }
        }
      }
      Card(modifier = Modifier.weight(0.7f).fillMaxHeight().padding(10.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Column(modifier = Modifier.fillMaxSize().padding(10.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
          Text("Alarm", fontWeight = FontWeight.Bold, fontSize = 20.sp)

          // Probe TextFields
          probes.value.forEachIndexed { index, probe ->
            Column {
              TextField("Probe ${index + 1}: ", probe.name, KeyboardType.Text,
                { newValue ->
                  val updatedProbes = probes.value.toMutableList()
                  updatedProbes[index] = probe.copy(name = newValue)
                  probes.value = updatedProbes
                },
                Modifier.weight(1f)
              )
              Spacer(modifier = Modifier.height(15.dp))

              Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                TextField("Over: ", probe.over.toString(), KeyboardType.Number,
                  { newValue ->
                    val doubleValue = newValue.toDoubleOrNull() ?: 0.0
                    val updatedProbes = probes.value.toMutableList()
                    updatedProbes[index] = probe.copy(over = doubleValue)
                    probes.value = updatedProbes
                  },
                  Modifier.weight(1f)
                )

                TextField("Under: ", probe.under.toString(), KeyboardType.Number,
                  { newValue ->
                    val doubleValue = newValue.toDoubleOrNull() ?: 0.0
                    val updatedProbes = probes.value.toMutableList()
                    updatedProbes[index] = probe.copy(under = doubleValue)
                    probes.value = updatedProbes
                  },
                  Modifier.weight(1f)
                )
              }
            }
          }
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button( onClick = { Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show() }, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(contentColor = Color.White.copy(alpha = 0.70f), containerColor = com.example.lgs_app.ui.theme.BabyBlue)) {
              Text("Save")
            }
          }
        }
      }
    }
  }
}

@Composable
private fun TextField(title: String, value: String, keyboardType: KeyboardType, callback: (String) -> Unit, modifier: Modifier) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Text(title, fontWeight = FontWeight.W600)
    Spacer(modifier = Modifier.width(10.dp))
    OutlinedTextField(
      value = value,
      onValueChange = callback,
      label = { Text("") },
      modifier = Modifier.width(100.dp),
      shape = RoundedCornerShape(6.dp),
      colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = com.example.lgs_app.ui.theme.BabyBlue, focusedLabelColor = com.example.lgs_app.ui.theme.BabyBlue, cursorColor = com.example.lgs_app.ui.theme.BabyBlue),
      keyboardOptions = KeyboardOptions( keyboardType = keyboardType ),
      singleLine = true
    )
  }
}