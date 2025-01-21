package com.refactoringlife.linternapp.ui.theme.presenter

import android.content.Context
import android.hardware.camera2.CameraManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.refactoringlife.linternapp.R


@Preview
@Composable
fun FlashScreen() {
    var isFlashOn by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isFlashOn) {
            Image(
                painterResource(id = R.drawable.light_hd), contentDescription = "Flash on",
                modifier = Modifier.weight(1f)
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
        Image(painterResource(id = R.drawable.lintern), contentDescription = "light",
            Modifier.clickable {
                isFlashOn = !isFlashOn
                toggleFlashlight(isFlashOn, context)
            }
        )
        Spacer(modifier = Modifier.height(100.dp))
        BannerAdView(modifier = Modifier.height(100.dp).fillMaxWidth())
    }
}

private fun toggleFlashlight(state: Boolean, context: Context) {
    val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    val cameraId: String = cameraManager.cameraIdList.firstOrNull() ?: return

    try {
        cameraManager.setTorchMode(cameraId, state)
    } catch (e: Exception) {
        Log.e("LanternApp", "Error", e)
    }
}