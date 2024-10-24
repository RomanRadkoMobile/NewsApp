plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    id("kotlin-kapt")
}

android {
    namespace = "me.radko.practice"
    compileSdk = 34

    defaultConfig {
        applicationId = "me.radko.practice"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "API_KEY", "\"7d0fea2853344c04b99f3a738c62cea7\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.gson)
    implementation (libs.kotlinx.serialization.json)

    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    kapt (libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)

    implementation (libs.androidx.room.ktx)

    // optional - Testing Room Databases
    testImplementation (libs.androidx.room.testing)
    implementation (libs.timber)

    implementation (libs.retrofit2.kotlin.coroutines.adapter)
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = false
}