package dev.android.allecheq.presentation.navigations

import dev.android.allecheq.presentation.utils.Screen

/**
 * @description
 * Different routes for the application
 */
// @Serializable
// sealed class Routes(val screen: String) {
//     @Serializable
//     data object OnboardingScreen1 : Routes()
//
//     @Serializable
//     data object OnboardingScreen2 : Routes()
//
//     @Serializable
//     data object HomeScreen : Routes()
//
//     @Serializable
//     data object ScreensWithBottomNavBar : Routes()
// }
object ScreensRoute {
    internal val OnboardingScreen1 = Screen("onboarding_screen_1")
    internal val OnboardingScreen2 = Screen("onboarding_screen_2")
    internal val HomeScreen = Screen("home_screen")
    internal val ScreensWithBottomNavBar = Screen("main_screen")
}

sealed class Routes(val screen: Screen) {
    data object OnboardingScreen1 : Routes(ScreensRoute.OnboardingScreen1)

    data object OnboardingScreen2 : Routes(ScreensRoute.OnboardingScreen2)

    data object HomeScreen : Routes(ScreensRoute.HomeScreen)

    data object ScreensWithBottomNavBar : Routes(ScreensRoute.ScreensWithBottomNavBar)
}



