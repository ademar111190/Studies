package ademar.study.reddit.plataform.ext

import android.view.View
import android.view.ViewTreeObserver

fun View.oneTimeGlobalLayoutListener(callback: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            callback()
        }
    })
}
