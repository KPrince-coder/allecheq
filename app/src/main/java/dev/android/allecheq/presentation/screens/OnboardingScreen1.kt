package dev.android.allecheq.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import dev.android.allecheq.R
import dev.android.allecheq.presentation.screens.components.FilledButton
import dev.android.allecheq.presentation.utils.VALUE_16
import dev.android.allecheq.presentation.utils.VALUE_20
import dev.android.allecheq.presentation.utils.VALUE_24
import dev.android.allecheq.presentation.utils.VALUE_8
import dev.android.allecheq.ui.theme.AlleCheqTheme
import dev.android.allecheq.ui.theme.FontFam

@Composable
fun OnboardingScreen1() {
    OnboardingScreen1Content(
        modifier = Modifier
            .fillMaxSize()
        // .background
    )
}

@Composable
private fun OnboardingScreen1Content(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        // background image
        Image(
            painter = painterResource(id = R.drawable.onboarding_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(
                    rotationY = 180F,
                    scaleY = 1.09F,
                    scaleX = 1.09F,
                    translationX = -50F,
                )
                .zIndex(1F),
            contentScale = ContentScale.Crop
        )
        // rounded shape with text and button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(440.dp)
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
                    .padding(vertical = VALUE_24.dp, horizontal = VALUE_16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = VALUE_8.dp)
                )
                FilledButton(text = stringResource(id = R.string.get_started))
            }
        }
    }
}

@Composable
private fun Text(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
        ) {
            val (title, subtitle) = createRefs()


            Text(
                text = stringResource(id = R.string.app_name).uppercase(), // p7R<7543
                style = TextStyle(
                    fontSize = 52.sp,
                    fontFamily = FontFam.BalooTamma2.fontFamily,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.secondary,
                ),
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(subtitle.top)
                    }
            )
            Text(
                text = "Your number one allergy stop shop",
                style = TextStyle(
                    fontFamily = FontFam.Inter.fontFamily,
                    fontSize = VALUE_20.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .constrainAs(subtitle) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(title.bottom)
                        bottom.linkTo(parent.bottom)
                    }
                    .offset(y = -VALUE_20.dp)
            )
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
        OnboardingScreen1()
    }
}