package study.template

import study.template.mpc.platformName

object AppData {

    fun name() = "Study template for ${platformName()}"

    fun version() = "1.0.0"

}
