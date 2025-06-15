package com.example.mcdonaldsclone.features.QRCode

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.core.graphics.set
import androidx.core.graphics.createBitmap
import androidx.compose.runtime.State

@HiltViewModel
class QRCodeViewModel @Inject constructor() : ViewModel() {

    private val _code = mutableStateOf("M 509 960") // Możesz tu dodać losowanie/symulację
    val code: State<String> = _code

    fun generateQRCodeBitmap(): Bitmap? {
        val content = _code.value
        val size = 512

        return try {
            val bitMatrix = MultiFormatWriter().encode(
                content,
                BarcodeFormat.QR_CODE,
                size,
                size
            )
            val bmp = createBitmap(size, size, Bitmap.Config.RGB_565)
            for (x in 0 until size) {
                for (y in 0 until size) {
                    bmp[x, y] =
                        if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE
                }
            }
            bmp
        } catch (e: Exception) {
            null
        }
    }
}

