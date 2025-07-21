package com.example.lgs_app.pages

import SmoothLineChart
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lgs_app.R
import com.example.lgs_app.models.DataPoint
import com.example.lgs_app.models.Probe
import com.example.lgs_app.services.AlternativeDatePickerModal
import com.example.lgs_app.services.formatDate

@SuppressLint("DefaultLocale")
@Composable
fun GraphPage(paddingValues: PaddingValues) {
  val probes = listOf(Probe("Probe 1", 20.0, 46.0, -2.0), Probe("Probe 2", 20.0, 30.0, -2.0))
  var selectedChart by remember { mutableStateOf("Probe 1") }
  var selectedDate by remember { mutableStateOf<Long?>(System.currentTimeMillis()) }
  var showModal by remember { mutableStateOf(false) }
  val lineData = listOf(
    listOf(
      DataPoint(1f, 150f), DataPoint(2f, 165f), DataPoint(3f, 142f),
      DataPoint(4f, 178f), DataPoint(5f, 185f), DataPoint(6f, 172f),
      DataPoint(7f, 188f), DataPoint(8f, 195f), DataPoint(9f, 203f)
    ),
    listOf(
      DataPoint(1f, 20f), DataPoint(2f, 45f), DataPoint(3f, 30f),
      DataPoint(4f, 70f), DataPoint(5f, 50f), DataPoint(6f, 85f),
      DataPoint(7f, 65f), DataPoint(8f, 40f), DataPoint(9f, 75f)
    )
  )

  Column(modifier = Modifier
    .fillMaxSize()
    .padding(paddingValues)) {
    // ปุ่มสำหรับเลือกประเภทกราฟ
    Row(modifier = Modifier
      .fillMaxWidth()
      .padding(bottom = 16.dp, top = 10.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
      Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.align(Alignment.TopStart), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          probes.forEach { probe -> Button(
              onClick = { selectedChart = probe.name },
              shape = RoundedCornerShape(10.dp),
              colors = ButtonDefaults.buttonColors(containerColor =  if (selectedChart == probe.name) Color.Blue.copy(alpha = 0.7f) else Color.Gray)
            ) {
              Icon(modifier = Modifier.size(20.dp), tint = Color.White, painter = painterResource(id = R.drawable.thermometer), contentDescription = "")
              Spacer(modifier = Modifier.width(10.dp))
              Text(probe.name)
            }
          }
        }
        Text(formatDate(selectedDate), modifier = Modifier.align(Alignment.Center), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Button(modifier = Modifier.align(Alignment.TopEnd), shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.White), onClick = { showModal = true }) {
          Text("Select date", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Blue.copy(alpha = 0.7f))
        }
      }
    }

    if (showModal) {
      AlternativeDatePickerModal(onDateSelected = { selectedDate = it }, onDismiss = { showModal = false })
    }

    // แสดงกราฟ
    Card(modifier = Modifier.fillMaxHeight(0.8f)) {
      probes.forEachIndexed { index, probe ->
        if (selectedChart == probe.name) {
          SmoothLineChart(data = lineData[index], modifier = Modifier.padding(16.dp), showGrid = true, lineColor = Color.Cyan)
        }
      }
    }

    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
      Row() {
        Image(painter = painterResource(id = if (selectedChart == "Probe 1") R.drawable.graph1 else R.drawable.graph2), contentDescription = "", modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Text("temp ${selectedChart.split(" ")[1]}", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.W600)
      }
      Card( modifier = Modifier.height(60.dp).width(200.dp), colors = CardDefaults.cardColors(Color.Gray) ) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
          IconButton(onClick = {  }) {
            Icon(painter = painterResource(id = R.drawable.zoom_in), modifier = Modifier.size(30.dp), tint = Color.White.copy(alpha = 0.70f), contentDescription = "")
          }
          IconButton(onClick = {  }) {
            Icon(painter = painterResource(id = R.drawable.zoom_out), modifier = Modifier.size(30.dp), tint = Color.White.copy(alpha = 0.70f), contentDescription = "")
          }
          IconButton(onClick = {  }) {
            Icon(painter = painterResource(id = R.drawable.scale1), modifier = Modifier.size(30.dp), tint = Color.White.copy(alpha = 0.70f), contentDescription = "")
          }
        }
      }
    }
  }
}