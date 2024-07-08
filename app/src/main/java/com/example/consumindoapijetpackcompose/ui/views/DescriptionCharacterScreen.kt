package com.example.consumindoapijetpackcompose.ui.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.consumindoapijetpackcompose.data.Character
import com.example.consumindoapijetpackcompose.viewmodel.CharactersViewModel

@Composable
fun DescriptionCharacterScreen(
    viewModel: CharactersViewModel,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val uiState by viewModel.descriptionCharacterScreenUiState.collectAsState()
    BackHandler {
        viewModel.navigateToCharactersScreen(navController = navController)
    }
    DescriptionCharacter(character = uiState.character, modifier = modifier)
}

@Composable
fun DescriptionCharacter(character: Character?, modifier: Modifier = Modifier) {
    if (character == null) {
        return
    }
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(character.image).crossfade(true).build(), contentDescription = null,
            placeholder = painterResource(id = android.R.drawable.ic_menu_report_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8F))
        Divider(thickness = 3.dp)
        Informations(title = "Nome Completo", description = character.fullName)
        Informations(title = "Apelido", description = character.nickname)
        Informations(title = "Casa de Hogwarts", description = character.hogwartsHouse)
        Informations(title = "Interpretado por", description = character.interpretedBy)
        Informations(title = "Data de Nascimento", description = character.birthdate)
        ListInformations(title = "Filhos", description = character.children)
        
    }
}

@Composable
fun Informations(title: String, description: String){
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        Divider(color = Color.Transparent, thickness = 3.dp)
        Text(text = title)
        Text(text = description, fontWeight = FontWeight.Bold)
        Divider(thickness = 3.dp)
    }
}

@Composable
fun ListInformations(title: String, description: List<String>){
    if(description.isEmpty()){
        return
    }
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        Divider(color = Color.Transparent, thickness = 3.dp)
        Text(text = title)
        description.forEach {
            Text(text = it, fontWeight = FontWeight.Bold)
        }
        Divider(thickness = 3.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun DescriptionCharacterScreenPreview() {
    val character = Character(
        fullName = "Harry James Potter",
        nickname = "Harry",
        hogwartsHouse = "Griffindor",
        interpretedBy = "Daniel Radcliffe",
        children = listOf("James Sirius Potter", "Albus Severus Potter", "Lily Luna Potter"),
        image = "https://raw.githubusercontent.com/fedeperin/potterapi/main/public/images/characters/harry_potter.png",
        birthdate = "Jul 31, 1980"
    )
    DescriptionCharacter(character = character)
}