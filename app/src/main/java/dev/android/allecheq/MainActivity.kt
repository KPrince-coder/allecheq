package dev.android.allecheq

//import allecheq.ml.Model1
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import dev.android.allecheq.ml.Model1
import dev.android.allecheq.presentation.navigations.NavigationGraph
import dev.android.allecheq.presentation.utils.PreprocessingUtils
import dev.android.allecheq.ui.theme.AlleCheqTheme
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            AlleCheqTheme {
                NavigationGraph()
//                CameraPreviewWithOverlay()
            }
        }


//        val features = FloatArray(128) { index ->
//            (index * 0.1f)  // This returns a Float
//        }
//
//        val byteBuffer = PreprocessingUtils.preprocessData(features)
//
//        // Create input TensorBuffer
//        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 128), DataType.FLOAT32)
//        inputFeature0.loadBuffer(byteBuffer)
//
//        // Load and run the model
//        val model = Model1.newInstance(this)
//        val outputs = model.process(inputFeature0)
//        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
//
//        // Handle the result
//        val result = outputFeature0.floatArray
//
//        // Release the model when done
//        model.close()
//    }
    }


    @Composable
    fun CameraPreviewWithOverlay() {
        val context = LocalContext.current
        val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
        val previewView = remember { PreviewView(context) }

        // Use a state to store the detected allergen text
        var detectedAllergen by remember { mutableStateOf("No Allergen Detected") }

        LaunchedEffect(cameraProviderFuture) {
            val cameraProvider = cameraProviderFuture.get()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also { analysis ->
                    analysis.setAnalyzer(ContextCompat.getMainExecutor(context)) { image ->
                        // Run model inference on each frame
                        detectedAllergen = analyzeImage(image, context)
                        image.close()
                    }
                }

            val preview = androidx.camera.core.Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    context as LifecycleOwner,
                    cameraSelector,
                    preview,
                    imageAnalyzer
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Display the camera preview and overlay the detected allergen text
        Box {
            AndroidView({ previewView }, modifier = Modifier.fillMaxSize())
            Text(
                text = detectedAllergen,
                modifier = Modifier.align(Alignment.BottomCenter),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    private fun analyzeImage(image: ImageProxy, context: Context): String {
        // Convert the ImageProxy to a ByteBuffer (matching your preprocessing)
        val byteBuffer: ByteBuffer = PreprocessingUtils.preprocessImage(image)

        // Load and run the model inference
        val model = Model1.newInstance(context)
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 128), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
        model.close()

        // Process the result and return the detected allergen as a String
        val result = outputFeature0.floatArray
        return if (result[0] > 0.5) "Allergen Detected!" else "No Allergen Detected"
    }
}


//import android.content.res.Configuration
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Devices
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import dev.android.allecheq.presentation.navigations.AppBottomNavigation
//import dev.android.allecheq.presentation.navigations.Destinations
//import dev.android.allecheq.presentation.navigations.NavigationGraph
//import dev.android.allecheq.presentation.navigations.Routes
//import dev.android.allecheq.presentation.screens.EmergencyScreen
//import dev.android.allecheq.presentation.screens.HomeScreen
//import dev.android.allecheq.presentation.screens.ProfileScreen
//import dev.android.allecheq.presentation.screens.ScanScreen
//import dev.android.allecheq.presentation.screens.ScreensWithBottomNavBar
//import dev.android.allecheq.ui.theme.AlleCheqTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        enableEdgeToEdge()
//
//
//        setContent {
//            AlleCheqTheme {
////                CameraPreviewWithOverlay()
//                NavigationGraph()
//
//            }
//        }
//    }
//}
//
//
//@Preview(
//    name = "light mode",
//    showSystemUi = true,
//    showBackground = true,
//    device = Devices.PIXEL_6
//)
//@Preview(
//    name = "dark mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showSystemUi = true,
//    showBackground = true,
//    device = Devices.PIXEL_6
//)
//@Composable
//private fun Preview() {
//    AlleCheqTheme {
//        val navController = rememberNavController()
//        NavHost(
//            navController = navController,
//            startDestination = Routes.HomeScreen.screen.route
//        ) {
//            composable(Routes.HomeScreen.screen.route) {
//                HomeScreen(onScanFoodClick = {
//                    navController.navigate(Destinations.ScanScreen.screen.route)
//                }, onEmergencyClick = {
//                    navController.navigate(Destinations.EmergencyScreen.screen.route)
//                })
//            }
//
//            composable(Destinations.ScanScreen.screen.route) {
//                Scaffold(
//                    modifier = Modifier.fillMaxSize(),
//                    bottomBar = {
//                        AppBottomNavigation(navController = navController)
//                    }
//                ) {
//                    ScanScreen(modifier = Modifier.padding(it), paddingValues = it)
//                }
//            }
//
//            composable(Destinations.ProfileScreen.screen.route) {
//                ScreensWithBottomNavBar(
//                    navController = navController,
//                    screenContent = { pad ->
//                        ProfileScreen(paddingValues = pad)
//                    }
//                )
//            }
//
//            composable(Destinations.EmergencyScreen.screen.route) {
//                ScreensWithBottomNavBar(
//                    navController = navController,
//                    screenContent = { pad ->
//                        EmergencyScreen(paddingValues = pad)
//                    }
//                )
//            }
//        }
//    }
//}
