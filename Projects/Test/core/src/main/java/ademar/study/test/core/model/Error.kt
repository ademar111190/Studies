package ademar.study.test.core.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class Error : Throwable() {

    @JsonField(name = arrayOf("code"))
    var code: Int = 0

    @JsonField(name = arrayOf("message"))
    override var message: String = ""

    fun report() {
        println(this)
    }

    companion object {

        val UNKNOWN = Error().apply { code = 10000 }
        val UNAUTHORIZED = Error().apply { code = 10001 }

    }

}
