package dev.android.allecheq.presentation.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import dev.android.allecheq.R

class CameraPermission {
    companion object {
        const val CAMERAX_PERMISSION = Manifest.permission.CAMERA
        private var denialCount = 0

        fun hasRequiredPermission(applicationContext: Context): Boolean {
            return ContextCompat.checkSelfPermission(
                applicationContext, CAMERAX_PERMISSION
            ) == PackageManager.PERMISSION_GRANTED
        }

        @Composable
        fun requestCameraPermission(
            context: Context,
        ): ActivityResultLauncher<String> {
            var showDialog by remember { mutableStateOf(false) }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (!isGranted) {
                    denialCount++
                    if (denialCount >= 3) {
                        showDialog = true
                    }
                } else {
                    denialCount = 0
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(stringResource(R.string.camera_permission_dialog_title))
                    },
                    text = {
                        Text(stringResource(R.string.camera_permission_dialog_text))
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showDialog = false
                                val intent =
                                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                        .apply {
                                            data = Uri.fromParts(
                                                "package",
                                                context.packageName,
                                                null
                                            )
                                        }
                                context.startActivity(intent)
                            }) {
                            Text(stringResource(R.string.open_settings_button_text))
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { showDialog = false }
                        ) {
                            Text(stringResource(R.string.cancel_button_text))
                        }
                    }
                )
            }

            return launcher
        }
    }
}
