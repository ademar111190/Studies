package ademar.study.reddit.core.ext

import ademar.study.reddit.core.test.BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CharSequenceTest : BaseTest() {

    @Test
    fun testExtractNumbers_empty() {
        assertThat("".extractNumbers()).isEqualTo("")
    }

    @Test
    fun testExtractNumbers_onlyNumbers() {
        assertThat("1234567890".extractNumbers()).isEqualTo("1234567890")
    }

    @Test
    fun testExtractNumbers_numbersAndLetters() {
        assertThat("abc123".extractNumbers()).isEqualTo("123")
    }

    @Test
    fun testExtractNumbers_onlyLetters() {
        assertThat("abcdefghijklmnopqrstuvwxyz".extractNumbers()).isEqualTo("")
        assertThat("ABCDEFGHIJKLMNOPQRSTUVWXYZ".extractNumbers()).isEqualTo("")
    }

    @Test
    fun testExtractNumbers_numbersAndSpecialCharacters() {
        assertThat("?/&*123".extractNumbers()).isEqualTo("123")
    }

    @Test
    fun testExtractNumbers_onlySpecialCharacters() {
        assertThat("~`!@#$%^&*()_-+=|\\}{][:;'?/.,".extractNumbers()).isEqualTo("")
    }

}
