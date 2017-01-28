package ademar.study.reddit.mapper.home

import ademar.study.reddit.mapper.HelloWorldMapper
import ademar.study.reddit.test.BaseTest
import ademar.study.reddit.test.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class HelloWorldMapperTest : BaseTest() {

    @Test
    fun testTransform() {
        val mapper = HelloWorldMapper(mockStandardErrors)
        val viewModel = mapper.transform(Fixture.helloWorld.makeModel())
        assertThat(viewModel.message).isEqualTo(Fixture.helloWorld.MESSAGE)
    }

    @Test
    fun testTransform_withNullMessage() {
        val mapper = HelloWorldMapper(mockStandardErrors)
        val viewModel = mapper.transform(Fixture.helloWorld.makeModel().apply {
            message = null
        })
        assertThat(viewModel.message).isEqualTo(mockErrorUnknown.message)
    }

}