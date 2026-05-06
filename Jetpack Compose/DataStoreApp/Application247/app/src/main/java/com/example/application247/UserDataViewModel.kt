package com.example.application247

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserDataViewModel(private val userRepository: UserRepository) : ViewModel() {
    val inputText = mutableStateOf("")

    fun updateTextInput(text: String){
        inputText.value = text
    }

    fun updateName(newName: String) {
        viewModelScope.launch {
            userRepository.saveUserName(name = newName)
        }
        inputText.value = ""
    }

    val currentUser: StateFlow<String> = userRepository.readUserName.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "Loading..."
    )
}