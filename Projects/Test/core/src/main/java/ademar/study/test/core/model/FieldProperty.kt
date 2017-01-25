package ademar.study.test.core.model

import java.util.*
import kotlin.reflect.KProperty

class FieldProperty<Clazz, Field : Any> {

    private val map = WeakHashMap<Clazz, Field>()

    operator fun getValue(thisRef: Clazz, property: KProperty<*>): Field {
        val value = map[thisRef]
        if (value != null) {
            return value
        } else {
            throw UninitializedPropertyAccessException()
        }
    }

    operator fun setValue(thisRef: Clazz, property: KProperty<*>, value: Field): Field {
        map[thisRef] = value
        return value
    }

}
