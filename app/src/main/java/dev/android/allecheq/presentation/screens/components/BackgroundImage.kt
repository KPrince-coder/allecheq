package dev.android.allecheq.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
import dev.android.allecheq.R

/**
 * @description
 * Produces the background image for some of the screens of the app
 */
@Composable
fun BackgroundImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.onboarding_background),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer(
                rotationY = 180F,
                scaleY = 1.04F,
                scaleX = 1.04F,
            )
            .zIndex(1F),
        contentScale = ContentScale.Crop
    )
}