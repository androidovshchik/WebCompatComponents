# Keep native & callbacks
-keepclasseswithmembernames class *{
    native <methods>;
}

-keepattributes JNINamespace
-keepattributes CalledByNative
-keepattributes *Annotation*
-keepattributes EnclosingMethod

# Too many hard code reflections between xwalk wrapper and bridge,so
# keep all xwalk classes.
-keep class org.xwalk.**{ *; }
-keep interface org.xwalk.**{ *; }
-keep class com.example.extension.**{ *; }
-keep class org.crosswalkproject.**{ *; }

# Rules for org.chromium classes:
# Keep annotations used by chromium to keep members referenced by native code
-keep class org.chromium.base.*Native*
-keep class org.chromium.base.annotations.JNINamespace
-keepclasseswithmembers class org.chromium.** {
    @org.chromium.base.AccessedByNative <fields>;
}
-keepclasseswithmembers class org.chromium.** {
    @org.chromium.base.*Native* <methods>;
}

-keep class org.chromium.** {
    native <methods>;
}

# Keep methods used by reflection and native code
-keep class org.chromium.base.UsedBy*
-keep @org.chromium.base.UsedBy* class *
-keepclassmembers class * {
    @org.chromium.base.UsedBy* *;
}

-keep @org.chromium.base.annotations.JNINamespace* class *
-keepclassmembers class * {
    @org.chromium.base.annotations.CalledByNative* *;
}

# Suppress unnecessary warnings.
-dontnote org.chromium.net.AndroidKeyStore
# Objects of this type are passed around by native code, but the class
# is never used directly by native code. Since the class is not loaded, it does
# not need to be preserved as an entry point.
-dontnote org.chromium.net.UrlRequest$ResponseHeadersMap

# Generate by aapt. may only need for testing, just add them here.
-keep class org.chromium.ui.ColorPickerAdvanced { <init>(...); }
-keep class org.chromium.ui.ColorPickerMoreButton { <init>(...); }
-keep class org.chromium.ui.ColorPickerSimple { <init>(...); }