package l0laapk3.onitama

import android.content.Context
import android.webkit.JavascriptInterface

import android.content.Intent


class JavaScriptShareInterface(var context: Context) {

    @JavascriptInterface
    fun share(title: String?, url: String?) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }
}