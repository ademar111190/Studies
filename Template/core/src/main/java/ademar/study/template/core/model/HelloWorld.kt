package ademar.study.template.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Parcelize
data class HelloWorld(

        val message: String,
        val image: String

) : Parcelable
