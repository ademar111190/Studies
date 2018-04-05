# General
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes Exceptions
-keepattributes LineNumberTable
-keepattributes Signature
-keepattributes SourceFile
-dontwarn sun.misc.**
-dontwarn android.net.SSLCertificateSocketFactory
-dontwarn android.app.Notification

# Kotlin
-dontwarn kotlin.**

# Moshi
-dontwarn okio.**
-dontwarn javax.annotation.**
-keep public class kotlin.reflect.jvm.internal.impl.builtins.* { public *; }
-keep @com.squareup.moshi.JsonQualifier interface *
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

# OkHttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }

# Okio
-dontwarn okio.**

# Retrofit
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8

# RxJava
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
