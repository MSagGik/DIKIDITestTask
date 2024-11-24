import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.msaggik.datanetwork"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        val properties = Properties()
        properties.load(InputStreamReader(FileInputStream(File("develop.properties"))))
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_ACCESS_TOKEN", "\"${properties.getProperty("apiAccessToken")}\"")
        }
        debug {
            buildConfigField("String", "API_ACCESS_TOKEN", "\"${properties.getProperty("apiAccessToken")}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get().toString()
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidX.core)
    implementation(libs.androidX.appCompat)
    implementation(libs.ui.material)
    testImplementation(libs.unitTests.junit)
    androidTestImplementation(libs.uiTests.junitExt)
    androidTestImplementation(libs.uiTests.espressoCore)

    // Add lib
    implementation(libs.retrofit)
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
    implementation(libs.logging.interceptor)
    implementation(libs.gson)
    implementation(libs.koin)
    implementation(libs.converter.gson)
    implementation(libs.kotlinx.coroutines.android)

    // modules
    implementation(project(":common-utils"))
}
