package ademar.study.test.mapper

import ademar.study.test.test.BaseTest
import ademar.study.test.test.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class HelloWorldMapperTest : BaseTest() {

    @Test
    fun testTransform() {
        val mapper = HelloWorldMapper()
        val viewModel = mapper.transform(Fixture.helloWorld.makeModel())
        assertThat(viewModel.message).isEqualTo(Fixture.helloWorld.MESSAGE)
    }

}
