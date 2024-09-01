package dev.android.allecheq.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.android.allecheq.presentation.utils.VALUE_40

@Composable
fun EmergencyScreen(paddingValues: PaddingValues, modifier: Modifier = Modifier) {
    EmergencyScreenContent(modifier = modifier.padding(paddingValues))
}

@Composable
private fun EmergencyScreenContent(modifier: Modifier = Modifier) {
    // Scaffold (
    //     modifier = modifier,
    //     bottomBar = {
    //         AppBottomNavigation(navController = navController)
    //     }
    // ){
    // }
    Text(
        text = "Emergency Screen",
        fontSize = VALUE_40.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}