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


    // Action Buttonを追加できる
    // 24px, 24px, 48dp
    val pendingIntent = PendingIntent.getActivity(this, 0 /* REQUEST_CODE */, intent, 0 /* flags */)
    val icon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
    Log.v("fff", "eee")
    builder.setToolbarColor(565656)
    builder.setShowTitle(true)
    builder.enableUrlBarHiding()
    builder.setActionButton(icon, "action", pendingIntent)
    builder.addMenuItem("foooo", pendingIntent)

    // CustomTabsでURLを開くIntentを発行
    val tabsIntent = builder.build()
    // val packageName = CustomTabsHelper.getPackageNameToUse(this)
    // tabsIntent.intent.`package` = packageName
    tabsIntent.launchUrl(this, Uri.parse(uri))
  }
}
