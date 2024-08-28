package dev.android.allecheq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dev.android.allecheq.presentation.navigations.NavigationGraph
import dev.android.allecheq.presentation.screens.HomeScreen
import dev.android.allecheq.presentation.screens.OnboardingScreen1
import dev.android.allecheq.presentation.screens.OnboardingScreen2
import dev.android.allecheq.ui.theme.AlleCheqTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // colors for status bar
        // val backgroundLight = Color(0xFFFAF8FF).toArgb()
        // val backgroundDark = Color(0xFF121318).toArgb()
        enableEdgeToEdge(
            // statusBarStyle = SystemBarStyle.auto(backgroundLight, backgroundDark),
        )

        setContent {
            AlleCheqTheme {
                // Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                //
                //     HomeScreen(modifier = Modifier.padding(paddingValues))
                // }
                NavigationGraph()
            }
        }
    }
}

