package dev.android.allecheq.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.android.allecheq.R
import dev.android.allecheq.presentation.screens.components.AppNameAndMotto
import dev.android.allecheq.presentation.screens.components.BackgroundImage
import dev.android.allecheq.presentation.screens.components.FilledButton
import dev.android.allecheq.presentation.utils.VALUE_16
import dev.android.allecheq.presentation.utils.VALUE_20
import dev.android.allecheq.presentation.utils.VALUE_24
import dev.android.allecheq.ui.theme.AlleCheqTheme

@Composable
fun OnboardingScreen1(onClick: () -> Unit) {
    OnboardingScreen1Content(
        onClick = onClick,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Composable
private fun OnboardingScreen1Content(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        // background image
        BackgroundImage()
        // rounded shape with text and button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.background_height))
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(topStartPercent = 30)
                )
                .align(Alignment.BottomCenter)
                .zIndex(2F),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = VALUE_24.dp, horizontal = VALUE_20.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppNameAndMotto()
                FilledButton(
                    label = stringResource(id = R.string.get_started),
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = VALUE_16.dp)
                )
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
private fun SplashScreenPreview() {
    AlleCheqTheme {
        OnboardingScreen1 {}
    }
}