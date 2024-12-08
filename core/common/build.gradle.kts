import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
}

fun getConfig(key: String): String {
    val properties = Properties().apply {
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            load(localPropertiesFile.inputStream())
        }
    }
    return "\"${properties.getProperty(key, "")}\""
}

android {
    namespace = "com.example.movieapp.core.common"

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    defaultConfig {
        buildConfigField("String", "TMDB_API_KEY", getConfig("TMDB_API_KEY"))
        buildConfigField("String", "TMDB_BASE_URL", getConfig("TMDB_BASE_URL"))
        buildConfigField("String", "TMDB_IMAGE_URL", getConfig("TMDB_IMAGE_URL"))
    }
}

dependencies {
    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    // DI
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Network
    implementation(libs.retrofit)

    // Testing
    testImplementation(libs.bundles.testing)
}