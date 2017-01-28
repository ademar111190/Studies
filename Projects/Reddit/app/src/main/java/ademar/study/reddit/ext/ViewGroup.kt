package ademar.study.reddit.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup?.inflate(layoutId: Int): View = LayoutInflater.from(this?.context).inflate(layoutId, this, false)
