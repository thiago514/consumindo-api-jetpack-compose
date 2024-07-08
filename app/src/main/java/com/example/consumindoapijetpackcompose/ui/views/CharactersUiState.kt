package com.example.consumindoapijetpackcompose.ui.views

import com.example.consumindoapijetpackcompose.data.Character

sealed interface CharactersUiState {
    object Loading : CharactersUiState

    data class Success(val characters: List<Character>) : CharactersUiState

    object Error : CharactersUiState
}