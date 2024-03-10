package com.bahadireray.draganddropjetpackcompose


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
  mainViewModel: MainViewModel
) {

  val screenWidth = LocalConfiguration.current.screenWidthDp

  Column(
    modifier = Modifier
      .background(Color.White)
      .fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(50.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ){
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ){
      mainViewModel.items.forEach { skill ->
        DragTarget(
          dataToDrop = skill,
          viewModel = mainViewModel
        ) {
          Box(
            modifier = Modifier
              .size(Dp(screenWidth / 5f))
              .clip(RoundedCornerShape(15.dp))
              .shadow(5.dp, RoundedCornerShape(15.dp))
              .background(skill.backgroundColor, RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.Center,
          ){
            Text(
              text = skill.name,
              style = MaterialTheme.typography.bodyLarge,
              color = Color.Black,
              fontWeight = FontWeight.SemiBold
            )
          }
        }
      }
    }
    AnimatedVisibility(
      mainViewModel.isCurrentlyDragging,
      enter = slideInHorizontally (initialOffsetX = {it})
    ) {
      DropItem<SkillsItem>(
        modifier = Modifier
          .size(Dp(screenWidth / 3.5f))
      ) { isInBound, skillItem ->
        if(skillItem != null){
          LaunchedEffect(key1 = skillItem){
            mainViewModel.addSkills(skillItem)
          }
        }
        if(isInBound){
          Box(
            modifier = Modifier
              .fillMaxSize()
              .border(
                1.dp,
                color = Color.Red,
                shape = RoundedCornerShape(15.dp)
              )
              .background(Color.Gray.copy(0.5f), RoundedCornerShape(15.dp))
            ,
            contentAlignment = Alignment.Center
          ){
            Text(
              text = "Add skills",
              style = MaterialTheme.typography.bodyLarge,
              color = Color.Black
            )
          }
        }else{
          Box(
            modifier = Modifier
              .fillMaxSize()
              .border(
                1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(15.dp)
              )
              .background(
                Color.Black.copy(0.5f),
                RoundedCornerShape(15.dp)
              ),
            contentAlignment = Alignment.Center
          ){
            Text(
              text = "Add skill",
              style = MaterialTheme.typography.bodyLarge,
              color = Color.Black
            )
          }
        }
      }
    }
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(top = 30.dp)
      ,
      contentAlignment = Alignment.Center
    ){
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp)
          .padding(bottom = 100.dp)
        ,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ){
        Text(
          text = "Added skills",
          color = Color.Black,
          style = MaterialTheme.typography.displaySmall,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.fillMaxWidth(),
          textAlign = TextAlign.Start
        )
        mainViewModel.addedSkills.forEach { skill ->
          Text(
            text = skill.name,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }
  }
}