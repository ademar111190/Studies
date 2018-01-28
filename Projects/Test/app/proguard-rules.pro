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

# Glide

-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# Kotlin
-dontwarn kotlin.**

# LoganSquare
-keep class com.bluelinelabs.logansquare.** { *; }
-keep @com.bluelinelabs.logansquare.annotation.JsonObject class *
-keep class **$$JsonObjectMapper { *; }

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
