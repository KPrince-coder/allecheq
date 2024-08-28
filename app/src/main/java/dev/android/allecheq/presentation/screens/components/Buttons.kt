package dev.android.allecheq.presentation.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.android.allecheq.presentation.utils.VALUE_2
import dev.android.allecheq.presentation.utils.VALUE_20
import dev.android.allecheq.presentation.utils.VALUE_8
import dev.android.allecheq.ui.theme.FontFam

@Composable
fun FilledButton(label: String, modifier: Modifier = Modifier.fillMaxWidth()) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 6.dp,
            focusedElevation = 6.dp,
            hoveredElevation = 6.dp
        ),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = FontFam.Inter.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = VALUE_20.sp,
                shadow = Shadow(color = Color.DarkGray, blurRadius = 0.8F),
                // lineHeight = 100.sp
            ),
            modifier = Modifier
                .padding(vertical = VALUE_8.dp)
        )
    }
}

@Composable
fun OutlinedButton(label: String, modifier: Modifier = Modifier.fillMaxWidth()) {
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = modifier,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 6.dp,
            focusedElevation = 6.dp,
            hoveredElevation = 6.dp
        ),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors().copy(
            contentColor = MaterialTheme.colorScheme.primary
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            width = VALUE_2.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.primary
                )
            )
        )
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = FontFam.Inter.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = VALUE_20.sp,
                shadow = Shadow(color = Color.DarkGray, blurRadius = 0.8F),
            ),
            modifier = Modifier
                .padding(vertical = VALUE_8.dp)
        )
    }
}