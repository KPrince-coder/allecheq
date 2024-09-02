package dev.android.allecheq.presentation.screens

import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.android.allecheq.R
import dev.android.allecheq.presentation.screens.camera.CameraPreview
import dev.android.allecheq.presentation.utils.CameraPermission
import dev.android.allecheq.presentation.utils.VALUE_16
import dev.android.allecheq.presentation.utils.VALUE_2
import dev.android.allecheq.presentation.utils.VALUE_24
import dev.android.allecheq.presentation.utils.VALUE_4
import dev.android.allecheq.presentation.utils.VALUE_40
import dev.android.allecheq.presentation.utils.VALUE_8

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
        modifier = Modifier
            .fillMaxSize()
            .padding(top = VALUE_24.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(VALUE_16.dp)
        ) {

            CameraScreen(
                controller, modifier = Modifier
                    .weight(0.7F)
                    .padding(top = VALUE_40.dp)
            )


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.4F)
                    .padding(bottom = VALUE_40.dp)
                    .wrapContentSize(Alignment.BottomCenter),

                contentAlignment = Alignment.TopCenter

            ) {
                TakePhotoButton(
                    controller = controller,
                    modifier = modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(
                            width = VALUE_2.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )


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
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .width(400.dp)
                .height(500.dp)
                .clip(RoundedCornerShape(VALUE_8))
                .border(
                    width = VALUE_4.dp,
                    color = MaterialTheme.colorScheme.primary,
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
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(VALUE_8.dp)

        )
    }
}

