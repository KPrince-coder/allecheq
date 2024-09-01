package dev.android.allecheq.presentation.navigations

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.android.allecheq.presentation.navigations.graphs.homeScreenGraph
import dev.android.allecheq.presentation.navigations.graphs.onboardingScreensGraph
import dev.android.allecheq.presentation.navigations.graphs.screensWithBottomNavGraph

/**
 * @description
 * The navigation system of the app
 */
@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.OnboardingScreen1.screen.route,
        modifier = modifier
    ) {
        homeScreenGraph(navController = navController)
        onboardingScreensGraph(navController = navController)
        screensWithBottomNavGraph(navController = navController)
    }
}