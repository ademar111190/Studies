package ademar.study.reddit.core.model.internal

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import nz.bradcampbell.paperparcel.PaperParcel

@JsonObject
@PaperParcel
class Response {

    @JsonField(name = arrayOf("data"))
    lateinit var data: Data

}
