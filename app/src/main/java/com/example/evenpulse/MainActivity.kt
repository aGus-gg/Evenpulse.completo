package com.example.evenpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.evenpulse.presentation.navigation.screens.EventPulseApp
import com.example.evenpulse.ui.theme.EvenpulseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvenpulseTheme {
                EventPulseApp()
            }
        }
    }
}
