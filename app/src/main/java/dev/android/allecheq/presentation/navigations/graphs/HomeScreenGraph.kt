package dev.android.allecheq.presentation.navigations.graphs

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.android.allecheq.presentation.navigations.Destinations
import dev.android.allecheq.presentation.navigations.Routes
import dev.android.allecheq.presentation.screens.HomeScreen

fun NavGraphBuilder.homeScreenGraph(navController: NavHostController) {
    composable(
        route = Routes.HomeScreen.screen.route,
        enterTransition = {
            fadeIn(
                animationSpec = tween(durationMillis = 500, easing = EaseIn)
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(durationMillis = 250, easing = EaseOut)
            )
        }
    ) {
        HomeScreen(
            onScanFoodClick = {
                navController.navigate(Destinations.ScanScreen.screen.route)
            }, onEmergencyClick = {
                navController.navigate(Destinations.EmergencyScreen.screen.route)
            }
        )
    }
}
