package com.example.consumindoapijetpackcompose.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.consumindoapijetpackcompose.data.Character
import com.example.consumindoapijetpackcompose.viewmodel.CharactersViewModel

@Composable
fun CharactersScreen(
    charactersViewModel: CharactersViewModel,
    navController: NavController
) {
    val uiState by charactersViewModel.charactersScreenUiState.collectAsState()
    when(uiState){
        is CharactersUiState.Loading -> {
            LoadingScreen()
        }
        is CharactersUiState.Success -> {
            val characters = (uiState as CharactersUiState.Success).characters
            CharactersList(characters = characters, charactersViewModel, navController)
        }
        is CharactersUiState.Error -> {
            ErrorScreen()
        }
    }
    
}

@Composable
fun LoadingScreen() {
    Text(text = "Loading...")
    
}

@Composable
fun ErrorScreen() {
    Text("ERROR")
}

@Composable
fun CharactersList(characters: List<Character>, viewModel: CharactersViewModel, navController: NavController) {
    println(characters)
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(characters) { character ->
            CharacterCard(character = character, onClick = {
                viewModel.navigateToDescriptionCharacterScreen(navController = navController, character = character)
            })
        }

    }
    
}

@Composable
fun CharacterCard(character: Character, onClick: () -> Unit = {}) {
    println(character)
    val density = LocalDensity.current.density
    val width = remember {
        mutableStateOf(0F)
    }
    val height = remember {
        mutableStateOf(0F)
    }
    Card(
        modifier = Modifier.padding(6.dp).clickable {
            onClick()
        },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(){
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(character.image).crossfade(true).build(), contentDescription = null,
                placeholder = painterResource(id = android.R.drawable.ic_menu_report_image),
                contentScale = ContentScale.Crop,
                modifier =  Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        width.value = it.size.width / density
                        height.value = it.size.height / density
                    })
                Box(
                    modifier = Modifier.size(
                        width.value.dp,
                        height.value.dp
                    ).background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 100F,
                            endY = 10000F
                        )
                    )
                ){}
            Text(
                text = character.nickname,
                modifier = Modifier.align(Alignment.BottomCenter),
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            )

        }

    }

}
