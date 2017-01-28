package ademar.study.reddit.core.model.internal

import ademar.study.reddit.core.model.Post
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import nz.bradcampbell.paperparcel.PaperParcel

@JsonObject
@PaperParcel
class Child {

    @JsonField(name = arrayOf("data"))
    lateinit var post: Post

}
