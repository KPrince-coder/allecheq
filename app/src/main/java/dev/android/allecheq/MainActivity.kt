package dev.android.allecheq

import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.android.allecheq.presentation.navigations.AppBottomNavigation
import dev.android.allecheq.presentation.navigations.Destinations
import dev.android.allecheq.presentation.navigations.NavigationGraph
import dev.android.allecheq.presentation.navigations.Routes
import dev.android.allecheq.presentation.screens.EmergencyScreen
import dev.android.allecheq.presentation.screens.HomeScreen
import dev.android.allecheq.presentation.screens.ProfileScreen
import dev.android.allecheq.presentation.screens.ScanScreen
import dev.android.allecheq.presentation.screens.ScreensWithBottomNavBar
import dev.android.allecheq.ui.theme.AlleCheqTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            AlleCheqTheme {
                NavigationGraph()
            }
        }
    }
}

@Preview(
    name = "light mode",
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_6
)
@Preview(
    name = "dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_6
)
@Composable
private fun Preview() {
    AlleCheqTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.HomeScreen.screen.route
        ) {
            composable(Routes.HomeScreen.screen.route) {
                HomeScreen(onScanFoodClick = {
                    navController.navigate(Destinations.ScanScreen.screen.route)
                }, onEmergencyClick = {
                    navController.navigate(Destinations.EmergencyScreen.screen.route)
                })
            }

            composable(Destinations.ScanScreen.screen.route) {
                // ScreensWithBottomNavBar(
                //     navController = navController,
                //     screenContent = { pad ->
                //         ScanScreen(paddingValues = pad, modifier = Modifier.systemBarsPadding())
                //     }
                // )
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AppBottomNavigation(navController = navController)
                    }
                ) {
                    ScanScreen(modifier = Modifier.padding(it), paddingValues = it)
                    // ScreensWithBottomNavGraph(navController = navController, modifier = Modifier.padding(it))
                }
            }

            composable(Destinations.ProfileScreen.screen.route) {
                ScreensWithBottomNavBar(
                    navController = navController,
                    screenContent = { pad ->
                        ProfileScreen(paddingValues = pad)
                    }
                )
            }

            composable(Destinations.EmergencyScreen.screen.route) {
                ScreensWithBottomNavBar(
                    navController = navController,
                    screenContent = { pad ->
                        EmergencyScreen(paddingValues = pad)
                    }
                )
            }
        }
    }
}