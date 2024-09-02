package dev.android.allecheq.presentation.navigations

import androidx.annotation.DrawableRes
import dev.android.allecheq.R
import dev.android.allecheq.presentation.utils.Screen

data object BottomNavRoutes{
    internal val ScanScreen = Screen("scan_screen")
    internal val EmergencyScreen = Screen("emergency_screen")
    internal val ProfileScreen = Screen("profile_screen")
}

sealed class Destinations(val screen:Screen) {
    data object ScanScreen : Destinations(BottomNavRoutes.ScanScreen)

    data object EmergencyScreen : Destinations(BottomNavRoutes.EmergencyScreen)

    data object ProfileScreen : Destinations(BottomNavRoutes.ProfileScreen)
}

sealed class BottomNavigationRoutes<Destinations>(
    val name: String,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
    val route: Destinations
) {
    data object Scan : BottomNavigationRoutes<Destinations.ScanScreen>(
        name = "Scan",
        icon = R.drawable.scan_icon,
        selectedIcon = R.drawable.scan_selected_icon,
        route = Destinations.ScanScreen
    )

    data object Emergency : BottomNavigationRoutes<Destinations.EmergencyScreen>(
        name = "Emergency",
        icon = R.drawable.info_icon,
        selectedIcon = R.drawable.info_selected_icon,
        route = Destinations.EmergencyScreen
    )

    data object Profile : BottomNavigationRoutes<Destinations.ProfileScreen>(
        name = "Profile",
        icon = R.drawable.profile_icon,
        selectedIcon = R.drawable.profile_selected_icon,
        route = Destinations.ProfileScreen
    )
}