package study.template

import study.template.mpc.platformName

object AppData {

    fun name() = AppDataName("Study template for ${platformName()}")

    fun version() = AppDataVersion("1.0.0")

}

inline class AppDataName(val value: String)
inline class AppDataVersion(val value: String)
