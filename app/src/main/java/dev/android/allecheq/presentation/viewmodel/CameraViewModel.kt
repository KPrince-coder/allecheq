package dev.android.allecheq.presentation.viewmodel

// import kotlinx.coroutines.CoroutineScope

// class CameraViewModel(private val cameraSelector: CameraXSelector) : ViewModel() {
//     private lateinit var objectDetector: ObjectDetector
//
//     init {
//         initializeObjectDetector()
//     }
//
//     private fun initializeObjectDetector() {
//         val options = ObjectDetectionOptions.Builder().build()
//         objectDetector = ObjectDetector.create(options)
//     }
//
//     fun processImage(image: Image): List<String> {
//         view
//         return withContext(Dispatchers.IO) {
//             val objects = objectDetector.detectInImage(Image.fromBitmap(image.toBitmap(), null), options)
//             objects.map { it.label }.toList()
//         }
//     }
// }