object Versions {
    const val kotlin = "1.4.21"
    const val kotlinCoroutines = "1.4.2-native-mt"
    const val ktor = "1.4.0"
    const val kotlinxSerialization = "1.0.0-RC"
    const val koin = "3.0.0-alpha-4"
    const val ktx = "1.0.1"
    const val lifecycle = "2.2.0-alpha01"
    const val compose = "1.0.0-alpha09"
    const val nav_compose_version = "1.0.0-alpha04"
    const val coilVersion = "0.4.1"
    const val kermit = "0.1.8"
    const val kodein_db = "0.3.0-beta"
    const val junit = "4.12"

    // Jetpack Libraries
    const val appCompatVersion = "1.2.0"
    const val materialVersion = "1.2.1"
    const val lifecycleVersion = "2.2.0"

    const val firebaseCrashlyticsVersion = "17.2.1"
    const val firebaseAnalyticsVersion = "17.5.0"
    const val glideVersion = "4.11.0"
    const val exoPlayerVersion = "2.11.3"
    const val kohiiVersion = "1.1.0.2011003"

    const val slf4j = "1.7.30"
}

object Deps {
    const val kermit = "co.touchlab:kermit:${Versions.kermit}"
}

object Compose {
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val runtimeRxJava = "androidx.compose.runtime:runtime-rxjava2:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.nav_compose_version}"
    const val accompanist = "dev.chrisbanes.accompanist:accompanist-coil:${Versions.coilVersion}"
}

object Koin {
    val core = "org.koin:koin-core:${Versions.koin}"
    val android = "org.koin:koin-android:${Versions.koin}"
    val androidViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}

object Serialization {
    val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
}

object JetpackLibs {
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    val material = "com.google.android.material:material:${Versions.materialVersion}"
    val ktxViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    val ktxReactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.lifecycleVersion}"
}

object Ktor {
    val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
    val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"

    val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"

    val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
    val clientCio = "io.ktor:ktor-client-cio:${Versions.ktor}"
}

object ExternalLibs {
    val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx:${Versions.firebaseAnalyticsVersion}"
    val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx:${Versions.firebaseCrashlyticsVersion}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    val exoPlayer = "com.google.android.exoplayer:exoplayer:${Versions.exoPlayerVersion}"
    val kohiiCore = "im.ene.kohii:kohii-core:${Versions.kohiiVersion}"
    val kohiiExoPlayer = "im.ene.kohii:kohii-exoplayer:${Versions.kohiiVersion}"
}