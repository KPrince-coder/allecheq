package dev.android.allecheq.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.android.allecheq.presentation.navigations.AppBottomNavigation

@Composable
fun ScreensWithBottomNavBar(
    navController: NavHostController,
    screenContent: @Composable (PaddingValues) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            AppBottomNavigation(navController = navController)
        }
    ) {
        screenContent(it)
    }
}
