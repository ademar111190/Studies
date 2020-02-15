package study.template

import kotlin.test.assertEquals
import org.junit.Test

class AppDataTest {

    @Test
    fun `name should return app name`() {
        assertEquals(
            expected = AppDataName("Study template"),
            actual = AppData.name()
        )
    }

}
