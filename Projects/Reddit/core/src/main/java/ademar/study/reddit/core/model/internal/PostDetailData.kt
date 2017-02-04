package ademar.study.reddit.core.model.internal

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class PostDetailData {

    @JsonField(name = arrayOf("children"))
    lateinit var children: List<PostDetailDataChildren>

}
