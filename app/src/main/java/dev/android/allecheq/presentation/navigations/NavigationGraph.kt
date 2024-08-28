package dev.android.allecheq.presentation.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.android.allecheq.presentation.screens.HomeScreen
import dev.android.allecheq.presentation.screens.OnboardingScreen1
import dev.android.allecheq.presentation.screens.OnboardingScreen2

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.OnboardingScreen1,
        modifier = modifier
    ) {
        composable<Routes.OnboardingScreen1> {
            OnboardingScreen1(onClick = {navController.navigate(Routes.OnboardingScreen2)})
        }
        composable<Routes.OnboardingScreen2> {
            OnboardingScreen2(
                onClick = { navController.navigate(Routes.HomeScreen) },
                onBackwardNavigation = {navController.popBackStack()}
            )
        }
        composable<Routes.HomeScreen> {
            HomeScreen(
                onEmergencyClick = { TODO("emergency screen") },
                onScanFoodClick = { TODO("scan food screen") }
            )
        }
    }
}