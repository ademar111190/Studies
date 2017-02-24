package ademar.study.test.core.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@JsonObject
@PaperParcel
class HelloWorld : PaperParcelable {

    @JsonField(name = arrayOf("message"))
    var message: String? = null

    companion object {

        @JvmField val CREATOR = PaperParcelHelloWorld.CREATOR

        val UNKNOWN = Error().apply { code = 10000 }
        val UNAUTHORIZED = Error().apply { code = 10001 }

    }

}
