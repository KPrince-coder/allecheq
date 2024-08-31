package dev.android.allecheq.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
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
        // ScreensWithBottomNavGraph(navController = navController, modifier = Modifier.padding(it))
    }
}
// @Preview(
//     name = "light mode",
//     showSystemUi = true,
//     showBackground = true,
//     device = Devices.PIXEL_6
// )
// @Preview(
//     name = "dark mode",
//     uiMode = Configuration.UI_MODE_NIGHT_YES,
//     showSystemUi = true,
//     showBackground = true,
//     device = Devices.PIXEL_6
// )
// @Composable
// private fun Preview() {
//     AlleCheqTheme {
//         ScreensWithBottomNavBar(navController = rememberNavController(), screenContent = {
//             ScanScreen(paddingValues = it)
//         })
//     }
// }