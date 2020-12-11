plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.squrlabs.peertube"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            this.manifestPlaceholders["crashlyticsCollectionEnabled"] = "false"
        }
        getByName("release") {
            this.manifestPlaceholders["crashlyticsCollectionEnabled"] = "true"
            minifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    dynamicFeatures = mutableSetOf(":app:mobile", ":app:tv")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

dependencies {

    // Internal
    api(project(":common"))

    // JetPack
    api(JetpackLibs.appCompat)
    api(JetpackLibs.material)
    api(JetpackLibs.ktxViewModel)
    api(JetpackLibs.ktxReactiveStreams)
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    api("androidx.lifecycle:lifecycle-extensions:2.2.0")
    api("androidx.preference:preference-ktx:1.1.1")

    // Compose
    api(Compose.ui)
    api(Compose.uiGraphics)
    api(Compose.uiTooling)
    api(Compose.foundationLayout)
    api(Compose.material)
    api(Compose.runtimeLiveData)
    api(Compose.navigation)
    api(Compose.accompanist)

    // Koin
    api(Koin.android)
    api(Koin.androidViewModel)

    // Glide
    api(ExternalLibs.glide)
    kapt(ExternalLibs.glideCompiler)

    // Kohii
    api(ExternalLibs.exoPlayer)
    api(ExternalLibs.kohiiCore)
    api(ExternalLibs.kohiiExoPlayer)

    // Firebase
    api(ExternalLibs.firebaseAnalytics)
    api(ExternalLibs.firebaseCrashlytics)

    // TODO add to dependence
    api("com.mikepenz:iconics-core:5.0.3")
    api("com.mikepenz:iconics-views:5.0.3")
    api("com.mikepenz:community-material-typeface:5.3.45.1-kotlin@aar")

    // Test
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test:runner:1.2.0")
}