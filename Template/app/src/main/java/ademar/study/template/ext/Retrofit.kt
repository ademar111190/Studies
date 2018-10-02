package ademar.study.template.ext

import retrofit2.Retrofit
import timber.log.Timber

inline fun <reified T> Retrofit.create() = create(T::class.java) ?: throw IllegalTypeException().apply {
    Timber.w("Unable to create the type %s using the retrofit %s", T::class.java, this@create)
}

class IllegalTypeException : IllegalArgumentException()
