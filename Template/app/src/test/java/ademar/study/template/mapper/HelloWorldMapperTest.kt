package ademar.study.template.mapper

import ademar.study.template.test.BaseTest
import ademar.study.template.test.Fixture
import ademar.study.template.test.Fixture.IMAGE
import ademar.study.template.test.Fixture.MESSAGE
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class HelloWorldMapperTest : BaseTest() {

    @Test
    fun testTransform() {
        val mapper = HelloWorldMapper()
        val viewModel = mapper.transform(Fixture.helloWorld())
        assertThat(viewModel.message).isEqualTo(MESSAGE)
        assertThat(viewModel.image).isEqualTo("https://upload.wikimedia.org/wikipedia/commons/$IMAGE")
    }

}
