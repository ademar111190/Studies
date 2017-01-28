package ademar.study.reddit.core.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import nz.bradcampbell.paperparcel.PaperParcel

@JsonObject
@PaperParcel
class Submission {

    @JsonField(name = arrayOf("title"))
    lateinit var title: String

    @JsonField(name = arrayOf("author"))
    lateinit var author: String

    @JsonField(name = arrayOf("thumbnail"))
    lateinit var thumbnail: String

    @JsonField(name = arrayOf("created_utc"))
    var created: Long = 0L

    @JsonField(name = arrayOf("num_comments"))
    var comments: Long = 0L

    @JsonField(name = arrayOf("downs"))
    var downs: Long = 0L

    @JsonField(name = arrayOf("ups"))
    var ups: Long = 0L

}
