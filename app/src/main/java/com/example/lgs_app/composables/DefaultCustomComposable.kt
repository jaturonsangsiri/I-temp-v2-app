package com.example.lgs_app.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lgs_app.R
import com.example.lgs_app.ui.theme.BabyBlue

class DefaultCustomComposable {
  @Composable
  fun BuildButton(text: String, bgColor: Color) {
    Button(onClick = {}, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(containerColor = bgColor)) {
      Text(text)
    }
  }

  @Composable
  fun BuildIconButton(text: String, bgColor: Color) {
    Button( onClick = {}, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(containerColor = bgColor)) {
      Row {
        Icon(modifier = Modifier.size(20.dp), tint = Color.White, imageVector = Icons.Default.Email, contentDescription = "")
        Spacer(Modifier.width(8.dp))
        Text(text)
      }
    }
  }

  @Composable
  fun BuildCheckBox(text: String, checker: Boolean, onCheckedChange: ((Boolean) -> Unit)) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 10.dp)) {
      Checkbox(modifier = Modifier.size(24.dp).padding(0.dp), checked = checker, onCheckedChange = onCheckedChange, interactionSource = remember { MutableInteractionSource() })
      Text(text = text, modifier = Modifier.padding(start = 4.dp))
    }
  }

  @Composable
  fun RadioButtonCustom(text: String, selected: Boolean, onClick: (() -> Unit)) {
    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), verticalAlignment = Alignment.CenterVertically) {
      RadioButton(modifier = Modifier.size(24.dp).padding(0.dp), selected = !selected, onClick = onClick)
      Text(text)
    }
  }

  @Composable
  fun BuildAddMinusControl(plusEnable: Boolean, plusClick: () -> Unit, minusEnable: Boolean, minusClick: () -> Unit, value: String) {
    Card(onClick = plusClick, modifier = Modifier.size(36.dp), enabled = plusEnable, colors = CardDefaults.cardColors(containerColor = BabyBlue, disabledContainerColor = Color.Gray)) {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Icon(painter = painterResource(id = R.drawable.minus), contentDescription = "Decrease", modifier = Modifier.size(16.dp), tint = Color.White)
      }
    }
    Spacer(modifier = Modifier.width(10.dp))
    OutlinedCard(modifier = Modifier.widthIn(min = 48.dp), border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)) {
      Text(text = value, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
    }
    Spacer(modifier = Modifier.width(10.dp))
    Card(onClick = minusClick, modifier = Modifier.size(36.dp), enabled = minusEnable, colors = CardDefaults.cardColors(containerColor = BabyBlue, disabledContainerColor = Color.Gray)) {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Increase", modifier = Modifier.size(16.dp), tint = Color.White)
      }
    }
  }
}