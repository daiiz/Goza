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



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        renderWebpage("https://www.google.co.jp/")
    }

    fun renderWebpage (uri: String?) {
        val tabsIntent = CustomTabsIntent.Builder().build()
//        val packageName = CustomTabsHelper.getPackageNameToUse(this)
//        tabsIntent.intent.`package` = packageName
        tabsIntent.launchUrl(this, Uri.parse(uri))
    }
}
