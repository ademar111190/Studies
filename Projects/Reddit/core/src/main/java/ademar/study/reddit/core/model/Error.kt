package ademar.study.reddit.core.model

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Error

        if (code != other.code) return false
        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code
        result = 31 * result + message.hashCode()
        return result
    }

}
