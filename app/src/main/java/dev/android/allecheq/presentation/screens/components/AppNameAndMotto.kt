package dev.android.allecheq.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.android.allecheq.R
import dev.android.allecheq.presentation.utils.VALUE_20
import dev.android.allecheq.ui.theme.FontFam

/**
 * @description
 * A composable function containing the application name and the motto of the app
 */
@Composable
fun AppNameAndMotto(modifier: Modifier = Modifier) {
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
                text = stringResource(id = R.string.app_name).uppercase(),
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
                text = stringResource(id = R.string.app_motto),
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