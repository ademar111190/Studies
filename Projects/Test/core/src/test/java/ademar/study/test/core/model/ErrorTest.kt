package ademar.study.test.core.model

import ademar.study.test.core.injection.ApplicationJsonAdapterFactory
import ademar.study.test.core.test.BaseTest
import ademar.study.test.core.test.Fixture
import ademar.study.test.core.test.JsonAssertions.assertJsonIntValue
import ademar.study.test.core.test.JsonAssertions.assertJsonStringValue
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class ErrorTest : BaseTest() {

    private lateinit var adapter: JsonAdapter<Error>

    @Before
    override fun setUp() {
        super.setUp()
        adapter = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(ApplicationJsonAdapterFactory.INSTANCE)
                .build()
                .adapter(Error::class.java)
    }

    @Test
    fun testParse() {
        val error = adapter.fromJson(Fixture.error.JSON) ?: throw IllegalStateException("Failed to parse")
        assertThat(error.code).isEqualTo(Fixture.error.CODE)
        assertThat(error.message).isEqualTo(Fixture.error.MESSAGE)
    }

    @Test
    fun testSerialize() {
        val json = adapter.toJson(Fixture.error.makeModel())
        assertJsonIntValue(json, "code", Fixture.error.CODE)
        assertJsonStringValue(json, "message", Fixture.error.MESSAGE)
    }

    @Test
    fun testReport() {
        Fixture.error.makeModel().report()
    }

}
