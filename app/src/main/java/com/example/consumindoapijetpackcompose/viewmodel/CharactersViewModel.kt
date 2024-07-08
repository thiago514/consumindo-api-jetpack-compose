package com.example.consumindoapijetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.consumindoapijetpackcompose.data.Character
import com.example.consumindoapijetpackcompose.network.PotterAPI
import com.example.consumindoapijetpackcompose.ui.views.CharactersUiState
import com.example.consumindoapijetpackcompose.ui.views.DescriptionCharacterScreenUiState
import com.example.consumindoapijetpackcompose.ui.views.ManagerScreenAction
import com.example.consumindoapijetpackcompose.ui.views.ManagerScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel: ViewModel(){

    private val _charactersScreenUiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Loading)

    val charactersScreenUiState: StateFlow<CharactersUiState> = _charactersScreenUiState.asStateFlow()

    private val _managerScreenUiState: MutableStateFlow<ManagerScreenUiState> =
        MutableStateFlow(ManagerScreenUiState("Lista de personasagens"))

    val managerScreenUiState: StateFlow<ManagerScreenUiState> = _managerScreenUiState.asStateFlow()

    private val _descriptionCharacterScreenUiState: MutableStateFlow<DescriptionCharacterScreenUiState> =
        MutableStateFlow(DescriptionCharacterScreenUiState(null))

    val descriptionCharacterScreenUiState: StateFlow<DescriptionCharacterScreenUiState> = _descriptionCharacterScreenUiState.asStateFlow()


    init {
        getCharacters()
    }

    private fun getCharacters(){
        viewModelScope.launch {
            try {
                val characters = PotterAPI.retrofitService.getCharacters()
                println(characters)
                _charactersScreenUiState.value = CharactersUiState.Success(characters)
            } catch (e: Exception){
                println("ERROR")
                println(e)
                _charactersScreenUiState.value = CharactersUiState.Error
            }
        }
    }

    fun navigateToDescriptionCharacterScreen(navController: NavController, character: Character){
        _managerScreenUiState.value = ManagerScreenUiState("Descrição do personagem")
        _descriptionCharacterScreenUiState.value = DescriptionCharacterScreenUiState(character)
        navController.navigate(ManagerScreenAction.DESCRIPTION_CHARACTER.name)
    }

    fun navigateToCharactersScreen(navController: NavController){
        _managerScreenUiState.value = ManagerScreenUiState("Lista de personagens")
        _descriptionCharacterScreenUiState.value = DescriptionCharacterScreenUiState(null)
        navController.popBackStack()
    }


}