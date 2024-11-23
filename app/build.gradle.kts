plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.msaggik.dikiditesttask.plugins.developproperties")
}

android {
    namespace = "com.msaggik.dikiditesttask"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.msaggik.dikiditesttask"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(type = "String", name = "API_ACCESS_TOKEN", value = "\"${developProperties.apiAccessToken}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidX.core)
    implementation(libs.androidX.appCompat)

    // UI libraries
    implementation(libs.ui.material)
    implementation(libs.ui.constraintLayout)

    // tests
    testImplementation(libs.unitTests.junit)
    androidTestImplementation(libs.uiTests.junitExt)
    androidTestImplementation(libs.uiTests.espressoCore)

    // lib
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.nav.ui)
    implementation(libs.nav.fragment)
    implementation(libs.koin)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // modules
    implementation(project(":common-ui"))
    implementation(project(":common-utils"))
    implementation(project(":data-network"))
    implementation(project(":feature-home"))
    implementation(project(":feature-catalog"))
    implementation(project(":feature-promotions"))
    implementation(project(":feature-myentries"))
    implementation(project(":feature-more"))
}
