package study.template

object AppData {

    fun name() = AppDataName("Study template")

    fun version() = AppDataVersion("1.0.0")

}

inline class AppDataName(
    val value: String
)

inline class AppDataVersion(
    val value: String
)
