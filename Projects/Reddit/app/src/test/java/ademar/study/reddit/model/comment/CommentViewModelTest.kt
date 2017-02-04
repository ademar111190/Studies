package ademar.study.reddit.model.comment

import ademar.study.reddit.test.BaseTest
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class CommentViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        CommentViewModel("An aithor", "A text", "7 downs", "9 ups", listOf())
    }

}
