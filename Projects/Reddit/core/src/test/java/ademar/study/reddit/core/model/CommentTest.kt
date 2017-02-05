package ademar.study.reddit.core.model

import ademar.study.reddit.core.test.BaseTest
import org.junit.Test

class CommentTest : BaseTest() {

    @Test
    fun testInstantiate() {
        Comment("An author", "A text", 7, 6, 3, listOf())
    }

}
