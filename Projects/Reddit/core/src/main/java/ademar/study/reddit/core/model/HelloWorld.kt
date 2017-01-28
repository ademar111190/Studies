package ademar.study.reddit.core.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import nz.bradcampbell.paperparcel.PaperParcel

@JsonObject
@PaperParcel
class HelloWorld {

    @JsonField(name = arrayOf("message"))
    var message: String? = null

    companion object {

        val UNKNOWN = Error().apply { code = 10000 }
        val UNAUTHORIZED = Error().apply { code = 10001 }

    }

}
