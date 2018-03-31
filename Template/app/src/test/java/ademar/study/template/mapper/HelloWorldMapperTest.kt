package ademar.study.template.mapper

import ademar.study.template.test.BaseTest
import ademar.study.template.test.Fixture
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
