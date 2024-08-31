package dev.android.allecheq.presentation.navigations.graphs

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.android.allecheq.presentation.navigations.Routes
import dev.android.allecheq.presentation.screens.OnboardingScreen1
import dev.android.allecheq.presentation.screens.OnboardingScreen2

fun NavGraphBuilder.onboardingScreensGraph(navController: NavHostController) {
    composable(route = Routes.OnboardingScreen1.screen.route,
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
        OnboardingScreen1(onClick = { navController.navigate(Routes.OnboardingScreen2.screen.route) })
    }
    composable(
        route = Routes.OnboardingScreen2.screen.route,
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
        OnboardingScreen2(
            onClick = { navController.navigate(Routes.HomeScreen.screen.route) },
            onBackwardNavigation = { navController.popBackStack() }
        )
    }
}