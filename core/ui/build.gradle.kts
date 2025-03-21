plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.movieapp.core.ui"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    // Modules
    implementation(project(":core:common"))
    implementation(project(":core:domain"))

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    // DI
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    // Image Loading
    implementation(libs.coil)

    // Testing
    testImplementation(libs.bundles.testing)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}