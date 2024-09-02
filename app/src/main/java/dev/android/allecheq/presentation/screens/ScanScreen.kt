package dev.android.allecheq.presentation.screens

import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.android.allecheq.R
import dev.android.allecheq.presentation.screens.camera.CameraPreview
import dev.android.allecheq.presentation.utils.CameraPermission
import dev.android.allecheq.presentation.utils.VALUE_16
import dev.android.allecheq.presentation.utils.VALUE_24
import dev.android.allecheq.presentation.utils.VALUE_4
import dev.android.allecheq.presentation.utils.VALUE_40
import dev.android.allecheq.presentation.utils.VALUE_8
import dev.android.allecheq.ui.theme.FontFam
import kotlinx.coroutines.delay

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


//    // Example features array with 128 features
//    val features = FloatArray(128) {index ->
//        0.0f }
//
//    // Preprocess data
//    val byteBuffer = PreprocessingUtils.preprocessData(features)
//
//    // Initialize model
//    val model = Model1.newInstance(context)
//
//    // Create input TensorBuffer
//    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 128), DataType.FLOAT32)
//    inputFeature0.loadBuffer(byteBuffer)
//
//    // Run inference
//    val outputs = model.process(inputFeature0)
//    val outputFeature0 = outputs.outputFeature0AsTensorBuffer
//
//    // Process the result
//    val result = outputFeature0.floatArray
//
//    // Release model resources
//    model.close()
//
//    // Now you can use the result for further actions, e.g., display it on the UI


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
//                    .weight(0.7F)
                    .weight(0.8f)
                    .padding(top = VALUE_40.dp)
            )

            var processing by remember {
                mutableStateOf("Processing...")
            }


            var timerRunning by remember { mutableStateOf(true) }
            var color by remember {
                mutableStateOf(Color.Red)
            }

            LaunchedEffect(Unit) {
                delay(9000) // 60 seconds = 60000 milliseconds
                processing = "Contains your food allergen!"
                color = Color.Green
            }

            Text(
                text = processing,
                fontSize = VALUE_24.sp,
                color = color,
                fontWeight = FontWeight.W500,
                fontFamily = FontFam.Inter.fontFamily,
                modifier = Modifier.animateContentSize()
                    .weight(0.2F)
            )
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .weight(0.4F)
//                    .padding(bottom = VALUE_40.dp)
//                    .wrapContentSize(Alignment.BottomCenter),
//
//                contentAlignment = Alignment.TopCenter
//
//            ) {
//                TakePhotoButton(
//                    controller = controller,
//                    modifier = modifier
//                        .size(100.dp)
//                        .clip(CircleShape)
//                        .border(
//                            width = VALUE_2.dp,
//                            color = MaterialTheme.colorScheme.primary,
//                            shape = CircleShape
//                        )
//
//
//                    )


//            }
        }
    }
}

@Composable
fun CameraScreen(controller: LifecycleCameraController, modifier: Modifier = Modifier) {
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

