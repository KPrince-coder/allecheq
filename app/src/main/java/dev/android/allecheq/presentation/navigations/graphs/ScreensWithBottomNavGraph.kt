package dev.android.allecheq.presentation.navigations.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.android.allecheq.presentation.navigations.Destinations
import dev.android.allecheq.presentation.screens.EmergencyScreen
import dev.android.allecheq.presentation.screens.ProfileScreen
import dev.android.allecheq.presentation.screens.ScanScreen
import dev.android.allecheq.presentation.screens.ScreensWithBottomNavBar

fun NavGraphBuilder.screensWithBottomNavGraph(navController: NavHostController) {
    composable(Destinations.ScanScreen.screen.route) {
        ScreensWithBottomNavBar(
            navController = navController,
            screenContent = { pad ->
                ScanScreen(paddingValues = pad)
            }
        )
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
