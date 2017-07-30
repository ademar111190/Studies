package ademar.study.test.core.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import java.util.*

@JsonObject
class Error : Throwable() {

    @JsonField(name = arrayOf("code"))
    var code: Int = 0

    @JsonField(name = arrayOf("message"))
    override var message: String = ""

    fun report() = println(this)

    override fun equals(other: Any?) = other is Error && code == other.code && message == other.message

    override fun hashCode() = Objects.hash(code, message)

}
