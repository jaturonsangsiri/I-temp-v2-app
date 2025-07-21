package com.example.lgs_app.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lgs_app.composables.MainTables
import com.example.lgs_app.R
import com.example.lgs_app.services.AlternativeDatePickerModal
import com.example.lgs_app.services.checkDateIsBefore
import com.example.lgs_app.services.formatDate

@Composable
fun DataTable(paddingValues: PaddingValues) {
  var email by remember { mutableStateOf("test@hotmail.com") }
  var file by remember { mutableStateOf("") }
  var showModal by remember { mutableStateOf(false) }
  var showModal2 by remember { mutableStateOf(false) }
  var selectedStartDate by remember { mutableStateOf<Long?>(System.currentTimeMillis()) }
  var selectedEndDate by remember { mutableStateOf<Long?>(System.currentTimeMillis()) }
  val context = LocalContext.current

  Column(modifier = Modifier.padding(paddingValues), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
    Row {
      Card(modifier = Modifier.weight(0.6f).fillMaxHeight().padding(20.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        MainTables()
      }

      if (showModal) {
        AlternativeDatePickerModal(onDateSelected = { if (!checkDateIsBefore(selectedStartDate, selectedEndDate)) { selectedStartDate = it } }, onDismiss = { showModal = false })
      }

      if (showModal2) {
        AlternativeDatePickerModal(onDateSelected = { if (!checkDateIsBefore(selectedStartDate, selectedEndDate)) { selectedEndDate = it } }, onDismiss = { showModal2 = false })
      }

      Card(modifier = Modifier.weight(0.4f).fillMaxHeight(0.8f).padding(20.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Column(modifier = Modifier.fillMaxSize().padding(10.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
          // Start Date Row
          Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Start Date:", fontSize = 16.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
            Row(modifier = Modifier.clickable { showModal = true }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
              Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(Color.Gray.copy(alpha = 0.2f)).padding(horizontal = 20.dp, vertical = 8.dp)) {
                Text(text = formatDate(selectedStartDate), fontSize = 14.sp, color = Color.Black.copy(alpha = 0.5f))
              }
              Image(painter = painterResource(id = R.drawable.calendar), contentDescription = "Select Start Date", modifier = Modifier.size(30.dp))
            }
          }

          // End Date Row
          Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "End Date:", fontSize = 16.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
            Row(modifier = Modifier.clickable { showModal2 = true }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
              Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(Color.Gray.copy(alpha = 0.2f)).padding(horizontal = 20.dp, vertical = 8.dp)) {
                Text(text = formatDate(selectedEndDate), fontSize = 14.sp, color = Color.Black.copy(alpha = 0.5f))
              }
              Image(painter = painterResource(id = R.drawable.calendar), contentDescription = "Select End Date", modifier = Modifier.size(30.dp))
            }
          }

          // Email textField
          OutlinedTextField(
            value = file,
            onValueChange = { file = it },
            label = { Text("File name") },
            placeholder = { Text("Enter your file") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = com.example.lgs_app.ui.theme.BabyBlue, focusedLabelColor = com.example.lgs_app.ui.theme.BabyBlue, cursorColor = com.example.lgs_app.ui.theme.BabyBlue),
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Email ),
            singleLine = true
          )

          OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Enter your Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = com.example.lgs_app.ui.theme.BabyBlue, focusedLabelColor = com.example.lgs_app.ui.theme.BabyBlue, cursorColor = com.example.lgs_app.ui.theme.BabyBlue),
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Email ),
            singleLine = true
          )

          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button( onClick = { Toast.makeText(context, "Send email success", Toast.LENGTH_SHORT).show() }, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(contentColor = Color.White.copy(alpha = 0.70f), containerColor = com.example.lgs_app.ui.theme.BabyBlue)) {
              Row {
                Icon(modifier = Modifier.size(20.dp), tint = Color.White, imageVector = Icons.Default.Email, contentDescription = "")
                Spacer(Modifier.width(8.dp))
                Text("E-mail")
              }
            }
          }
        }
      }
    }
  }
}