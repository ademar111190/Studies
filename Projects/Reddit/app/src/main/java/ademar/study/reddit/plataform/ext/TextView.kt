package ademar.study.reddit.plataform.ext

import android.graphics.Paint
import android.widget.TextView

fun TextView.strike() {
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}
