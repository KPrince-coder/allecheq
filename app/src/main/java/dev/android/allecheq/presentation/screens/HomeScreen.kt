package dev.android.allecheq.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.android.allecheq.R
import dev.android.allecheq.presentation.screens.components.AppNameAndMotto
import dev.android.allecheq.presentation.screens.components.BackgroundImage
import dev.android.allecheq.presentation.screens.components.FilledButton
import dev.android.allecheq.presentation.screens.components.OutlinedButton
import dev.android.allecheq.presentation.utils.VALUE_16
import dev.android.allecheq.presentation.utils.VALUE_20
import dev.android.allecheq.ui.theme.AlleCheqTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    HomeScreenContent(
        modifier = modifier
    )
}

@Composable
private fun HomeScreenContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        BackgroundImage(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1F)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.surface,
                            MaterialTheme.colorScheme.surface
                        ),
                    )
                )
                .zIndex(2F)
        ) {
            Box(
                modifier = Modifier
                    .padding(VALUE_20.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Column(
                    modifier = Modifier
                        .height(420.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    AppNameAndMotto()
                    Column(
                        modifier = Modifier
                    ) {
                        FilledButton(label = stringResource(id = R.string.scan_food))
                        Spacer(modifier = Modifier.height(VALUE_16.dp))
                        OutlinedButton(label = stringResource(id = R.string.emergency))
                    }
                }
            }
        }
    }
}

@Preview(
    name = "light mode",
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_6
)
@Preview(
    name = "dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_6
)
@Composable
private fun HomeScreenPreview() {
    AlleCheqTheme {
        HomeScreen()
    }
}