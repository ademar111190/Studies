package ademar.study.test.core.ext

import android.os.Build
import android.text.Html

fun CharSequence.extractNumbers() = this.toString().replace("[^0-9]".toRegex(), "")

fun CharSequence.toHtml() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(toString(), android.text.Html.FROM_HTML_MODE_LEGACY)
} else {
    @Suppress("DEPRECATION")
    Html.fromHtml(toString())
}

fun CharSequence.extractHtmlContent() = toHtml().toString()
