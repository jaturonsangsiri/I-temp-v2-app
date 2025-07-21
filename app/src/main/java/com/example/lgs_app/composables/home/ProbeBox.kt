package com.example.lgs_app.composables.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProbeBox(title: String, modifier: Modifier = Modifier) {
  Card( modifier = modifier.height(250.dp).padding(10.dp), colors = CardDefaults.cardColors(Color(0xFFDEE2E6).copy(alpha = 0.50f)) ) {
    Box( modifier = Modifier.fillMaxSize() ) {
      Text(title,
        modifier = Modifier.align(Alignment.TopStart).padding(15.dp).padding(horizontal = 8.dp, vertical = 4.dp),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White.copy(alpha = 0.70f)
      )
      TemperatorValue(Modifier.align(Alignment.Center))

      Text("Max",
        modifier = Modifier.align(Alignment.TopEnd).padding(15.dp).background(Color(0xFF0096FF), RoundedCornerShape(4.dp)).padding(horizontal = 8.dp, vertical = 4.dp),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
      )

      Text("Min",
        modifier = Modifier.align(Alignment.BottomEnd).padding(15.dp).background(Color(0xFF0096FF), RoundedCornerShape(4.dp)).padding(horizontal = 8.dp, vertical = 4.dp),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
      )
    }
  }
}