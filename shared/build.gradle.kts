import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("com.vanniktech.maven.publish") version "0.28.0"
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.aozora.hlsmui"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


mavenPublishing {
    // Define coordinates for the published artifact
    coordinates(
        groupId = "com.aozora.hlsmui",
        artifactId = "hlsmui",
        version = "1.0.0"
    )

    // Configure POM metadata for the published artifact
    pom {
        name.set("hlsmUI Library")
        description.set("UI Theme Library for Jetpack Compose")
        inceptionYear.set("2024")
        url.set("https://github.com/aozora01/hlsmui")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        // Specify developers information
        developers {
            developer {
                id.set("aozora01")
                name.set("Aozora")
                email.set("aozoralangitbiru1315@gmail.com")
            }
        }

        // Specify SCM information
        scm {
            url.set("https://github.com/aozora01/hlsmui")
        }
    }
}

