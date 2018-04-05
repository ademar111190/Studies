package ademar.study.test.core.model

import ademar.study.test.core.injection.ApplicationJsonAdapterFactory
import ademar.study.test.core.test.BaseTest
import ademar.study.test.core.test.Fixture
import ademar.study.test.core.test.JsonAssertions.assertJsonStringValue
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class HelloWorldTest : BaseTest() {

    private lateinit var adapter: JsonAdapter<HelloWorld>

    @Before
    override fun setUp() {
        super.setUp()
        adapter = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(ApplicationJsonAdapterFactory.INSTANCE)
                .build()
                .adapter(HelloWorld::class.java)
    }

    @Test
    fun testParse() {
        val helloWorld = adapter.fromJson(Fixture.helloWorld.JSON) ?: throw IllegalStateException("Failed to parse")
        assertThat(helloWorld.message).isEqualTo(Fixture.helloWorld.MESSAGE)
    }

    @Test
    fun testSerialize() {
        val json = adapter.toJson(Fixture.helloWorld.makeModel())
        assertJsonStringValue(json, "message", Fixture.helloWorld.MESSAGE)
    }

}
