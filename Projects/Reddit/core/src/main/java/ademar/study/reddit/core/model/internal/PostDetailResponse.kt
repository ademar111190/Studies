package ademar.study.reddit.core.model.internal

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
class PostDetailResponse {

    @JsonField(name = arrayOf("data"))
    lateinit var data: PostDetailData

}
