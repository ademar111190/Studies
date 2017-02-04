package ademar.study.reddit.core.model.internal

import ademar.study.reddit.core.model.Post
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class Child {

    @JsonField(name = arrayOf("data"))
    lateinit var post: Post

}
