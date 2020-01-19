plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm("android")

    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.3")
        implementation("com.badoo.reaktive:reaktive:1.1.8")
        implementation("com.badoo.reaktive:reaktive-annotations:1.1.8")
        implementation("com.badoo.reaktive:coroutines-interop:1.1.8")
    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")
    }
}

