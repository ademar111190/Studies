package ademar.study.reddit.core.model.internal

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class PostDetailDataChildren {

    @JsonField(name = arrayOf("kind"))
    lateinit var kind: String

    @JsonField(name = arrayOf("data"))
    lateinit var comment: Comment

    fun isComment(): Boolean {
        return kind == "t1"
    }

}
