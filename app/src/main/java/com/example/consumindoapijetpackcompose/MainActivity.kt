package com.example.consumindoapijetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.consumindoapijetpackcompose.ui.theme.ConsumindoApiJetpackComposeTheme
import com.example.consumindoapijetpackcompose.ui.views.ManagerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConsumindoApiJetpackComposeTheme {
                ManagerScreen()
            }
        }
    }
}
