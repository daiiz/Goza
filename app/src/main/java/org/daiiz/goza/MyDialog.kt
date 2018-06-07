package org.daiiz.goza

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.google.zxing.qrcode.encoder.Encoder
import android.support.v4.app.NotificationCompat.getExtras



class MyDialog : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.my_dialog)
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
  fun generateQRCode (text: String): Bitmap {
    // QR codeのビットマトリクス
    val qrCode = Encoder.encode(text, ErrorCorrectionLevel.L)
    val byteMatrix = qrCode.matrix

    // QRコードのBitmapを生成
    // ビットマトリクスのサイズに合わせた大きさで作成される
    val bitmap = Bitmap.createBitmap(byteMatrix.width, byteMatrix.height, Bitmap.Config.ARGB_8888)
    // 各ピクセルを着色する
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