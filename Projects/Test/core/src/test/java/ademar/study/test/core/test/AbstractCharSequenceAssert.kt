package ademar.study.test.core.test

import org.assertj.core.api.AbstractCharSequenceAssert

fun AbstractCharSequenceAssert<*, String>.containsJson(key: String, value: String)
        : AbstractCharSequenceAssert<*, *> = contains("\"$key\":\"$value\"")
