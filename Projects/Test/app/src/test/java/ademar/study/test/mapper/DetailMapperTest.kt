package ademar.study.test.mapper

import ademar.study.test.test.BaseTest
import ademar.study.test.test.Fixture.helloWorld
import ademar.study.test.test.Fixture.helloWorldViewModel
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mock

class DetailMapperTest : BaseTest() {

    @Mock lateinit var mockHelloWorldMapper: HelloWorldMapper

    @Test
    fun testTransform() {
        whenever(mockHelloWorldMapper.transform(helloWorld())).thenReturn(helloWorldViewModel())

        val mapper = DetailMapper(mockHelloWorldMapper)
        val viewModel = mapper.transform(helloWorld(), listOf(helloWorld()))
        assertThat(viewModel.focused).isEqualTo(helloWorldViewModel())
        assertThat(viewModel.items).isEqualTo(listOf(helloWorldViewModel()))
    }

}
