package com.bahadireray.draganddropjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bahadireray.draganddropjetpackcompose.ui.theme.DragAndDropJetpackComposeTheme

class MainActivity : ComponentActivity() {

  private val viewModel = MainViewModel()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      DragAndDropJetpackComposeTheme {
        DragableScreen(
          modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.8f))
        ) {
          MainScreen(viewModel)
        }
      }
    }
  }
}