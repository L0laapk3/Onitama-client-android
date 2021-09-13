package l0laapk3.onitama

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.MenuItem
import l0laapk3.onitama.databinding.ActivityMainBinding
import android.os.Build
import android.webkit.*
import java.net.URL


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val webView = binding.mainWebView

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        webView.addJavascriptInterface(JavaScriptShareInterface(webView.context), "AndroidShareHandler")


        var link = "https://l0laapk3.github.io/Onitama-client/"
        if (intent.action == Intent.ACTION_VIEW) {
            val appLinkData = intent.dataString
            if (appLinkData != null) {
                var match = Regex(
                    "^https?://(?:l0laapk3\\.github\\.io/Onitama-client|git\\.io/onitama)/?#(.+)$",
                    RegexOption.IGNORE_CASE
                ).find(appLinkData)
                if (match != null) {
                    val (gameId) = match.destructured
                    if (gameId != null && gameId != "")
                        link = "https://l0laapk3.github.io/Onitama-client/#$gameId"
                }
            }
        }
        webView.loadUrl(link)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}