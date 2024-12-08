// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.hilt) apply false
}

// Common configurations for all modules
allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }
}

// Clean task configuration
tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

// Common configurations that will be applied to all Android modules
subprojects {
    afterEvaluate {
        project.apply {
            // Common Android configurations
            plugins.withId("com.android.application") {
                configure<com.android.build.gradle.AppExtension> {
                    compileSdkVersion(35)
                    defaultConfig {
                        minSdk = 28
                    }

                    compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_17
                        targetCompatibility = JavaVersion.VERSION_17
                    }
                }
            }
            plugins.withId("com.android.library") {
                configure<com.android.build.gradle.LibraryExtension> {
                    compileSdk = 35
                    defaultConfig {
                        minSdk = 28
                    }

                    compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_17
                        targetCompatibility = JavaVersion.VERSION_17
                    }
                }
            }
        }
    }
}