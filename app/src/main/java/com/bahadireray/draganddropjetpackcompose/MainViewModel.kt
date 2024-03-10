package com.bahadireray.draganddropjetpackcompose

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class MainViewModel :ViewModel() {

  var isCurrentlyDragging by mutableStateOf(false)
    private set

  var items by mutableStateOf(emptyList<SkillsItem>())
    private set

  var addedSkills = mutableStateListOf<SkillsItem>()
    private set

  init {
    items = listOf(
      SkillsItem("Android","1", Color.Gray),
      SkillsItem("Jetpack","2", Color.Blue),
      SkillsItem("Compose","3", Color.Green),
    )
  }

  fun startDragging(){
    isCurrentlyDragging = true
  }
  fun stopDragging(){
    isCurrentlyDragging = false
  }

  fun addSkills(skillsItem: SkillsItem){
    println("Added Item")
    addedSkills.add(skillsItem)
  }
}