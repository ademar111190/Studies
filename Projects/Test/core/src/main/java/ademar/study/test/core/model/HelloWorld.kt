package ademar.study.test.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Parcelize
data class HelloWorld(

        val message: String

) : Parcelable
