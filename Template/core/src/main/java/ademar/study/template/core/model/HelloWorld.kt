package ademar.study.template.core.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@PaperParcel
data class HelloWorld(

        val message: String

) : PaperParcelable {

    companion object {

        @JvmField val CREATOR = PaperParcelHelloWorld.CREATOR

    }

}
