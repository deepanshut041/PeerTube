pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "PeerTube"
enableFeaturePreview("GRADLE_METADATA")
include(":app")
include(":common")
include(":desktop")
