package org.daiiz.goza

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.support.customtabs.CustomTabsClient
import android.support.customtabs.CustomTabsServiceConnection
import android.support.customtabs.CustomTabsIntent
import android.content.Intent
import android.app.PendingIntent
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.google.zxing.qrcode.encoder.Encoder
import com.google.zxing.qrcode.encoder.QRCode
import android.support.v4.view.ViewCompat.getMatrix
import com.google.zxing.qrcode.encoder.ByteMatrix
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.R.attr.bitmap
import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.graphics.Color
import android.opengl.ETC1.getWidth
import android.opengl.ETC1.getHeight


class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val uri = "https://scrapbox.io/"

    // builderを使う
    val builder = CustomTabsIntent.Builder()

    // Action Button 押下時に発行するIntent
    val intent = Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_TEXT, "url")

    // Action Buttonを追加できるはず
    // 24px, 24px, 48dp
    val icon = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_foreground)

    // https@ //developer.android.com/training/basics/firstapp/starting-activity?hl=ja
    val intent2 = Intent(this, MyDialog::class.java)
    intent2.putExtra("uri", uri)
    val pendingIntent = PendingIntent.getActivity(
        this, 0 , intent2, PendingIntent.FLAG_UPDATE_CURRENT)

    builder.setToolbarColor(Color.GRAY)
    builder.setShowTitle(true)
    builder.enableUrlBarHiding()
    builder.setActionButton(icon, "action", pendingIntent)
    builder.addMenuItem("intent2", pendingIntent)

    // CustomTabsでURLを開くIntentを発行
    val tabsIntent = builder.build()
    // val packageName = CustomTabsHelper.getPackageNameToUse(this)
    // tabsIntent.intent.`package` = packageName
    tabsIntent.launchUrl(this, Uri.parse(uri))
  }
}
