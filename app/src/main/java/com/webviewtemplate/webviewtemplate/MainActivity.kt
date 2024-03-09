package com.webviewtemplate.webviewtemplate

import android.os.Bundle
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.webkit.WebView
import android.window.OnBackInvokedDispatcher
import com.webviewtemplate.webviewtemplate.databinding.ActivityMainBinding

class MainActivity : Activity() {
    private val applicationUrl = "https://www.bing.com/copilot"
    private lateinit var binding: ActivityMainBinding
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webView = binding.webView

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    finish()
                }
            }
        }

        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptEnabled = true

        // Set user agent to mobile Microsoft Edge
        val edgeUserAgent = "Mozilla/5.0 (Linux; Android 10; HD1913) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.6099.144 Mobile Safari/537.36 EdgA/120.0.2210.64"
        webView.settings.userAgentString = edgeUserAgent

        webView.loadUrl(applicationUrl)
    }
}
