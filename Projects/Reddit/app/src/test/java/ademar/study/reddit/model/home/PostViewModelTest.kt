package ademar.study.reddit.model.home

import ademar.study.reddit.test.BaseTest
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class PostViewModelTest : BaseTest() {

    @Test
    fun testInstantiate() {
        PostViewModel("Some Title", "Some Author", "Some Thumbnail", 1L, 20L, -4L, 7L)
    }

}
