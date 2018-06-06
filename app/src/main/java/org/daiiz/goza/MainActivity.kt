package org.daiiz.goza

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        renderScrapbox()
    }

    fun renderScrapbox () {
        lateinit var webview : WebView
        webview = findViewById(R.id.main_webview)
        webview.webViewClient = object: WebViewClient () {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }
        webview.loadUrl("https://scrapbox.io/")
    }
}
