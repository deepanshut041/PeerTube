plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.kotlin.native.cocoapods")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

kotlin {
    targets {
        val sdkName: String? = System.getenv("SDK_NAME")

        val isiOSDevice = sdkName.orEmpty().startsWith("iphoneos")
        if (isiOSDevice) {
            iosArm64("iOS64")
        } else {
            iosX64("iOS")
        }
        android()
    }


    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Peertube common module"
        homepage = "homepage placeholder"
    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}")

                implementation(Ktor.clientCore)
                implementation(Ktor.clientJson)
                implementation(Ktor.clientLogging)
                implementation(Ktor.clientSerialization)

                // Serialize
                implementation(Serialization.core)

                // koin
                api(Koin.core)

                // Multiplatform Settings
                implementation("com.russhwolf:multiplatform-settings:0.6.3")

                // Kodein-DB
                api("org.kodein.db:kodein-db:${Versions.kodein_db}")
                api("org.kodein.db:kodein-db-serializer-kotlinx:${Versions.kodein_db}")

                // kermit
                api(Deps.kermit)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Ktor.clientAndroid)
            }
        }

        val iOSMain by getting {
            dependencies {
                implementation(Ktor.clientIos)
            }
        }
    }
}