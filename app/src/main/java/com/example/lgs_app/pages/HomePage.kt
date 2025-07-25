package com.example.lgs_app.pages

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lgs_app.constants.tabsName

@SuppressLint("ContextCastToActivity")
@Composable
fun HomePage(controlRoute: NavHostController) {
  var selectedTabIndex by remember { mutableIntStateOf(0) }
  val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabsName.size })
  LaunchedEffect(selectedTabIndex) {
    pagerState.animateScrollToPage(selectedTabIndex)
  }
  LaunchedEffect(pagerState.currentPage) {
    selectedTabIndex = pagerState.currentPage
  }

  Scaffold(
    containerColor = Color(0xFF29292B),
    topBar = {
      TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color(0xFF343434),
        contentColor = Color.White,
        indicator = { tabPositions -> TabRowDefaults.SecondaryIndicator(modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]), color = Color.White, height = 3.dp) }
      ) {
        tabsName.forEachIndexed { index, title ->
          Tab(selected = selectedTabIndex == index, onClick = { selectedTabIndex = index }, text = { Text(title, fontSize = 14.sp, fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal) })
        }
      }
    }
  ) { paddingValues ->
    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
      when(page) {
        0 -> HomePage(paddingValues)
        1 -> GraphPage(paddingValues)
        2 -> DataTable(paddingValues)
        3 -> SetUpDevice(paddingValues)
        4 -> MessagePage(paddingValues)
        5 -> ManageSim(paddingValues)
        6 -> AdjustPage(paddingValues)
        7 -> Column(modifier = Modifier.padding(paddingValues).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
          val activity = (LocalContext.current as? Activity)
          Button(onClick = { activity?.finish() }, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
            Text("Click here to Exit App", color = Color.Blue.copy(alpha = 0.7f))
          }
        }
      }
    }
  }
}