package ademar.study.test.core.model

import ademar.study.test.core.injection.ApplicationJsonAdapterFactory
import ademar.study.test.core.test.BaseTest
import ademar.study.test.core.test.Fixture
import ademar.study.test.core.test.Fixture.IMAGE
import ademar.study.test.core.test.Fixture.MESSAGE
import ademar.study.test.core.test.containsJson
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
        val helloWorld = adapter.fromJson(readJson("helloWorld")) ?: throw IllegalStateException("Failed to parse")
        assertThat(helloWorld.message).isEqualTo(MESSAGE)
        assertThat(helloWorld.image).isEqualTo(IMAGE)
    }

    @Test
    fun testSerialize() {
        val json = adapter.toJson(Fixture.helloWorld())
        assertThat(json).containsJson("message", MESSAGE)
        assertThat(json).containsJson("image", IMAGE)
    }

}
