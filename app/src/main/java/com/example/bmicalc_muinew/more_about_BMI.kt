package com.example.bmicalc_muinew

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.bmicalc_muinew.databinding.ActivityMoreAboutBmiBinding

class more_about_BMI : AppCompatActivity() {
    private lateinit var binding: ActivityMoreAboutBmiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreAboutBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webview.webViewClient = MyWebViewClient()
        binding.webview.loadUrl("https://www.indi.ie/fact-sheets/healthy-eating,-healthy-weight-and-dieting/435-all-about-body-mass-index.html")

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item3 -> {
                ProgressDialog(this).apply {
                    this.setCancelable(false)
                    this.setTitle(R.string.app_name)
                    this.setMessage("please Wait..")
                    this.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                }
                    .show()
            }


        }


        return true
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.progressbar.visibility = View.GONE
        }

    }

}