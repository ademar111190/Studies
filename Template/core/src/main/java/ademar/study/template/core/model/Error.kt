package ademar.study.template.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Parcelize
data class Error(

        val code: Int,
        val message: String

) : Parcelable {

    fun report() = println(this)

    fun toThrowable() = Throwable("{ \"code\": $code, \"message\": \"$message\" }")

}
