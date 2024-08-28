package dev.android.allecheq.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.android.allecheq.R
import dev.android.allecheq.data.Allergy
import dev.android.allecheq.data.AllergyData
import dev.android.allecheq.presentation.screens.components.FilledButton
import dev.android.allecheq.presentation.utils.VALUE_12
import dev.android.allecheq.presentation.utils.VALUE_16
import dev.android.allecheq.presentation.utils.VALUE_20
import dev.android.allecheq.presentation.utils.VALUE_24
import dev.android.allecheq.presentation.utils.VALUE_32
import dev.android.allecheq.presentation.utils.VALUE_8
import dev.android.allecheq.ui.theme.AlleCheqTheme
import dev.android.allecheq.ui.theme.FontFam

@Composable
fun OnboardingScreen2(onClick: () -> Unit, onBackwardNavigation: () -> Unit) {
    // allergyViewModel: AllergyViewModel = viewModel()
    val allergies = AllergyData.allergies

    OnboardingScreen2Content(
        allergies = allergies,
        // onCheckedChange = { isChecked, allergyId ->
        //     allergyViewModel.toggleAllergySelection(allergyId = allergyId, isChecked = isChecked)
        // }
        onCheckedChange = { isChecked, allergyId ->
            allergies
                .filter { it.id == allergyId }
                .map { allergy ->
                    allergy.copy(isSelected = isChecked)
                }
        },
        onClick = onClick,
        onBackwardNavigation = onBackwardNavigation
    )
}

@Composable
private fun OnboardingScreen2Content(
    allergies: List<Allergy>,
    onClick: () -> Unit,
    onBackwardNavigation: () -> Unit,
    onCheckedChange: (Boolean, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(VALUE_16.dp),
        ) {
            // top text and backwards navigation arrow
            TopTextAndNavIcon(
                onBackwardNavigation,
                modifier = Modifier.padding(
                    top = VALUE_32.dp
                )
            )
            // allergies and their checkboxes
            AllergyItems(
                allergies = allergies,
                onCheckedChange = onCheckedChange,
                modifier = Modifier
                    .padding(horizontal = VALUE_8.dp, vertical = VALUE_12.dp)
            )
            // continue button
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = VALUE_12.dp)
                    .wrapContentSize(Alignment.BottomCenter)
            ) {
                FilledButton(
                    label = stringResource(R.string.continue_button),
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = VALUE_16.dp)
                )
            }
        }
    }
}

@Composable
private fun TopTextAndNavIcon(onBackwardNavigation: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(Alignment.CenterStart),
            horizontalAlignment = Alignment.Start
        ) {
            IconButton(
                onClick = onBackwardNavigation,
                modifier = Modifier,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate backwards",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.CenterStart),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
        Column {
            Text(
                text = "Select your Allergies",
                style = TextStyle(
                    fontSize = VALUE_24.sp,
                    fontFamily = FontFam.Inter.fontFamily,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.W600
                )
            )
        }
    }
}

@Composable
private fun AllergyItems(
    allergies: List<Allergy>,
    onCheckedChange: (Boolean, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        allergies.forEach { allergy ->
            AllergyItem(
                allergy = allergy,
                onCheckedChange = onCheckedChange,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun AllergyItem(
    allergy: Allergy,
    onCheckedChange: (Boolean, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(vertical = (VALUE_12 - 2).dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = allergy.allergyImage),
                    contentDescription = stringResource(id = allergy.allergyImageDescription),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(VALUE_32.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(VALUE_16.dp))
            Column {
                Text(
                    text = stringResource(id = allergy.name),
                    style = TextStyle(
                        fontSize = VALUE_20.sp,
                        fontFamily = FontFam.Inter.fontFamily,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.W500
                    ),
                    modifier = Modifier
                )
            }
        }

        Column {
            Switch(
                checked = allergy.isSelected,
                onCheckedChange = { onCheckedChange(it, allergy.id) },
                colors = SwitchDefaults.colors().copy(
                    checkedThumbColor = MaterialTheme.colorScheme.tertiary,
                    checkedTrackColor = MaterialTheme.colorScheme.tertiaryContainer,
                    checkedBorderColor = MaterialTheme.colorScheme.tertiary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                    uncheckedBorderColor = MaterialTheme.colorScheme.secondary
                )
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
private fun ScreenPrev() {
    AlleCheqTheme {
        OnboardingScreen2Content(
            onCheckedChange = { _, _ ->
            },
            allergies = AllergyData.allergies,
            onClick = {},
            onBackwardNavigation = {}
        )
    }
}