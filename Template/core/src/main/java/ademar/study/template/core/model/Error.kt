package ademar.study.template.core.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Error(

        val code: Int,
        val message: String

) {

    fun report() = println(this)

    fun toThrowable() = Throwable("{ \"code\": $code, \"message\": \"$message\" }")

}
