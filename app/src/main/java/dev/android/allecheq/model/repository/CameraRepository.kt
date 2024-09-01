package dev.android.allecheq.model.repository

import androidx.camera.view.LifecycleCameraController

interface CameraRepository {
    suspend fun takePhoto(
        controller: LifecycleCameraController
    )
}