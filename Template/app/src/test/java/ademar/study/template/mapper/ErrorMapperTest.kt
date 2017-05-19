package ademar.study.template.mapper

import ademar.study.template.test.BaseTest
import ademar.study.template.test.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ErrorMapperTest : BaseTest() {

    @Test
    fun testTransform() {
        val mapper = ErrorMapper()
        val viewModel = mapper.transform(Fixture.error.makeModel())
        assertThat(viewModel.code).isEqualTo(Fixture.error.CODE)
        assertThat(viewModel.message).isEqualTo(Fixture.error.MESSAGE)
    }

}
