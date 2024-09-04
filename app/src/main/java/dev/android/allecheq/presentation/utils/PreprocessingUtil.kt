package dev.android.allecheq.presentation.utils


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

object PreprocessingUtils {

    fun preprocessData(features: FloatArray): ByteBuffer {
        val inputSize = 128  // As per your model's input shape
        val byteBuffer = ByteBuffer.allocateDirect(4 * inputSize)
        byteBuffer.order(ByteOrder.nativeOrder())

        for (i in features.indices) {
            byteBuffer.putFloat(features[i])
        }

        return byteBuffer
    }

    fun preprocessImage(bitmap: ImageProxy): ByteBuffer {
        val inputImageWidth = 224  // Adjust as per model
        val inputImageHeight = 224  // Adjust as per model
        val inputSize = inputImageWidth * inputImageHeight * 3

        val byteBuffer = ByteBuffer.allocateDirect(4 * inputSize)
        byteBuffer.order(ByteOrder.nativeOrder())

        val intValues = IntArray(inputImageWidth * inputImageHeight)
        bitmap.toBitmapImage().getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        for (pixelValue in intValues) {
            byteBuffer.putFloat(((pixelValue shr 16) and 0xFF) / 255.0f)
            byteBuffer.putFloat(((pixelValue shr 8) and 0xFF) / 255.0f)
            byteBuffer.putFloat((pixelValue and 0xFF) / 255.0f)
        }

        return byteBuffer
    }
}

@OptIn(ExperimentalGetImage::class)
fun ImageProxy.toBitmapImage(): Bitmap {
    val image = this.image ?: throw IllegalStateException("ImageProxy's image is null")
    val planes = image.planes
    val yPlane = planes[0]
    val uPlane = planes[1]
    val vPlane = planes[2]

    val yBuffer = yPlane.buffer
    val uBuffer = uPlane.buffer
    val vBuffer = vPlane.buffer

    val ySize = yBuffer.remaining()
    val uSize = uBuffer.remaining()
    val vSize = vBuffer.remaining()

    val y = ByteArray(ySize)
    val u = ByteArray(uSize)
    val v = ByteArray(vSize)

    yBuffer.get(y)
    uBuffer.get(u)
    vBuffer.get(v)

    val yuv = YuvImage(y, ImageFormat.NV21, width, height, null)
    val out = ByteArrayOutputStream()
    yuv.compressToJpeg(Rect(0, 0, width, height), 100, out)
    val imageBytes = out.toByteArray()

    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}
