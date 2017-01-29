package ademar.study.reddit.plataform.factories

import java.util.*
import javax.inject.Inject

class CalendarFactory @Inject constructor() {

    fun makeCalendar(): Calendar {
        return Calendar.getInstance()
    }

}
