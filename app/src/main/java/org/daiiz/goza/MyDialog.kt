package org.daiiz.goza

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.google.zxing.qrcode.encoder.Encoder


class QrActivity : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_qr)
    val imageView = findViewById<ImageView>(R.id.image_view)
    val i = intent
    val action = intent.action
    if (action == Intent.ACTION_SEND) {
      val extras = intent.extras
      if (extras != null) {
        val uri = extras.getCharSequence(Intent.EXTRA_TEXT).toString()
        if (uri.isNotEmpty()) {
          val qrCodeImage = generateQRCode(uri)
          imageView.setImageBitmap(qrCodeImage)
        }
      }
    } else {
      val uri = i.getStringExtra("uri")
      val qrCodeImage = generateQRCode(uri)
      imageView.setImageBitmap(qrCodeImage)
    }
  }

  // thanks: https://qiita.com/niusounds/items/302a97afabf1d469bc81
  private fun generateQRCode (text: String): Bitmap {
    val qrCode = Encoder.encode(text, ErrorCorrectionLevel.L)
    val byteMatrix = qrCode.matrix
    val bitmap = Bitmap.createBitmap(byteMatrix.width, byteMatrix.height, Bitmap.Config.ARGB_8888)
    for (y in 0 until byteMatrix.height) {
      for (x in 0 until byteMatrix.width) {
        val `val` = byteMatrix.get(x, y)
        bitmap.setPixel(x, y, if (`val`.toInt() == 1) Color.BLACK else Color.WHITE)
      }
    }

    val size = 96
    val res = Bitmap.createScaledBitmap(bitmap, size, size, false)
    return res
  }
}