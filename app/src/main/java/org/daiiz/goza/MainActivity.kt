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
    val uri = "https://scrapbox.io/shokai/Scrapbox%E3%81%AE%E9%96%8B%E7%99%BA_-_React_&_Websocket%E3%81%A7%E4%BD%9C%E3%82%8B%E3%83%AA%E3%82%A2%E3%83%AB%E3%82%BF%E3%82%A4%E3%83%A0Wiki"
    // val uri = "https://scrapbox.io/daiiz/Nota%E3%82%A4%E3%83%B3%E3%82%BF%E3%83%BC%E3%83%B3%E3%81%AB%E5%8F%82%E5%8A%A0%E3%81%97%E3%81%A6Scrapbox%E3%82%92%E4%BD%9C%E3%81%A3%E3%81%A6%E3%81%8D%E3%81%BE%E3%81%97%E3%81%9F"

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
