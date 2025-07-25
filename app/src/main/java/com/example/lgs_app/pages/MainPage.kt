package com.example.lgs_app.pages

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.TextClock
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.lgs_app.R
import com.example.lgs_app.composables.home.ProbeBox
import com.example.lgs_app.constants.colorRed
import com.example.lgs_app.models.Probe
import com.example.lgs_app.ui.theme.BabyBlue

@Composable
fun HomePage(paddingValues: PaddingValues) {
  val isMute = remember { mutableStateOf(false) }
  val context = LocalContext.current
  val probes = listOf<Probe>(Probe("Probe 1", 25.0, 46.0, -2.0), Probe("Probe 2", 25.0, 30.0, -2.0))

  Column(modifier = Modifier.padding(paddingValues)) {
    // Box Status
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
      Box(modifier = Modifier.size(100.dp).padding(10.dp).clip(CircleShape).background(Color.White))
      Card(modifier = Modifier.width(220.dp).height(80.dp).padding(16.dp), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.6f)), elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
        Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
          Icon(modifier = Modifier.size(35.dp).padding(end = 10.dp), tint = Color.White.copy(alpha = 0.7f), painter = painterResource(id = R.drawable.alarm_clock), contentDescription = "")
          AndroidView(
            factory = { context ->
              TextClock(context).apply {
                format24Hour = "HH:mm:ss"
                format12Hour = null
                textSize = 24f
                setTextColor(context.getColor(R.color.white70))
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                setTypeface(typeface, Typeface.BOLD)
                gravity = Gravity.CENTER
              }
            },
            modifier = Modifier.fillMaxSize()
          )
        }
      }
    }

    // Probs , temperature
    Row(modifier = Modifier.fillMaxWidth()) {
      probes.forEach { probe ->
        ProbeBox(probe.name, modifier = Modifier.weight(1f))
      }
    }
    Spacer(modifier = Modifier.height(20.dp))

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
      Button(modifier = Modifier.height(60.dp), onClick = {
          isMute.value = !isMute.value
          Toast.makeText(context, if (isMute.value) "Mute Alarm Success" else "Unmute alarm Success", Toast.LENGTH_SHORT).show()
        }, shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(contentColor = Color.White.copy(alpha = 0.70f), containerColor = if (isMute.value) BabyBlue else colorRed)
      ) {
        Row {
          Icon(modifier = Modifier.size(30.dp), tint = Color.White, painter = painterResource(id = if (isMute.value) R.drawable.speaker else R.drawable.mute_icon), contentDescription = "")
          Spacer(Modifier.width(8.dp))
          Text(if (isMute.value) "Unmute Alarm" else "Mute alarm", fontSize = 18.sp)
        }
      }
    }
  }
}