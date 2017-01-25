package ademar.study.test.core.model

import ademar.study.test.core.test.BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class FieldPropertyTest : BaseTest() {

    @Test(expected = UninitializedPropertyAccessException::class)
    fun testGetValue_uninitialized() {
        val delegated = Stub().delegated
    }

    @Test
    fun testSetGet() {
        val stub = Stub()
        stub.delegated = "Test"
        val delegated = stub.delegated
        assertThat(delegated).isEqualTo("Test")
    }

    class Stub {

        var delegated: String by FieldProperty()

    }

}
