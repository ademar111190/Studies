package ademar.study.test.core.test

import org.assertj.core.api.Assertions.assertThat

object JsonAssertions {

    fun assertJsonIntValue(json: String, key: String, value: Int) {
        assertThat(json).contains("\"$key\":$value")
    }

    fun assertJsonStringValue(json: String, key: String, value: String) {
        assertThat(json).contains("\"$key\":\"$value\"")
    }

    fun assertJsonObjectNotNull(json: String, key: String) {
        assertThat(json).contains("\"$key\":{")
    }

}
