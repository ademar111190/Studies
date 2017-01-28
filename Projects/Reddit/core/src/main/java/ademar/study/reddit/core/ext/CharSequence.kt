package ademar.study.reddit.core.ext

import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.Spanned

fun CharSequence.extractNumbers(): String {
    return this.toString().replace("[^0-9]".toRegex(), "")
}

fun CharSequence.toHtml(): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(toString(), FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(toString())
    }
}

fun CharSequence.extractHtmlContent(): String {
    return toHtml().toString()
}
