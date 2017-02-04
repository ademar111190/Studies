package ademar.study.reddit.core.model.internal

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class Comment {

    @JsonField(name = arrayOf("replies"))
    var replies: PostDetailData? = null

    @JsonField(name = arrayOf("author"))
    lateinit var author: String

    @JsonField(name = arrayOf("body"))
    lateinit var text: String

    @JsonField(name = arrayOf("downs"))
    var downs: Long = 0L

    @JsonField(name = arrayOf("ups"))
    var ups: Long = 0L

}
