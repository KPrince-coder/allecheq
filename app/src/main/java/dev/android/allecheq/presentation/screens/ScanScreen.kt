package dev.android.allecheq.presentation.screens

import android.content.res.Configuration
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.android.allecheq.R
import dev.android.allecheq.presentation.screens.camera.CameraPreview
import dev.android.allecheq.presentation.utils.CameraPermission
import dev.android.allecheq.presentation.utils.VALUE_2
import dev.android.allecheq.presentation.utils.VALUE_4
import dev.android.allecheq.presentation.utils.VALUE_8
import dev.android.allecheq.ui.theme.AlleCheqTheme

@Composable
fun ScanScreen(paddingValues: PaddingValues, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lifecycleController = remember {
        LifecycleCameraController(context).apply {
            CameraController.IMAGE_CAPTURE and CameraController.IMAGE_ANALYSIS
        }
    }
    val permissionLauncher =
        CameraPermission.requestCameraPermission(
            context = context
        )
    LaunchedEffect(Unit) {
        if (!CameraPermission.hasRequiredPermission(context)) {
            permissionLauncher.launch(CameraPermission.CAMERAX_PERMISSION)
        }
    }

    ScanScreenContent(
        controller = lifecycleController,
        modifier = modifier.padding(paddingValues)
    )
}

@Composable
private fun ScanScreenContent(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Scan Screen",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
            )
            // CameraScreen(controller)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.BottomEnd)
                    .background(Color.Blue),
                contentAlignment = Alignment.BottomCenter
            ) {
                TakePhotoButton(
                    controller = controller, modifier = modifier
                        .height(100.dp)
                        .background(Color.Green)
                )
            }
        }
    }
}

@Composable
private fun CameraScreen(controller: LifecycleCameraController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(320.dp)
                .height(460.dp)
                .clip(RoundedCornerShape(VALUE_8))
                .border(
                    width = VALUE_4.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(VALUE_8)
                ),
            contentAlignment = Alignment.Center
        ) {
            CameraPreview(
                controller = controller,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
private fun TakePhotoButton(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { /*TODO*/ },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.camera_icon),
            contentDescription = stringResource(R.string.camera_icon_desc),
            modifier = Modifier
                .padding(VALUE_2.dp)
        )
    }
}

