package com.example.consumindoapijetpackcompose.ui.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.consumindoapijetpackcompose.viewmodel.CharactersViewModel
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerScreen() {
    val viewModel: CharactersViewModel = viewModel()
    val navController = rememberNavController()
    val uiState by viewModel.managerScreenUiState.collectAsState()
    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = uiState.title) })
        }
    ){
        NavHost(navController = navController,
            startDestination = ManagerScreenAction.CHARACTERS.name,
            modifier = Modifier.padding(it)){
            composable(route = ManagerScreenAction.CHARACTERS.name){
                CharactersScreen(charactersViewModel = viewModel, navController = navController)
            }
            composable(route = ManagerScreenAction.DESCRIPTION_CHARACTER.name){
                DescriptionCharacterScreen(viewModel = viewModel, navController = navController)
            }
        }
    }
}

enum class ManagerScreenAction {
    CHARACTERS,
    DESCRIPTION_CHARACTER
}