package ademar.study.reddit.plataform.factories

import ademar.study.reddit.test.BaseTest
import org.junit.Test

class CalendarFactoryTest : BaseTest() {

    @Test
    fun testMakeCalendar() {
        val calendarFactory = CalendarFactory()
        calendarFactory.makeCalendar()
    }

}
