package ademar.study.template.core.ext

fun CharSequence.extractNumbers() = toString().replace("[^0-9]".toRegex(), "")
